package dev.cuonghoang.repository.content;

import dev.cuonghoang.entity.content.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Project Data Access Repository
 * Repository xử lý truy cập dữ liệu dự án
 * 
 * @author CuongHoang
 * @version 1.0
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    /**
     * Tìm project theo slug
     */
    Optional<Project> findBySlug(String slug);

    /**
     * Tìm project featured
     */
    @Query("SELECT p FROM Project p WHERE p.featured = true ORDER BY p.createdAt DESC")
    List<Project> findFeaturedProjects();

    /**
     * Tìm project theo technology
     */
    @Query("SELECT p FROM Project p WHERE :technology MEMBER OF p.technologies ORDER BY p.createdAt DESC")
    List<Project> findByTechnology(@Param("technology") String technology);

    /**
     * Tìm project theo status
     */
    List<Project> findByStatusOrderByCreatedAtDesc(String status);

    /**
     * Tìm project theo category
     */
    List<Project> findByCategoryOrderByCreatedAtDesc(String category);

    /**
     * Tìm project theo keyword
     */
    @Query("SELECT p FROM Project p WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%')) ORDER BY p.createdAt DESC")
    List<Project> findByKeyword(@Param("keyword") String keyword);
}
