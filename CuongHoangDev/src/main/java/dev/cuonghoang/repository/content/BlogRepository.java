package dev.cuonghoang.repository.content;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dev.cuonghoang.entity.content.Blog;

/**
 * Blog Data Access Repository
 * Repository xử lý truy cập dữ liệu blog
 * 
 * @author CuongHoang
 * @version 1.0
 */
@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {

    /**
     * Tìm blog theo slug
     */
    Optional<Blog> findBySlug(String slug);

    /**
     * Tìm blog đã publish
     */
    @Query("SELECT b FROM Blog b WHERE b.status = 'PUBLISHED' AND b.publishedAt IS NOT NULL AND b.publishedAt <= CURRENT_TIMESTAMP ORDER BY b.createdAt DESC")
    Page<Blog> findPublishedBlogs(Pageable pageable);

    /**
     * Tìm blog theo category
     */
    @Query("SELECT b FROM Blog b WHERE b.category = :category AND b.status = 'PUBLISHED' AND b.publishedAt IS NOT NULL AND b.publishedAt <= CURRENT_TIMESTAMP ORDER BY b.createdAt DESC")
    Page<Blog> findByCategory(@Param("category") String category, Pageable pageable);

    /**
     * Tìm blog theo tag
     */
    @Query("SELECT b FROM Blog b WHERE (b.tags IS NOT NULL AND (LOWER(b.tags) LIKE LOWER(CONCAT('%', :tag, '%')) OR LOWER(b.tags) LIKE LOWER(CONCAT('%,', :tag, '%')) OR LOWER(b.tags) LIKE LOWER(CONCAT('%,', :tag, ',%')) OR LOWER(b.tags) LIKE LOWER(CONCAT(:tag, ',%')))) AND b.status = 'PUBLISHED' AND b.publishedAt IS NOT NULL AND b.publishedAt <= CURRENT_TIMESTAMP ORDER BY b.createdAt DESC")
    Page<Blog> findByTag(@Param("tag") String tag, Pageable pageable);

    /**
     * Tìm blog featured
     */
    @Query("SELECT b FROM Blog b WHERE b.featured = true AND b.status = 'PUBLISHED' AND b.publishedAt IS NOT NULL AND b.publishedAt <= CURRENT_TIMESTAMP ORDER BY b.createdAt DESC")
    List<Blog> findFeaturedBlogs();

    /**
     * Tìm blog theo keyword
     */
    @Query("SELECT b FROM Blog b WHERE (LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR b.content LIKE CONCAT('%', :keyword, '%')) AND b.status = 'PUBLISHED' AND b.publishedAt IS NOT NULL AND b.publishedAt <= CURRENT_TIMESTAMP ORDER BY b.createdAt DESC")
    Page<Blog> findByKeyword(@Param("keyword") String keyword, Pageable pageable);
}
