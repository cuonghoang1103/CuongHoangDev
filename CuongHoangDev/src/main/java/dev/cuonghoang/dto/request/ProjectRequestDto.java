package dev.cuonghoang.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Project Request DTO
 * DTO for project creation and update requests
 * 
 * @author CuongHoang
 * @version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectRequestDto {

    @NotBlank(message = "Title is required")
    @Size(max = 200, message = "Title must not exceed 200 characters")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @Size(max = 500, message = "Summary must not exceed 500 characters")
    private String summary;

    @Size(max = 200, message = "Featured image URL must not exceed 200 characters")
    private String featuredImage;

    @Size(max = 500, message = "Technologies must not exceed 500 characters")
    private String technologies;

    @Size(max = 200, message = "GitHub URL must not exceed 200 characters")
    private String githubUrl;

    @Size(max = 200, message = "Live demo URL must not exceed 200 characters")
    private String liveDemoUrl;

    @Size(max = 100, message = "Status must not exceed 100 characters")
    private String status;

    @Size(max = 100, message = "Category must not exceed 100 characters")
    private String category;

    private Boolean featured = false;

    private Integer priority = 0;
}
