package dev.cuonghoang.repository.file;

import dev.cuonghoang.entity.file.FileMetadata;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * File Metadata Repository
 * Repository xử lý truy cập dữ liệu file metadata
 * 
 * @author CuongHoang
 * @version 1.0
 */
@Repository
public interface FileMetadataRepository extends JpaRepository<FileMetadata, Long> {

    /**
     * Tìm file theo tên file
     */
    Optional<FileMetadata> findByFileName(String fileName);

    /**
     * Tìm file theo category
     */
    Page<FileMetadata> findByCategoryAndActiveTrue(String category, Pageable pageable);

    /**
     * Tìm file theo file type
     */
    List<FileMetadata> findByFileTypeAndActiveTrue(String fileType);

    /**
     * Tìm file theo người upload
     */
    Page<FileMetadata> findByUploadedByAndActiveTrue(String uploadedBy, Pageable pageable);

    /**
     * Tìm file theo kích thước
     */
    @Query("SELECT f FROM FileMetadata f WHERE f.fileSize BETWEEN :minSize AND :maxSize AND f.active = true")
    List<FileMetadata> findByFileSizeRange(@Param("minSize") Long minSize, @Param("maxSize") Long maxSize);

    /**
     * Tìm file theo mime type
     */
    List<FileMetadata> findByMimeTypeAndActiveTrue(String mimeType);

    /**
     * Tìm file theo keyword
     */
    @Query("SELECT f FROM FileMetadata f WHERE (LOWER(f.fileName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(f.originalFileName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(f.description) LIKE LOWER(CONCAT('%', :keyword, '%'))) AND f.active = true")
    Page<FileMetadata> findByKeyword(@Param("keyword") String keyword, Pageable pageable);

    /**
     * Tìm file images
     */
    @Query("SELECT f FROM FileMetadata f WHERE f.category = 'IMAGE' AND f.active = true ORDER BY f.createdAt DESC")
    Page<FileMetadata> findImages(Pageable pageable);

    /**
     * Tìm file documents
     */
    @Query("SELECT f FROM FileMetadata f WHERE f.category = 'DOCUMENT' AND f.active = true ORDER BY f.createdAt DESC")
    Page<FileMetadata> findDocuments(Pageable pageable);

    /**
     * Tính tổng dung lượng file
     */
    @Query("SELECT SUM(f.fileSize) FROM FileMetadata f WHERE f.active = true")
    Long getTotalFileSize();

    /**
     * Đếm số file theo category
     */
    Long countByCategoryAndActiveTrue(String category);
}
