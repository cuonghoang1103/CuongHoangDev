package dev.cuonghoang.util.constants;

/**
 * Application Constants
 * Các hằng số của ứng dụng
 * 
 * @author CuongHoang
 * @since 1.0.0
 */
public final class AppConstants {

    private AppConstants() {
        // Utility class
    }

    // ===== ROLES =====
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_MODERATOR = "ROLE_MODERATOR";

    // ===== AUTHORITIES =====
    public static final String AUTHORITY_ADMIN = "ADMIN";
    public static final String AUTHORITY_USER = "USER";
    public static final String AUTHORITY_MODERATOR = "MODERATOR";

    // ===== DEFAULT VALUES =====
    public static final String DEFAULT_PAGE_NUMBER = "0";
    public static final String DEFAULT_PAGE_SIZE = "10";
    public static final String DEFAULT_SORT_BY = "createdAt";
    public static final String DEFAULT_SORT_DIRECTION = "desc";

    // ===== PAGINATION =====
    public static final int MAX_PAGE_SIZE = 100;
    public static final int MIN_PAGE_SIZE = 1;

    // ===== FILE UPLOAD =====
    public static final long MAX_FILE_SIZE = 10 * 1024 * 1024; // 10MB
    public static final String[] ALLOWED_IMAGE_TYPES = {"jpg", "jpeg", "png", "gif", "webp"};
    public static final String[] ALLOWED_DOCUMENT_TYPES = {"pdf", "doc", "docx", "txt", "rtf"};
    public static final String[] ALLOWED_AUDIO_TYPES = {"mp3", "wav", "ogg", "m4a"};
    public static final String[] ALLOWED_VIDEO_TYPES = {"mp4", "avi", "mov", "wmv"};

    // ===== CACHE NAMES =====
    public static final String CACHE_USERS = "users";
    public static final String CACHE_ROLES = "roles";
    public static final String CACHE_BLOGS = "blogs";
    public static final String CACHE_PROJECTS = "projects";
    public static final String CACHE_PRODUCTS = "products";

    // ===== DATE FORMATS =====
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String TIME_FORMAT = "HH:mm:ss";

    // ===== VALIDATION =====
    public static final int MIN_PASSWORD_LENGTH = 6;
    public static final int MAX_PASSWORD_LENGTH = 100;
    public static final int MIN_USERNAME_LENGTH = 3;
    public static final int MAX_USERNAME_LENGTH = 50;
    public static final int MAX_EMAIL_LENGTH = 100;
    public static final int MAX_NAME_LENGTH = 100;
    public static final int MAX_DESCRIPTION_LENGTH = 1000;
    public static final int MAX_CONTENT_LENGTH = 10000;

    // ===== STATUS =====
    public static final String STATUS_ACTIVE = "ACTIVE";
    public static final String STATUS_INACTIVE = "INACTIVE";
    public static final String STATUS_PENDING = "PENDING";
    public static final String STATUS_APPROVED = "APPROVED";
    public static final String STATUS_REJECTED = "REJECTED";
    public static final String STATUS_DRAFT = "DRAFT";
    public static final String STATUS_PUBLISHED = "PUBLISHED";
    public static final String STATUS_ARCHIVED = "ARCHIVED";

    // ===== OAUTH2 PROVIDERS =====
    public static final String OAUTH2_GOOGLE = "google";
    public static final String OAUTH2_GITHUB = "github";
    public static final String OAUTH2_FACEBOOK = "facebook";

    // ===== EMAIL TEMPLATES =====
    public static final String EMAIL_WELCOME = "welcome";
    public static final String EMAIL_PASSWORD_RESET = "password-reset";
    public static final String EMAIL_CONTACT_CONFIRMATION = "contact-confirmation";
    public static final String EMAIL_NOTIFICATION = "notification";

    // ===== NOTIFICATION TYPES =====
    public static final String NOTIFICATION_INFO = "INFO";
    public static final String NOTIFICATION_SUCCESS = "SUCCESS";
    public static final String NOTIFICATION_WARNING = "WARNING";
    public static final String NOTIFICATION_ERROR = "ERROR";

    // ===== CONTENT TYPES =====
    public static final String CONTENT_TYPE_BLOG = "BLOG";
    public static final String CONTENT_TYPE_PROJECT = "PROJECT";
    public static final String CONTENT_TYPE_PRODUCT = "PRODUCT";
    public static final String CONTENT_TYPE_GAME = "GAME";

    // ===== PRIVACY LEVELS =====
    public static final String PRIVACY_PUBLIC = "PUBLIC";
    public static final String PRIVACY_PRIVATE = "PRIVATE";
    public static final String PRIVACY_FRIENDS = "FRIENDS";
    public static final String PRIVACY_UNLISTED = "UNLISTED";

    // ===== API ENDPOINTS =====
    public static final String API_V1 = "/api/v1";
    public static final String API_AUTH = API_V1 + "/auth";
    public static final String API_USER = API_V1 + "/user";
    public static final String API_ADMIN = API_V1 + "/admin";
    public static final String API_PUBLIC = API_V1 + "/public";

    // ===== MESSAGES =====
    public static final String MSG_SUCCESS = "Operation completed successfully";
    public static final String MSG_ERROR = "An error occurred";
    public static final String MSG_NOT_FOUND = "Resource not found";
    public static final String MSG_UNAUTHORIZED = "Unauthorized access";
    public static final String MSG_FORBIDDEN = "Access forbidden";
    public static final String MSG_VALIDATION_ERROR = "Validation error";
    public static final String MSG_DUPLICATE_ERROR = "Resource already exists";

    // ===== REGEX PATTERNS =====
    public static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+\\.[A-Za-z]{2,})$";
    public static final String PHONE_PATTERN = "^[+]?[0-9]{10,15}$";
    public static final String USERNAME_PATTERN = "^[a-zA-Z0-9._-]{3,50}$";
    public static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d@$!%*?&]{6,}$";

    // ===== LOCALES =====
    public static final String LOCALE_VI = "vi";
    public static final String LOCALE_EN = "en";
    public static final String DEFAULT_LOCALE = LOCALE_VI;

    // ===== THEMES =====
    public static final String THEME_LIGHT = "light";
    public static final String THEME_DARK = "dark";
    public static final String DEFAULT_THEME = THEME_LIGHT;
}
