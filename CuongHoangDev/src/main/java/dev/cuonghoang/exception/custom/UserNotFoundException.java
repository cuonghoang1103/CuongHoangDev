package dev.cuonghoang.exception.custom;

/**
 * User Not Found Exception
 * Exception khi không tìm thấy người dùng
 * 
 * @author CuongHoang
 * @version 1.0
 */
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(Long userId) {
        super("User not found with id: " + userId);
    }

    public UserNotFoundException(String field, String value) {
        super("User not found with " + field + ": " + value);
    }
}
