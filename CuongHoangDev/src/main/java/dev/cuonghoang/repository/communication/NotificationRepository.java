package dev.cuonghoang.repository.communication;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dev.cuonghoang.entity.communication.Notification;

/**
 * Notification Repository
 * Repository xử lý truy cập dữ liệu notification
 * 
 * @author CuongHoang
 * @version 1.0
 */
@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    /**
     * Tìm notification theo user
     */
    @Query("SELECT n FROM Notification n WHERE (n.userId = :userId OR n.isGlobal = true) AND n.active = true ORDER BY n.createdAt DESC")
    Page<Notification> findByUserIdOrGlobal(@Param("userId") Long userId, Pageable pageable);

    /**
     * Tìm notification chưa đọc của user
     */
    @Query("SELECT n FROM Notification n WHERE (n.userId = :userId OR n.isGlobal = true) AND n.isRead = false AND n.active = true ORDER BY n.createdAt DESC")
    List<Notification> findUnreadByUserId(@Param("userId") Long userId);

    /**
     * Tìm notification theo type
     */
    Page<Notification> findByTypeAndActiveTrue(String type, Pageable pageable);

    /**
     * Tìm notification theo category
     */
    Page<Notification> findByCategoryAndActiveTrue(String category, Pageable pageable);

    /**
     * Tìm notification theo priority
     */
    Page<Notification> findByPriorityAndActiveTrue(String priority, Pageable pageable);

    /**
     * Tìm global notifications
     */
    @Query("SELECT n FROM Notification n WHERE n.isGlobal = true AND n.active = true ORDER BY n.createdAt DESC")
    Page<Notification> findGlobalNotifications(Pageable pageable);

    /**
     * Tìm scheduled notifications
     */
    @Query("SELECT n FROM Notification n WHERE n.scheduledAt IS NOT NULL AND n.scheduledAt <= :now AND n.active = true")
    List<Notification> findScheduledNotifications(@Param("now") LocalDateTime now);

    /**
     * Tìm expired notifications
     */
    @Query("SELECT n FROM Notification n WHERE n.expiresAt IS NOT NULL AND n.expiresAt <= :now AND n.active = true")
    List<Notification> findExpiredNotifications(@Param("now") LocalDateTime now);

    /**
     * Đếm notification chưa đọc của user
     */
    @Query("SELECT COUNT(n) FROM Notification n WHERE (n.userId = :userId OR n.isGlobal = true) AND n.isRead = false AND n.active = true")
    Long countUnreadByUserId(@Param("userId") Long userId);

    /**
     * Tìm notification theo target role
     */
    Page<Notification> findByTargetRoleAndActiveTrue(String targetRole, Pageable pageable);

    /**
     * Tìm notification trong khoảng thời gian
     */
    @Query("SELECT n FROM Notification n WHERE n.createdAt BETWEEN :startDate AND :endDate AND n.active = true ORDER BY n.createdAt DESC")
    List<Notification> findByDateRange(@Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    /**
     * Đánh dấu tất cả notification của user là đã đọc
     */
    @Modifying
    @Query("UPDATE Notification n SET n.isRead = true, n.readAt = :readAt WHERE (n.userId = :userId OR n.isGlobal = true) AND n.isRead = false AND n.active = true")
    void markAllAsReadByUserId(@Param("userId") Long userId, @Param("readAt") LocalDateTime readAt);
}
