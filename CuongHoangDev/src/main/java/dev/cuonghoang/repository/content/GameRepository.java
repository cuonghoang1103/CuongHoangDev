package dev.cuonghoang.repository.content;

import dev.cuonghoang.entity.content.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Game Data Access Repository
 * Repository xử lý truy cập dữ liệu game
 * 
 * @author CuongHoang
 * @version 1.0
 */
@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    /**
     * Tìm game theo slug
     */
    Optional<Game> findBySlug(String slug);

    /**
     * Tìm game theo genre
     */
    List<Game> findByGenreOrderByCreatedAtDesc(String genre);

    /**
     * Tìm game theo platform
     */
    @Query("SELECT g FROM Game g WHERE :platform MEMBER OF g.platforms ORDER BY g.createdAt DESC")
    List<Game> findByPlatform(@Param("platform") String platform);

    /**
     * Tìm game theo rating range
     */
    @Query("SELECT g FROM Game g WHERE g.rating BETWEEN :minRating AND :maxRating ORDER BY g.rating DESC")
    List<Game> findByRatingRange(@Param("minRating") Double minRating, @Param("maxRating") Double maxRating);

    /**
     * Tìm game featured
     */
    @Query("SELECT g FROM Game g WHERE g.featured = true ORDER BY g.createdAt DESC")
    List<Game> findFeaturedGames();

    /**
     * Tìm game theo keyword
     */
    @Query("SELECT g FROM Game g WHERE LOWER(g.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(g.description) LIKE LOWER(CONCAT('%', :keyword, '%')) ORDER BY g.createdAt DESC")
    List<Game> findByKeyword(@Param("keyword") String keyword);

    /**
     * Tìm top rated games
     */
    @Query("SELECT g FROM Game g ORDER BY g.rating DESC")
    List<Game> findTopRatedGames();
}
