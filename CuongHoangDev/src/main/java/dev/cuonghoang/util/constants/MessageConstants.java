package dev.cuonghoang.util.constants;

/**
 * Message Constants
 * Các hằng số thông báo
 * 
 * @author CuongHoang
 * @version 1.0
 */
public class MessageConstants {

    // Success Messages
    public static final String USER_CREATED_SUCCESS = "User created successfully";
    public static final String USER_UPDATED_SUCCESS = "User updated successfully";
    public static final String USER_DELETED_SUCCESS = "User deleted successfully";
    public static final String LOGIN_SUCCESS = "Login successful";
    public static final String LOGOUT_SUCCESS = "Logout successful";
    public static final String PASSWORD_RESET_SUCCESS = "Password reset successfully";
    public static final String EMAIL_SENT_SUCCESS = "Email sent successfully";
    public static final String FILE_UPLOADED_SUCCESS = "File uploaded successfully";
    
    // Error Messages
    public static final String USER_NOT_FOUND = "User not found";
    public static final String INVALID_CREDENTIALS = "Invalid username or password";
    public static final String EMAIL_ALREADY_EXISTS = "Email already exists";
    public static final String USERNAME_ALREADY_EXISTS = "Username already exists";
    public static final String INVALID_TOKEN = "Invalid or expired token";
    public static final String ACCESS_DENIED = "Access denied";
    public static final String FILE_UPLOAD_ERROR = "File upload failed";
    public static final String FILE_TOO_LARGE = "File size exceeds maximum allowed size";
    public static final String INVALID_FILE_TYPE = "Invalid file type";
    
    // Validation Messages
    public static final String FIELD_REQUIRED = "This field is required";
    public static final String INVALID_EMAIL_FORMAT = "Invalid email format";
    public static final String PASSWORD_TOO_SHORT = "Password must be at least 8 characters";
    public static final String INVALID_PHONE_NUMBER = "Invalid phone number format";
    
    // Blog Messages
    public static final String BLOG_CREATED_SUCCESS = "Blog post created successfully";
    public static final String BLOG_UPDATED_SUCCESS = "Blog post updated successfully";
    public static final String BLOG_DELETED_SUCCESS = "Blog post deleted successfully";
    public static final String BLOG_NOT_FOUND = "Blog post not found";
    
    // Project Messages
    public static final String PROJECT_CREATED_SUCCESS = "Project created successfully";
    public static final String PROJECT_UPDATED_SUCCESS = "Project updated successfully";
    public static final String PROJECT_DELETED_SUCCESS = "Project deleted successfully";
    public static final String PROJECT_NOT_FOUND = "Project not found";
    
    // Contact Messages
    public static final String CONTACT_MESSAGE_SENT = "Your message has been sent successfully";
    public static final String CONTACT_MESSAGE_ERROR = "Failed to send message";
    
    private MessageConstants() {
        // Private constructor to prevent instantiation
    }
}
