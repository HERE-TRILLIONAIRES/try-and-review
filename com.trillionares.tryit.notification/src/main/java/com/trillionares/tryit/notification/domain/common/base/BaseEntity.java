package com.trillionares.tryit.notification.domain.common.base;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

  @CreatedDate
  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;
  @CreatedBy
  @Column(name = "created_by", updatable = false)
  private String createdBy;
  @LastModifiedDate
  @Column(name = "updated_at", nullable = false)
  private LocalDateTime updatedAt;
  @LastModifiedBy
  @Column(name = "updated_by")
  private String updatedBy;
  @Column(name = "deleted_at")
  private LocalDateTime deletedAt;
  @Column(name = "deleted_by")
  private String deletedBy;
  @Column(name = "is_deleted", nullable = false)
  private boolean isDeleted;
}
