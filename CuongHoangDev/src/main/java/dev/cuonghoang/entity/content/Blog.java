package dev.cuonghoang.entity.content;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import dev.cuonghoang.entity.base.AuditableEntity;
import dev.cuonghoang.entity.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * Blog Entity
 * Entity đại diện cho bài viết blog
 * 
 * @author CuongHoang
 * @since 1.0.0
 */
@Entity
@Table(name = "blogs", indexes = {
        @Index(name = "idx_blog_slug", columnList = "slug", unique = true),
        @Index(name = "idx_blog_status", columnList = "status"),
        @Index(name = "idx_blog_published_at", columnList = "published_at"),
        @Index(name = "idx_blog_author_id", columnList = "author_id"),
        @Index(name = "idx_blog_category", columnList = "category"),
        @Index(name = "idx_blog_featured", columnList = "featured")
})
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = { "author", "tags" })
@ToString(exclude = { "author", "content" })
public class Blog extends AuditableEntity {

    /**
     * Blog title
     */
    @Column(name = "title", nullable = false, length = 255)
    private String title;

    /**
     * Blog slug (URL-friendly)
     */
    @Column(name = "slug", nullable = false, unique = true, length = 255)
    private String slug;

    /**
     * Blog excerpt/summary
     */
    @Column(name = "excerpt", length = 500)
    private String excerpt;

    /**
     * Blog content (HTML)
     */
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    /**
     * Featured image URL
     */
    @Column(name = "featured_image", length = 500)
    private String featuredImage;

    /**
     * Blog category
     */
    @Column(name = "category", length = 100)
    private String category;

    /**
     * Blog tags (comma-separated)
     */
    @Column(name = "tags", length = 500)
    private String tags;

    /**
     * Blog status (DRAFT, PUBLISHED, ARCHIVED)
     */
    @Column(name = "status", nullable = false, length = 20)
    @lombok.Builder.Default
    private String status = "DRAFT";

    /**
     * Whether blog is featured
     */
    @Column(name = "featured", nullable = false)
    @lombok.Builder.Default
    private Boolean featured = false;

    /**
     * Whether comments are allowed
     */
    @Column(name = "allow_comments", nullable = false)
    @lombok.Builder.Default
    private Boolean allowComments = true;

    /**
     * Published date
     */
    @Column(name = "published_at")
    private LocalDateTime publishedAt;

    /**
     * View count
     */
    @Column(name = "view_count")
    @lombok.Builder.Default
    private Long viewCount = 0L;

    /**
     * Like count
     */
    @Column(name = "like_count")
    @lombok.Builder.Default
    private Long likeCount = 0L;

    /**
     * Comment count
     */
    @Column(name = "comment_count")
    @lombok.Builder.Default
    private Long commentCount = 0L;

    /**
     * Share count
     */
    @Column(name = "share_count")
    @lombok.Builder.Default
    private Long shareCount = 0L;

    /**
     * Reading time in minutes
     */
    @Column(name = "reading_time")
    private Integer readingTime;

    /**
     * SEO meta title
     */
    @Column(name = "meta_title", length = 255)
    private String metaTitle;

    /**
     * SEO meta description
     */
    @Column(name = "meta_description", length = 500)
    private String metaDescription;

    /**
     * SEO meta keywords
     */
    @Column(name = "meta_keywords", length = 500)
    private String metaKeywords;

    /**
     * Blog author
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    /**
     * Check if blog is published
     */
    @Transient
    public boolean isPublished() {
        return "PUBLISHED".equals(status) && publishedAt != null &&
                publishedAt.isBefore(LocalDateTime.now());
    }

    /**
     * Check if blog is draft
     */
    @Transient
    public boolean isDraft() {
        return "DRAFT".equals(status);
    }

    /**
     * Check if blog is archived
     */
    @Transient
    public boolean isArchived() {
        return "ARCHIVED".equals(status);
    }

    /**
     * Publish blog
     */
    public void publish() {
        this.status = "PUBLISHED";
        if (this.publishedAt == null) {
            this.publishedAt = LocalDateTime.now();
        }
    }

    /**
     * Archive blog
     */
    public void archive() {
        this.status = "ARCHIVED";
    }

    /**
     * Set as draft
     */
    public void setAsDraft() {
        this.status = "DRAFT";
        this.publishedAt = null;
    }

    /**
     * Increment view count
     */
    public void incrementViewCount() {
        this.viewCount++;
    }

    /**
     * Increment like count
     */
    public void incrementLikeCount() {
        this.likeCount++;
    }

    /**
     * Decrement like count
     */
    public void decrementLikeCount() {
        if (this.likeCount > 0) {
            this.likeCount--;
        }
    }

    /**
     * Increment comment count
     */
    public void incrementCommentCount() {
        this.commentCount++;
    }

    /**
     * Decrement comment count
     */
    public void decrementCommentCount() {
        if (this.commentCount > 0) {
            this.commentCount--;
        }
    }

    /**
     * Increment share count
     */
    public void incrementShareCount() {
        this.shareCount++;
    }

    /**
     * Get tags as set
     */
    @Transient
    public Set<String> getTagsAsSet() {
        Set<String> tagSet = new HashSet<>();
        if (tags != null && !tags.isEmpty()) {
            String[] tagArray = tags.split(",");
            for (String tag : tagArray) {
                tagSet.add(tag.trim());
            }
        }
        return tagSet;
    }

    /**
     * Set tags from set
     */
    public void setTagsFromSet(Set<String> tagSet) {
        if (tagSet != null && !tagSet.isEmpty()) {
            this.tags = String.join(",", tagSet);
        } else {
            this.tags = null;
        }
    }

    /**
     * Calculate reading time based on content
     */
    public void calculateReadingTime() {
        if (content != null && !content.isEmpty()) {
            // Remove HTML tags for word count
            String plainText = content.replaceAll("<[^>]*>", "");
            String[] words = plainText.split("\\s+");
            int wordCount = words.length;

            // Average reading speed: 200 words per minute
            this.readingTime = Math.max(1, (int) Math.ceil(wordCount / 200.0));
        } else {
            this.readingTime = 1;
        }
    }

    /**
     * Get formatted reading time
     */
    @Transient
    public String getFormattedReadingTime() {
        if (readingTime == null || readingTime <= 0) {
            return "1 phút đọc";
        }
        return readingTime + " phút đọc";
    }
}
