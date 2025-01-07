package com.trillionares.tryit.trial.jhtest.domain.model;

import com.trillionares.tryit.trial.jhtest.domain.common.base.BaseEntity;
import com.trillionares.tryit.trial.jhtest.domain.model.type.SubmissionStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Table(name = "p_submission", schema = "recruitment")
public class Trial extends BaseEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "submission_id", updatable = false, nullable = false)
    private UUID submissionId;
    @Column(name = "recruitment_id", nullable = false)
    private UUID recruitmentId;
    @Column(name = "user_id", nullable = false)
    private UUID userId;
    @Enumerated(EnumType.STRING)
    @Column(name = "submission_status", nullable = false)
    private SubmissionStatus submissionStatus;
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "selected_sequence")
    private Long selectedSequence;

    public void setSubmissionStatus(SubmissionStatus status) {
        this.submissionStatus = status;
    }
}
