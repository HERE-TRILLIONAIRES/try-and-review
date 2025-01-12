package com.trillionares.tryit.product.domain.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.trillionares.tryit.product.domain.model.recruitment.Recruitment;
import com.trillionares.tryit.product.domain.model.recruitment.QRecruitment;
import com.trillionares.tryit.product.presentation.dto.response.GetRecruitmentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomRecruitmentRepositoryImpl implements CustomRecruitmentRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Slice<GetRecruitmentResponse> getRecruitmentList(Pageable pageable) {
        QRecruitment recruitment = QRecruitment.recruitment;

        List<Recruitment> recruitments = queryFactory
                .selectFrom(recruitment)
                .orderBy(recruitment.recruitmentStartDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1)
                .fetch();

        List<GetRecruitmentResponse> recruitmentResponses = recruitments.stream()
                .map(GetRecruitmentResponse::fromEntity)
                .toList();

        boolean hasNext = recruitmentResponses.size() > pageable.getPageSize();

        if (hasNext) {
            recruitmentResponses = recruitmentResponses.subList(0, pageable.getPageSize());
        }

        return new SliceImpl<>(recruitmentResponses, pageable, hasNext);
    }

}
