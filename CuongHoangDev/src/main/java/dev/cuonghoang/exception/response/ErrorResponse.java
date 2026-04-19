package dev.cuonghoang.exception.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Error Response Class
 * Class đại diện cho response lỗi
 * 
 * @author CuongHoang
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

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

    public ErrorResponse(String error, String message, int status, String path) {
        this.error = error;
        this.message = message;
        this.status = status;
        this.path = path;
        this.timestamp = LocalDateTime.now();
    }

    public ErrorResponse(String error, String message, int status) {
        this.error = error;
        this.message = message;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }

    public static ErrorResponse of(String error, String message, int status, String path) {
        return new ErrorResponse(error, message, status, path);
    }

    public static ErrorResponse of(String error, String message, int status) {
        return new ErrorResponse(error, message, status);
    }

    public static ErrorResponse validationError(String message, Map<String, String> fieldErrors) {
        ErrorResponse response = new ErrorResponse("VALIDATION_ERROR", message, 400);
        response.setFieldErrors(fieldErrors);
        return response;
    }

    public static ErrorResponse multipleErrors(String message, List<String> errors) {
        ErrorResponse response = new ErrorResponse("MULTIPLE_ERRORS", message, 400);
        response.setErrors(errors);
        return response;
    }
}
