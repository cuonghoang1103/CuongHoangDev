package dev.cuonghoang.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * API Response DTO
 * DTO cho response API chung
 * 
 * @author CuongHoang
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseDto<T> {

    private boolean success;
    private String message;
    private T data;
    private String timestamp;

    public ApiResponseDto(boolean success, String message) {
        this.success = success;
        this.message = message;
        this.timestamp = java.time.Instant.now().toString();
    }

    public ApiResponseDto(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.timestamp = java.time.Instant.now().toString();
    }

    public static <T> ApiResponseDto<T> success(String message) {
        return new ApiResponseDto<>(true, message);
    }

    public static <T> ApiResponseDto<T> success(String message, T data) {
        return new ApiResponseDto<>(true, message, data);
    }

    public static <T> ApiResponseDto<T> error(String message) {
        return new ApiResponseDto<>(false, message);
    }

    public static <T> ApiResponseDto<T> error(String message, T data) {
        return new ApiResponseDto<>(false, message, data);
    }
}
