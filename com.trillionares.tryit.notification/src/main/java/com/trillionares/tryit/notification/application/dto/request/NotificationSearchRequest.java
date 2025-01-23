package com.trillionares.tryit.notification.application.dto.request;

import com.trillionares.tryit.notification.domain.model.NotificationStatus;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NotificationSearchRequest {

  private NotificationStatus status;
  private UUID userId;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
}
