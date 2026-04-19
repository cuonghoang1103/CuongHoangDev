package dev.cuonghoang.entity.content;

import java.math.BigDecimal;
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
 * Product Entity
 * Entity đại diện cho sản phẩm
 * 
 * @author CuongHoang
 * @version 1.0
 */
@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Product extends AuditableEntity {

    // ID inherited from BaseEntity

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String slug;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String category;

    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    private String imageUrl;

    @ElementCollection
    @CollectionTable(name = "product_images", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "image_url")
    private List<String> images;

    @Column(columnDefinition = "boolean default true")
    private Boolean available = true;

    @Column(columnDefinition = "boolean default false")
    private Boolean featured = false;

    private Integer stock = 0;
    private Integer viewCount = 0;

    @ElementCollection
    @CollectionTable(name = "product_tags", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "tag")
    private List<String> tags;
}
