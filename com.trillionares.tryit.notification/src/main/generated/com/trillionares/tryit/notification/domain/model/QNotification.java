package com.trillionares.tryit.notification.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QNotification is a Querydsl query type for Notification
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNotification extends EntityPathBase<Notification> {

    private static final long serialVersionUID = 1918851396L;

    public static final QNotification notification = new QNotification("notification");

    public final com.trillionares.tryit.notification.domain.common.base.QBaseEntity _super = new com.trillionares.tryit.notification.domain.common.base.QBaseEntity(this);

    public final NumberPath<Integer> attemptCount = createNumber("attemptCount", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final DateTimePath<java.time.LocalDateTime> expiryDate = createDateTime("expiryDate", java.time.LocalDateTime.class);

    public final StringPath messageId = createString("messageId");

    public final ComparablePath<java.util.UUID> notificationId = createComparable("notificationId", java.util.UUID.class);

    public final EnumPath<NotificationStatus> notificationStatus = createEnum("notificationStatus", NotificationStatus.class);

    public final ComparablePath<java.util.UUID> submissionId = createComparable("submissionId", java.util.UUID.class);

    public final ComparablePath<java.util.UUID> userId = createComparable("userId", java.util.UUID.class);

    public QNotification(String variable) {
        super(Notification.class, forVariable(variable));
    }

    public QNotification(Path<? extends Notification> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNotification(PathMetadata metadata) {
        super(Notification.class, metadata);
    }

}

