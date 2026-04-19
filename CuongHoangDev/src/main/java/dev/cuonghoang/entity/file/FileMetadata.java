package dev.cuonghoang.entity.file;

import dev.cuonghoang.entity.base.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * File Metadata Entity
 * Entity đại diện cho metadata của file
 * 
 * @author CuongHoang
 * @version 1.0
 */
@Entity
@Table(name = "file_metadata")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FileMetadata extends AuditableEntity {

    // ID inherited from BaseEntity

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String originalFileName;

    @Column(nullable = false)
    private String filePath;

    @Column(nullable = false)
    private String fileType;

    @Column(nullable = false)
    private String mimeType;

    @Column(nullable = false)
    private Long fileSize;

    private String fileUrl;
    private String thumbnailUrl;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String uploadedBy;
    private String category; // IMAGE, DOCUMENT, VIDEO, AUDIO, OTHER

    @Column(columnDefinition = "boolean default true")
    private Boolean active = true;

    private Integer downloadCount = 0;
    private Integer viewCount = 0;

    // Image specific fields
    private Integer width;
    private Integer height;
    private String resolution;

    // Document specific fields
    private Integer pageCount;
    private String documentType;

    // Video/Audio specific fields
    private Integer duration; // in seconds
    private String codec;
    private Integer bitrate;

    @Column(columnDefinition = "TEXT")
    private String metadata; // JSON string for additional metadata
}
