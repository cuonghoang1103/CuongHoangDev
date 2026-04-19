package dev.cuonghoang.util.helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Date Helper
 * Utility class cho xử lý ngày tháng
 * 
 * @author CuongHoang
 * @version 1.0
 */
public class DateHelper {

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DISPLAY_DATE_FORMAT = "dd/MM/yyyy";
    public static final String DISPLAY_DATETIME_FORMAT = "dd/MM/yyyy HH:mm";

    /**
     * Format LocalDateTime to string
     */
    public static String formatDateTime(LocalDateTime dateTime, String pattern) {
        if (dateTime == null) return "";
        return dateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * Format LocalDateTime with default pattern
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        return formatDateTime(dateTime, DEFAULT_DATETIME_FORMAT);
    }

    /**
     * Format LocalDateTime for display
     */
    public static String formatDateTimeForDisplay(LocalDateTime dateTime) {
        return formatDateTime(dateTime, DISPLAY_DATETIME_FORMAT);
    }

    /**
     * Get time ago string
     */
    public static String getTimeAgo(LocalDateTime dateTime) {
        if (dateTime == null) return "";
        
        LocalDateTime now = LocalDateTime.now();
        long minutes = ChronoUnit.MINUTES.between(dateTime, now);
        long hours = ChronoUnit.HOURS.between(dateTime, now);
        long days = ChronoUnit.DAYS.between(dateTime, now);
        long months = ChronoUnit.MONTHS.between(dateTime, now);
        long years = ChronoUnit.YEARS.between(dateTime, now);

        if (minutes < 1) {
            return "Just now";
        } else if (minutes < 60) {
            return minutes + " minute" + (minutes > 1 ? "s" : "") + " ago";
        } else if (hours < 24) {
            return hours + " hour" + (hours > 1 ? "s" : "") + " ago";
        } else if (days < 30) {
            return days + " day" + (days > 1 ? "s" : "") + " ago";
        } else if (months < 12) {
            return months + " month" + (months > 1 ? "s" : "") + " ago";
        } else {
            return years + " year" + (years > 1 ? "s" : "") + " ago";
        }
    }

    /**
     * Check if date is today
     */
    public static boolean isToday(LocalDateTime dateTime) {
        if (dateTime == null) return false;
        return dateTime.toLocalDate().equals(LocalDateTime.now().toLocalDate());
    }

    /**
     * Check if date is this week
     */
    public static boolean isThisWeek(LocalDateTime dateTime) {
        if (dateTime == null) return false;
        LocalDateTime now = LocalDateTime.now();
        return ChronoUnit.DAYS.between(dateTime, now) < 7;
    }

    /**
     * Get current timestamp
     */
    public static String getCurrentTimestamp() {
        return formatDateTime(LocalDateTime.now());
    }

    private DateHelper() {
        // Private constructor to prevent instantiation
    }
}
