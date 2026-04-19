package dev.cuonghoang.service.communication;

import org.springframework.stereotype.Service;

/**
 * Notification Service
 * Service xử lý logic thông báo
 * 
 * @author CuongHoang
 * @version 1.0
 */
@Service
public class NotificationService {

    // TODO: Implement notification service logic
    
    public void createNotification(Long userId, String title, String message) {
        // Implementation will be added later
    }
    
    public void sendNotification(Long notificationId) {
        // Implementation will be added later
    }
    
    public void markAsRead(Long notificationId) {
        // Implementation will be added later
    }
    
    public void markAllAsRead(Long userId) {
        // Implementation will be added later
    }
    
    public void deleteNotification(Long notificationId) {
        // Implementation will be added later
    }
    
    public void sendPushNotification(Long userId, String title, String message) {
        // Implementation will be added later
    }
    
    public void sendBrowserNotification(Long userId, String title, String message) {
        // Implementation will be added later
    }
    
    public void scheduleNotification(Long userId, String title, String message, String scheduleTime) {
        // Implementation will be added later
    }
}
