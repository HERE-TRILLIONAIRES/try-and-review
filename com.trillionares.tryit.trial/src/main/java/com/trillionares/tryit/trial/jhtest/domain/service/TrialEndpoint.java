package com.trillionares.tryit.trial.jhtest.domain.service;

import com.trillionares.tryit.trial.jhtest.domain.common.json.JsonUtils;
import com.trillionares.tryit.trial.jhtest.domain.model.type.SubmissionStatus;
import com.trillionares.tryit.trial.jhtest.presentation.dto.UpdateStatusDto;
import com.trillionares.tryit.trial.jhtest.presentation.dto.common.kafka.KafkaMessage;
import com.trillionares.tryit.trial.jhtest.presentation.dto.trial.TrialInfoResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class TrialEndpoint {

    private final TrialService trialService;

    @Transactional
    @KafkaListener(groupId = "recruitment-tryit", topics = "updateStatus")
    public void updateStatus(String message) throws Exception {
        KafkaMessage kafkaMessage = JsonUtils.fromJson(message, KafkaMessage.class);

        UpdateStatusDto updateStatusDto = JsonUtils.fromJson(kafkaMessage.getPayload(), UpdateStatusDto.class);

        // TODO: Status 값에 따라 분기문 수정하기
        if(updateStatusDto.getStatus().contains("FAIL")){ // 상태는 아직 가칭 FAIL
            trialService.changeStatusOfTrial(updateStatusDto.getSubmissionId(), SubmissionStatus.FAILED);
        }
        trialService.changeStatusOfTrial(updateStatusDto.getSubmissionId(), SubmissionStatus.SELECTED);

        trialService.sendMessageToNotification(updateStatusDto.getSubmissionId());
    }
}
