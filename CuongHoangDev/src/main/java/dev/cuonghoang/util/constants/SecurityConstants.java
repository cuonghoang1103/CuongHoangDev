package dev.cuonghoang.util.constants;

/**
 * Security Constants
 * Các hằng số liên quan đến bảo mật
 * 
 * @author CuongHoang
 * @version 1.0
 */
public class SecurityConstants {

    // JWT Constants
    public static final String JWT_TOKEN_PREFIX = "Bearer ";
    public static final String JWT_HEADER_STRING = "Authorization";
    public static final String JWT_AUTHORITIES_KEY = "authorities";
    
    // Token Expiration
    public static final long JWT_EXPIRATION_TIME = 86400000; // 24 hours in milliseconds
    public static final long REFRESH_TOKEN_EXPIRATION_TIME = 604800000; // 7 days in milliseconds
    
    // Password Constants
    public static final int MIN_PASSWORD_LENGTH = 8;
    public static final int MAX_PASSWORD_LENGTH = 128;
    
    // Role Constants
    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_MODERATOR = "ROLE_MODERATOR";
    
    // OAuth2 Constants
    public static final String OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME = "oauth2_auth_request";
    public static final String REDIRECT_URI_PARAM_COOKIE_NAME = "redirect_uri";
    public static final int OAUTH2_COOKIE_EXPIRE_SECONDS = 180;
    
    // Security Headers
    public static final String CONTENT_SECURITY_POLICY = "default-src 'self'; script-src 'self' 'unsafe-inline'; style-src 'self' 'unsafe-inline'";
    
    private SecurityConstants() {
        // Private constructor to prevent instantiation
    }
}
