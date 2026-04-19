package dev.cuonghoang.util.validator;

import java.util.regex.Pattern;

/**
 * Password Validator
 * Utility class cho validation mật khẩu
 * 
 * @author CuongHoang
 * @version 1.0
 */
public class PasswordValidator {

    private static final int MIN_LENGTH = 8;
    private static final int MAX_LENGTH = 128;

    // At least one digit, one lowercase, one uppercase, one special character
    private static final String STRONG_PASSWORD_PATTERN = 
        "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";

    // At least one digit and one letter
    private static final String MEDIUM_PASSWORD_PATTERN = 
        "^(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$).{8,}$";

    private static final Pattern strongPattern = Pattern.compile(STRONG_PASSWORD_PATTERN);
    private static final Pattern mediumPattern = Pattern.compile(MEDIUM_PASSWORD_PATTERN);

    /**
     * Check if password meets minimum requirements
     */
    public static boolean isValid(String password) {
        if (password == null) {
            return false;
        }
        return password.length() >= MIN_LENGTH && password.length() <= MAX_LENGTH;
    }

    /**
     * Check if password is strong
     */
    public static boolean isStrong(String password) {
        if (!isValid(password)) {
            return false;
        }
        return strongPattern.matcher(password).matches();
    }

    /**
     * Check if password is medium strength
     */
    public static boolean isMedium(String password) {
        if (!isValid(password)) {
            return false;
        }
        return mediumPattern.matcher(password).matches();
    }

    /**
     * Get password strength level
     */
    public static PasswordStrength getStrength(String password) {
        if (!isValid(password)) {
            return PasswordStrength.INVALID;
        }
        
        if (isStrong(password)) {
            return PasswordStrength.STRONG;
        } else if (isMedium(password)) {
            return PasswordStrength.MEDIUM;
        } else {
            return PasswordStrength.WEAK;
        }
    }

    /**
     * Check if password contains common patterns
     */
    public static boolean hasCommonPatterns(String password) {
        if (password == null) {
            return false;
        }
        
        String lowerPassword = password.toLowerCase();
        String[] commonPatterns = {
            "123456", "password", "admin", "qwerty", "abc123", 
            "letmein", "welcome", "monkey", "dragon", "master"
        };
        
        for (String pattern : commonPatterns) {
            if (lowerPassword.contains(pattern)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if password contains user information
     */
    public static boolean containsUserInfo(String password, String username, String email) {
        if (password == null) {
            return false;
        }
        
        String lowerPassword = password.toLowerCase();
        
        if (username != null && lowerPassword.contains(username.toLowerCase())) {
            return true;
        }
        
        if (email != null) {
            String emailUsername = email.split("@")[0];
            if (lowerPassword.contains(emailUsername.toLowerCase())) {
                return true;
            }
        }
        
        return false;
    }

    public enum PasswordStrength {
        INVALID, WEAK, MEDIUM, STRONG
    }

    private PasswordValidator() {
        // Private constructor to prevent instantiation
    }
}
