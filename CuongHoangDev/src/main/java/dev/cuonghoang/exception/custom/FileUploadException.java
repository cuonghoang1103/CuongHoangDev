package dev.cuonghoang.exception.custom;

/**
 * File Upload Exception
 * Exception khi upload file thất bại
 * 
 * @author CuongHoang
 * @version 1.0
 */
public class FileUploadException extends RuntimeException {

    public FileUploadException(String message) {
        super(message);
    }

    public FileUploadException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileUploadException(String fileName, String reason) {
        super("Failed to upload file '" + fileName + "': " + reason);
    }
}
