package dev.cuonghoang.repository.communication;

import dev.cuonghoang.entity.communication.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Feedback Repository
 * Repository xử lý truy cập dữ liệu feedback
 * 
 * @author CuongHoang
 * @version 1.0
 */
@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    /**
     * Tìm feedback theo status
     */
    Page<Feedback> findByStatusOrderByCreatedAtDesc(String status, Pageable pageable);

    /**
     * Tìm feedback theo category
     */
    Page<Feedback> findByCategoryOrderByCreatedAtDesc(String category, Pageable pageable);

    /**
     * Tìm feedback theo priority
     */
    Page<Feedback> findByPriorityOrderByCreatedAtDesc(String priority, Pageable pageable);

    /**
     * Tìm feedback chưa đọc
     */
    @Query("SELECT f FROM Feedback f WHERE f.isRead = false ORDER BY f.createdAt DESC")
    Page<Feedback> findUnreadFeedbacks(Pageable pageable);

    /**
     * Tìm feedback theo email
     */
    Page<Feedback> findByEmailOrderByCreatedAtDesc(String email, Pageable pageable);

    /**
     * Tìm feedback theo keyword
     */
    @Query("SELECT f FROM Feedback f WHERE LOWER(f.subject) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(f.message) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(f.name) LIKE LOWER(CONCAT('%', :keyword, '%')) ORDER BY f.createdAt DESC")
    Page<Feedback> findByKeyword(@Param("keyword") String keyword, Pageable pageable);

    /**
     * Tìm feedback trong khoảng thời gian
     */
    @Query("SELECT f FROM Feedback f WHERE f.createdAt BETWEEN :startDate AND :endDate ORDER BY f.createdAt DESC")
    List<Feedback> findByDateRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    /**
     * Tìm feedback public
     */
    @Query("SELECT f FROM Feedback f WHERE f.isPublic = true ORDER BY f.createdAt DESC")
    Page<Feedback> findPublicFeedbacks(Pageable pageable);

    /**
     * Đếm feedback theo status
     */
    Long countByStatus(String status);

    /**
     * Đếm feedback chưa đọc
     */
    Long countByIsReadFalse();

    /**
     * Tìm feedback có rating
     */
    @Query("SELECT f FROM Feedback f WHERE f.rating IS NOT NULL ORDER BY f.createdAt DESC")
    Page<Feedback> findFeedbacksWithRating(Pageable pageable);

    /**
     * Tính rating trung bình
     */
    @Query("SELECT AVG(f.rating) FROM Feedback f WHERE f.rating IS NOT NULL")
    Double getAverageRating();
}
