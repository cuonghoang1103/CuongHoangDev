package dev.cuonghoang.config.web;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import java.util.Arrays;
import java.util.List;

/**
 * File Upload Configuration
 * Cấu hình tải lên file
 * 
 * @author CuongHoang
 * @since 1.0.0
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "app.upload")
public class FileUploadConfig {

    /**
     * Upload directory path
     */
    private String path = "uploads/";

    /**
     * Maximum file size in bytes (default: 10MB)
     */
    private long maxSize = 10485760L;

    /**
     * Allowed file types
     */
    private List<String> allowedTypes = Arrays.asList(
        "jpg", "jpeg", "png", "gif", "bmp", "webp", // Images
        "pdf", "doc", "docx", "txt", "rtf", // Documents
        "mp3", "wav", "ogg", "m4a", // Audio
        "mp4", "avi", "mov", "wmv", "flv", // Video
        "zip", "rar", "7z" // Archives
    );

    /**
     * Image file types
     */
    private List<String> imageTypes = Arrays.asList(
        "jpg", "jpeg", "png", "gif", "bmp", "webp"
    );

    /**
     * Document file types
     */
    private List<String> documentTypes = Arrays.asList(
        "pdf", "doc", "docx", "txt", "rtf", "xls", "xlsx", "ppt", "pptx"
    );

    /**
     * Audio file types
     */
    private List<String> audioTypes = Arrays.asList(
        "mp3", "wav", "ogg", "m4a", "aac", "flac"
    );

    /**
     * Video file types
     */
    private List<String> videoTypes = Arrays.asList(
        "mp4", "avi", "mov", "wmv", "flv", "mkv", "webm"
    );

    /**
     * Maximum image width for resizing
     */
    private int maxImageWidth = 1920;

    /**
     * Maximum image height for resizing
     */
    private int maxImageHeight = 1080;

    /**
     * Image quality for compression (0-100)
     */
    private int imageQuality = 85;

    /**
     * Generate thumbnails for images
     */
    private boolean generateThumbnails = true;

    /**
     * Thumbnail width
     */
    private int thumbnailWidth = 300;

    /**
     * Thumbnail height
     */
    private int thumbnailHeight = 300;

    /**
     * Multipart Resolver Bean
     */
    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

    /**
     * Check if file type is allowed
     */
    public boolean isAllowedType(String fileExtension) {
        return allowedTypes.contains(fileExtension.toLowerCase());
    }

    /**
     * Check if file is an image
     */
    public boolean isImageType(String fileExtension) {
        return imageTypes.contains(fileExtension.toLowerCase());
    }

    /**
     * Check if file is a document
     */
    public boolean isDocumentType(String fileExtension) {
        return documentTypes.contains(fileExtension.toLowerCase());
    }

    /**
     * Check if file is audio
     */
    public boolean isAudioType(String fileExtension) {
        return audioTypes.contains(fileExtension.toLowerCase());
    }

    /**
     * Check if file is video
     */
    public boolean isVideoType(String fileExtension) {
        return videoTypes.contains(fileExtension.toLowerCase());
    }
}
