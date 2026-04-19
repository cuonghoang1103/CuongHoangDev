package dev.cuonghoang.util.helper;

import java.text.Normalizer;
import java.util.regex.Pattern;

/**
 * String Helper
 * Utility class cho xử lý chuỗi
 * 
 * @author CuongHoang
 * @version 1.0
 */
public class StringHelper {

    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    /**
     * Generate slug from string
     */
    public static String generateSlug(String input) {
        if (input == null || input.trim().isEmpty()) {
            return "";
        }

        String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase().replaceAll("-{2,}", "-").replaceAll("^-|-$", "");
    }

    /**
     * Truncate string with ellipsis
     */
    public static String truncate(String input, int maxLength) {
        if (input == null || input.length() <= maxLength) {
            return input;
        }
        return input.substring(0, maxLength - 3) + "...";
    }

    /**
     * Capitalize first letter
     */
    public static String capitalize(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }

    /**
     * Convert to title case
     */
    public static String toTitleCase(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        StringBuilder titleCase = new StringBuilder();
        boolean nextTitleCase = true;

        for (char c : input.toCharArray()) {
            if (Character.isSpaceChar(c)) {
                nextTitleCase = true;
            } else if (nextTitleCase) {
                c = Character.toTitleCase(c);
                nextTitleCase = false;
            } else {
                c = Character.toLowerCase(c);
            }
            titleCase.append(c);
        }

        return titleCase.toString();
    }

    /**
     * Check if string is null or empty
     */
    public static boolean isNullOrEmpty(String input) {
        return input == null || input.trim().isEmpty();
    }

    /**
     * Check if string is not null or empty
     */
    public static boolean isNotNullOrEmpty(String input) {
        return !isNullOrEmpty(input);
    }

    /**
     * Generate random string
     */
    public static String generateRandomString(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            result.append(chars.charAt((int) (Math.random() * chars.length())));
        }
        return result.toString();
    }

    /**
     * Mask email for privacy
     */
    public static String maskEmail(String email) {
        if (isNullOrEmpty(email) || !email.contains("@")) {
            return email;
        }

        String[] parts = email.split("@");
        String username = parts[0];
        String domain = parts[1];

        if (username.length() <= 2) {
            return email;
        }

        String maskedUsername = username.charAt(0) + "*".repeat(username.length() - 2) + username.charAt(username.length() - 1);
        return maskedUsername + "@" + domain;
    }

    private StringHelper() {
        // Private constructor to prevent instantiation
    }
}
