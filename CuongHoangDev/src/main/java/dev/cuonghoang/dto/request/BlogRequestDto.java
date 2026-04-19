package dev.cuonghoang.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Blog Request DTO
 * DTO for blog creation and update requests
 * 
 * @author CuongHoang
 * @version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlogRequestDto {

    @NotBlank(message = "Title is required")
    @Size(max = 200, message = "Title must not exceed 200 characters")
    private String title;

    @NotBlank(message = "Content is required")
    private String content;

    @Size(max = 500, message = "Summary must not exceed 500 characters")
    private String summary;

    @Size(max = 200, message = "Featured image URL must not exceed 200 characters")
    private String featuredImage;

    @Size(max = 100, message = "Category must not exceed 100 characters")
    private String category;

    @Size(max = 500, message = "Tags must not exceed 500 characters")
    private String tags;

    @Size(max = 200, message = "SEO title must not exceed 200 characters")
    private String seoTitle;

    @Size(max = 500, message = "SEO description must not exceed 500 characters")
    private String seoDescription;

    @Size(max = 500, message = "SEO keywords must not exceed 500 characters")
    private String seoKeywords;

    private Boolean published = false;

    private Boolean featured = false;
}
