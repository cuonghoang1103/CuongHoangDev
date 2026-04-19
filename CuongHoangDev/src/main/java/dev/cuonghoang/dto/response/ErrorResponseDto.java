package dev.cuonghoang.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Error Response DTO
 * DTO cho response lỗi
 * 
 * @author CuongHoang
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseDto {

    private String error;
    private String message;
    private int status;
    private String path;
    private LocalDateTime timestamp;
    private String traceId;
    
    // For validation errors
    private Map<String, String> fieldErrors;
    
    // For multiple error messages
    private List<String> errors;
    
    // For additional error details
    private Map<String, Object> details;

    public ErrorResponseDto(String error, String message, int status, String path) {
        this.error = error;
        this.message = message;
        this.status = status;
        this.path = path;
        this.timestamp = LocalDateTime.now();
    }

    public ErrorResponseDto(String error, String message, int status) {
        this.error = error;
        this.message = message;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }

    public static ErrorResponseDto of(String error, String message, int status, String path) {
        return new ErrorResponseDto(error, message, status, path);
    }

    public static ErrorResponseDto of(String error, String message, int status) {
        return new ErrorResponseDto(error, message, status);
    }
}
