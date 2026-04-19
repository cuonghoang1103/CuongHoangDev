package dev.cuonghoang.repository.content;

import dev.cuonghoang.entity.content.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Product Data Access Repository
 * Repository xử lý truy cập dữ liệu sản phẩm
 * 
 * @author CuongHoang
 * @version 1.0
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Tìm product theo slug
     */
    Optional<Product> findBySlug(String slug);

    /**
     * Tìm product theo category
     */
    Page<Product> findByCategoryOrderByCreatedAtDesc(String category, Pageable pageable);

    /**
     * Tìm product featured
     */
    @Query("SELECT p FROM Product p WHERE p.featured = true ORDER BY p.createdAt DESC")
    List<Product> findFeaturedProducts();

    /**
     * Tìm product theo price range
     */
    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :minPrice AND :maxPrice ORDER BY p.price ASC")
    Page<Product> findByPriceRange(@Param("minPrice") BigDecimal minPrice, @Param("maxPrice") BigDecimal maxPrice, Pageable pageable);

    /**
     * Tìm product available
     */
    @Query("SELECT p FROM Product p WHERE p.available = true ORDER BY p.createdAt DESC")
    Page<Product> findAvailableProducts(Pageable pageable);

    /**
     * Tìm product theo keyword
     */
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%')) ORDER BY p.createdAt DESC")
    Page<Product> findByKeyword(@Param("keyword") String keyword, Pageable pageable);
}
