package solid.srp.example4.problem;

import java.util.List;

/**
 * PROBLEM (violates OCP): this class is modified every time we introduce or
 * remove a type of notification, because the switch has to list every type.
 */
public class NotificationSender {

    private final Notification notification = new Notification();

    public void sendNotifications(List<NotificationType> types, String message) {
        for (NotificationType type : types) {
            switch (type) {
                case SMS:
                    notification.sendSMSNotification(message);
                    break;
                case EMAIL:
                    notification.sendEmailNotification(message);
                    break;
                case PUSH:
                    notification.sendPushNotification(message);
                    break;
                case WHATSAPP:
                    notification.sendWhatsAppNotification(message);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown notification type: " + type);
            }
        }
    }
}
