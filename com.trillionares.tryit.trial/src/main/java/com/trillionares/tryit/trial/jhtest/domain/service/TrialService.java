package com.trillionares.tryit.trial.jhtest.domain.service;

import com.trillionares.tryit.trial.jhtest.domain.client.AuthClient;
import com.trillionares.tryit.trial.jhtest.domain.client.RecruitmentClient;
import com.trillionares.tryit.trial.jhtest.domain.common.json.JsonUtils;
import com.trillionares.tryit.trial.jhtest.domain.model.Trial;
import com.trillionares.tryit.trial.jhtest.domain.model.type.SubmissionStatus;
import com.trillionares.tryit.trial.jhtest.domain.repository.TrialRepository;
import com.trillionares.tryit.trial.jhtest.presentation.dto.RecruitmentExistAndStatusDto;
import com.trillionares.tryit.trial.jhtest.presentation.dto.SendNotificationDto;
import com.trillionares.tryit.trial.jhtest.presentation.dto.SendRecruitmentDto;
import com.trillionares.tryit.trial.jhtest.presentation.dto.SubmissionIdAndStatusResponseDto;
import com.trillionares.tryit.trial.jhtest.presentation.dto.TrialIdResponseDto;
import com.trillionares.tryit.trial.jhtest.presentation.dto.TrialInfoRequestDto;
import com.trillionares.tryit.trial.jhtest.presentation.dto.common.kafka.KafkaMessage;
import com.trillionares.tryit.trial.jhtest.presentation.dto.trial.TrialInfoResponseDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TrialService {

    private final TrialRepository trialRepository;

    private final AuthClient authClient;
    private final RecruitmentClient recruitmentClient;

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Transactional
    public TrialIdResponseDto createTrial(String username, String role, TrialInfoRequestDto requestDto) {
        if(!validatePermission(role)){
            throw new IllegalArgumentException("관리자나 판매자는 체험 신청할 수 없습니다.");
        }

        checkExistRecruitment(requestDto.getRecruitmentId());

        // TODO: UserId 비동기 업데이트 고려해보기
        UUID userId = authClient.getUserByUsername(username).getData().getUserId();

        if(existPastSubmissionHistory(userId, requestDto.getRecruitmentId())) {
            throw new RuntimeException("이전 신청내역이 있는 모집 입니다.");
        }

        Trial trial = TrialInfoRequestDto.toCreateEntity(requestDto, userId, username);

        trialRepository.save(trial);


        // TODO: 재고 빼기, 신청시간 담기, 신청자 정보 담기
        SendRecruitmentDto sendRecruitmentDto = SendRecruitmentDto.of(trial.getSubmissionId(), requestDto.getRecruitmentId(), userId, trial.getQuantity(), String.valueOf(trial.getCreatedAt()));
        try {
            String sendPayloadJson = JsonUtils.toJson(sendRecruitmentDto);
            KafkaMessage sendMessage = KafkaMessage.from(sendPayloadJson);
            String sendMessageJson = JsonUtils.toJson(sendMessage);

            kafkaTemplate.send("checkPossible", "ValidatedRecruitment-req", sendMessageJson);
        } catch (Exception e){
            throw new RuntimeException("Recruitment로 메시지 생성 실패");
        }

        sendMessageToNotification(trial.getSubmissionId());

        return TrialIdResponseDto.from(trial.getSubmissionId());
    }

    private void checkExistRecruitment(UUID recruitmentId) {
        // TODO: 객체가 null인지 판단 중인데, 존재여부만 확인하는 endpoint 추가 요청

        RecruitmentExistAndStatusDto responseDto = recruitmentClient.isExistRecruitmentById(recruitmentId).getData();

        if(!responseDto.getIsExist()) {
            throw new IllegalArgumentException("존재하지 않는 모집 입니다.");
        } else if(!responseDto.getStatus().contains("WAITING")) {
            throw new IllegalArgumentException("모집이 시작되지 않았습니다.");
        } else if(!responseDto.getStatus().contains("PAUSED")) {
            throw new IllegalArgumentException("모집이 중단 되었습니다.");
        } else if(!responseDto.getStatus().contains("ENDED")) {
            throw new IllegalArgumentException("모집이 종료 되었습니다.");
        } else if(!responseDto.getStatus().contains("NOT_FOUND")) {
            throw new IllegalArgumentException("모집을 찾을 수 없습니다.");
        }
    }

    private Boolean existPastSubmissionHistory(UUID userId, UUID recruitmentId) {
        if(trialRepository.existsByUserIdAndRecruitmentIdAndIsDeletedFalse(userId, recruitmentId)) {
            return true;
        }
        return false;
    }

    private Boolean validatePermission(String role) {
        if(role.contains("MEMBER")){
            return true;
        }
        return false;
    }

    public void sendMessageToNotification(UUID submissionId) {
        Trial trial = trialRepository.findBySubmissionIdAndIsDeletedFalse(submissionId).orElse(null);
        if(trial == null){
            throw new RuntimeException("신청을 찾을 수 없습니다.");
        }

        // TODO: 알람 보내기 (신청되었다는 알람만, 몇번째인지? 당첨되었는지?는 재고와 모집마감시간 비교후 적용)
        SendNotificationDto sendDto = SendNotificationDto.of(
                trial.getSubmissionId(),
                trial.getUserId(),
                trial.getRecruitmentId(),
                trial.getSubmissionStatus(),
                String.valueOf(trial.getCreatedAt())
        );
        try {
            String sendPayloadJson = JsonUtils.toJson(sendDto);
            KafkaMessage sendMessage = KafkaMessage.from(sendPayloadJson);
            String sendMessageJson = JsonUtils.toJson(sendMessage);

            log.info("sendMessageJson : {}", sendMessageJson);
            kafkaTemplate.send("tryit-completed", "NotificationInfo-req", sendMessageJson);
        } catch (Exception e) {
            throw new RuntimeException("Notification으로 메시지 생성 실패");
        }
    }

    public TrialInfoResponseDto getTrialById(UUID submissionId) {
        Trial trial = trialRepository.findBySubmissionIdAndIsDeletedFalse(submissionId).orElse(null);
        if(trial == null){
            throw new RuntimeException("신청을 찾을 수 없습니다.");
        }

        // TODO: User Service 호출해서 신청자 정보 받아오기, 내가 신청한건지 확인
        String trialedUser = "신청자";

        return TrialInfoResponseDto.from(trial);
    }

    @Transactional
    public TrialIdResponseDto changeStatusOfTrial(String username, String role, UUID submissionId, SubmissionStatus status) {
        if(!updateAndDeleteValidatePermission(role)){
            throw new IllegalArgumentException("판매자는 신청을 수정 할 수 없습니다.");
        }

        Trial trial = trialRepository.findBySubmissionIdAndIsDeletedFalse(submissionId).orElse(null);
        if(trial == null){
            throw new RuntimeException("신청을 찾을 수 없습니다.");
        }

        // TODO: UserId 비동기 업데이트 고려해보기
        UUID userId = authClient.getUserByUsername(username).getData().getUserId();
        if(!isSubmissionOwner(userId, trial.getUserId()) && role.contains("MEMBER")) {
            throw new IllegalArgumentException("로그인한 사용자의 신청이 아닙니다.");
        }

        trial = statusConvert(trial, trial.getSubmissionStatus(), status, username);

        return TrialIdResponseDto.from(trial.getSubmissionId());
    }

    private Boolean isSubmissionOwner(UUID userId1, UUID userId2) {
        if(userId1.equals(userId2)) {
            return true;
        }
        return false;
    }

    private Boolean updateAndDeleteValidatePermission(String role) {
        if(role.contains("MEMBER")){
            return true;
        } else if(role.contains("ADMIN")) {
            return true;
        }
        return false;
    }

    private Trial statusConvert(Trial trial, SubmissionStatus previousStatus, SubmissionStatus nextStatus, String username){
        // TODO: 변경 전 변경 가능 상태 비교
        // 신청 -> 당첨, 신청취소, 낙첨
        // 당첨 -> 리뷰 제출
        if((previousStatus == SubmissionStatus.APPLIED && (
                nextStatus == SubmissionStatus.SELECTED
                        || nextStatus == SubmissionStatus.CANCELED
                        || nextStatus == SubmissionStatus.FAILED))
        || (previousStatus == SubmissionStatus.SELECTED && nextStatus == SubmissionStatus.REVIEW_SUBMITTED)) {
            trial.setSubmissionStatus(nextStatus);
            trial.setUpdatedBy(username);
            trialRepository.save(trial);
        }

        return trial;
    }

    @Transactional
    public TrialIdResponseDto deleteTrial(String username, String role, UUID submissionId) {
        if(!updateAndDeleteValidatePermission(role)){
            throw new IllegalArgumentException("판매자는 신청을 삭제 할 수 없습니다.");
        }

        Trial trial = trialRepository.findBySubmissionIdAndIsDeletedFalse(submissionId).orElse(null);
        if(trial == null){
            throw new RuntimeException("신청을 찾을 수 없습니다.");
        }

        // TODO: UserId 비동기 업데이트 고려해보기
        UUID userId = authClient.getUserByUsername(username).getData().getUserId();
        if(!isSubmissionOwner(userId, trial.getUserId()) && role.contains("MEMBER")) {
            throw new IllegalArgumentException("로그인한 사용자의 신청이 아닙니다.");
        }

        trial = toDeletedStatusOfTrial(trial, SubmissionStatus.CANCELED, username);

        return TrialIdResponseDto.from(trial.getSubmissionId());
    }

    private Trial toDeletedStatusOfTrial(Trial trial, SubmissionStatus submissionStatus, String username) {
        if(trial.getSubmissionStatus() == SubmissionStatus.APPLIED) {
            trial.setSubmissionStatus(submissionStatus);
            trial.delete(username);
            trialRepository.save(trial);
        }

        return trial;
    }

    public SubmissionIdAndStatusResponseDto validateIsSelected(UUID submissionId) {
        Trial trial = trialRepository.findBySubmissionIdAndIsDeletedFalse(submissionId).orElse(null);
        if(trial == null){
            throw new RuntimeException("신청을 찾을 수 없습니다.");
        }


        if(trial.getSubmissionStatus().getMessage().equals(SubmissionStatus.SELECTED.getMessage())) {
            SubmissionIdAndStatusResponseDto responseDto = SubmissionIdAndStatusResponseDto.of(
                    trial.getSubmissionId(), true
            );

            return responseDto;
        }
        else {
            SubmissionIdAndStatusResponseDto responseDto = SubmissionIdAndStatusResponseDto.of(
                    trial.getSubmissionId(), false
            );

            return responseDto;
        }
    }

    @Transactional
    public TrialIdResponseDto updateSubmissionStatusToReviewSubmit(UUID submissionId) {
        Trial trial = trialRepository.findBySubmissionIdAndIsDeletedFalse(submissionId).orElse(null);
        if(trial == null){
            throw new RuntimeException("신청을 찾을 수 없습니다.");
        }

        trial = statusConvert(trial, trial.getSubmissionStatus(), SubmissionStatus.REVIEW_SUBMITTED, "시스템");

        return TrialIdResponseDto.from(trial.getSubmissionId());
    }
}
