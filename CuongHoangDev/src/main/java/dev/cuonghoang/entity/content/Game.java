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
 * Game Entity
 * Entity đại diện cho game
 * 
 * @author CuongHoang
 * @version 1.0
 */
@Entity
@Table(name = "games")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Game extends AuditableEntity {

    // ID inherited from BaseEntity

    @Column(nullable = false)
    private String title;

    @Column(unique = true, nullable = false)
    private String slug;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String review;

    private String genre;

    @ElementCollection
    @CollectionTable(name = "game_platforms", joinColumns = @JoinColumn(name = "game_id"))
    @Column(name = "platform")
    private List<String> platforms;

    private String developer;
    private String publisher;
    private String releaseDate;

    private Double rating; // 0.0 to 10.0
    private String imageUrl;

    @ElementCollection
    @CollectionTable(name = "game_images", joinColumns = @JoinColumn(name = "game_id"))
    @Column(name = "image_url")
    private List<String> images;

    @Column(columnDefinition = "boolean default false")
    private Boolean featured = false;

    private Integer playTime; // in hours
    private String status; // COMPLETED, PLAYING, PLANNED, DROPPED

    @ElementCollection
    @CollectionTable(name = "game_tags", joinColumns = @JoinColumn(name = "game_id"))
    @Column(name = "tag")
    private List<String> tags;
}
