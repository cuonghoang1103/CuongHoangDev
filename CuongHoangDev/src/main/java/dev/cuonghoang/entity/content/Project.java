package dev.cuonghoang.entity.content;

import java.util.List;

import dev.cuonghoang.entity.base.AuditableEntity;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Project Entity
 * Entity đại diện cho dự án
 * 
 * @author CuongHoang
 * @version 1.0
 */
@Entity
@Table(name = "projects")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Project extends AuditableEntity {

    // ID inherited from BaseEntity

    @Column(nullable = false)
    private String title;

    @Column(unique = true, nullable = false)
    private String slug;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String category;

    @ElementCollection
    @CollectionTable(name = "project_technologies", joinColumns = @JoinColumn(name = "project_id"))
    @Column(name = "technology")
    private List<String> technologies;

    private String githubUrl;
    private String liveUrl;
    private String imageUrl;

    @ElementCollection
    @CollectionTable(name = "project_images", joinColumns = @JoinColumn(name = "project_id"))
    @Column(name = "image_url")
    private List<String> images;

    private String status; // COMPLETED, IN_PROGRESS, PLANNED

    @Column(columnDefinition = "boolean default false")
    private Boolean featured = false;

    private Integer viewCount = 0;
}
