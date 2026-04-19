package dev.cuonghoang.entity.communication;

import dev.cuonghoang.entity.base.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Notification Entity
 * Entity đại diện cho thông báo
 * 
 * @author CuongHoang
 * @version 1.0
 */
@Entity
@Table(name = "notifications")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Notification extends AuditableEntity {

    // ID inherited from BaseEntity

    private Long userId; // Null for global notifications

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String message;

    private String type; // INFO, SUCCESS, WARNING, ERROR, SYSTEM

    private String category; // USER_ACTION, SYSTEM_UPDATE, SECURITY, PROMOTION

    @Column(columnDefinition = "boolean default false")
    private Boolean isRead = false;

    @Column(columnDefinition = "boolean default false")
    private Boolean isGlobal = false; // For all users

    private String actionUrl; // URL to redirect when clicked
    private String actionText; // Text for action button

    private String iconClass; // CSS class for icon
    private String iconColor; // Color for icon

    private java.time.LocalDateTime scheduledAt; // For scheduled notifications
    private java.time.LocalDateTime readAt;
    private java.time.LocalDateTime expiresAt;

    @Column(columnDefinition = "boolean default true")
    private Boolean active = true;

    private String priority; // LOW, MEDIUM, HIGH

    @Column(columnDefinition = "TEXT")
    private String metadata; // JSON string for additional data

    private String createdBy; // Who created this notification
    private String targetRole; // Target specific role (ADMIN, USER, etc.)
}
