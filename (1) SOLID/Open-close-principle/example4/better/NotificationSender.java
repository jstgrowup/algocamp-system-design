package solid.ocp.example4.better;

import java.util.List;

/**
 * Depends on the Notification abstraction, not on concrete types.
 * Adding or removing a notification type never touches this class (OCP).
 */
public class NotificationSender {

    public void sendNotifications(List<Notification> notifications, String message) {
        for (Notification notification : notifications) {
            notification.sendMessage(message);
        }
    }
}
