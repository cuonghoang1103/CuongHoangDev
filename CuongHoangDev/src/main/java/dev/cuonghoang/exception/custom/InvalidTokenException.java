package dev.cuonghoang.exception.custom;

/**
 * Invalid Token Exception
 * Exception khi token không hợp lệ
 * 
 * @author CuongHoang
 * @version 1.0
 */
public class InvalidTokenException extends RuntimeException {

    public InvalidTokenException(String message) {
        super(message);
    }

    public InvalidTokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidTokenException() {
        super("Invalid or expired token");
    }
}
