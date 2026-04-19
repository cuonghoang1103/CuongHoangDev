package dev.cuonghoang.util.validator;

import java.util.regex.Pattern;

/**
 * Email Validator
 * Utility class cho validation email
 * 
 * @author CuongHoang
 * @version 1.0
 */
public class EmailValidator {

    private static final String EMAIL_PATTERN = 
        "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
        "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    /**
     * Validate email format
     */
    public static boolean isValid(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        return pattern.matcher(email).matches();
    }

    /**
     * Validate email and check length
     */
    public static boolean isValidWithLength(String email, int maxLength) {
        return isValid(email) && email.length() <= maxLength;
    }

    /**
     * Check if email domain is allowed
     */
    public static boolean isAllowedDomain(String email, String[] allowedDomains) {
        if (!isValid(email)) {
            return false;
        }

        String domain = email.substring(email.indexOf("@") + 1).toLowerCase();
        for (String allowedDomain : allowedDomains) {
            if (domain.equals(allowedDomain.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if email domain is blocked
     */
    public static boolean isBlockedDomain(String email, String[] blockedDomains) {
        if (!isValid(email)) {
            return false;
        }

        String domain = email.substring(email.indexOf("@") + 1).toLowerCase();
        for (String blockedDomain : blockedDomains) {
            if (domain.equals(blockedDomain.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Extract domain from email
     */
    public static String extractDomain(String email) {
        if (!isValid(email)) {
            return "";
        }
        return email.substring(email.indexOf("@") + 1);
    }

    private EmailValidator() {
        // Private constructor to prevent instantiation
    }
}
