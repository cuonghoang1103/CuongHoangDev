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
 * Feedback Entity
 * Entity đại diện cho phản hồi từ người dùng
 * 
 * @author CuongHoang
 * @version 1.0
 */
@Entity
@Table(name = "feedbacks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Feedback extends AuditableEntity {

    // ID inherited from BaseEntity

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    private String phoneNumber;

    @Column(nullable = false)
    private String subject;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String message;

    private String category; // GENERAL, SUPPORT, COMPLAINT, SUGGESTION, BUG_REPORT

    private String status; // NEW, IN_PROGRESS, RESOLVED, CLOSED

    private String priority; // LOW, MEDIUM, HIGH, URGENT

    @Column(columnDefinition = "TEXT")
    private String adminReply;

    private String repliedBy;
    private java.time.LocalDateTime repliedAt;

    @Column(columnDefinition = "boolean default false")
    private Boolean isRead = false;

    @Column(columnDefinition = "boolean default false")
    private Boolean isPublic = false;

    private Integer rating; // 1-5 stars if it's a rating feedback

    private String ipAddress;
    private String userAgent;

    @Column(columnDefinition = "TEXT")
    private String attachments; // JSON array of file URLs

    @Column(columnDefinition = "TEXT")
    private String tags; // Comma-separated tags
}
