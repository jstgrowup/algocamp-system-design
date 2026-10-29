package solid.srp.example4.problem;

/** The kinds of notification we can send. */
enum NotificationType {
    SMS,
    EMAIL,
    PUSH,
    WHATSAPP
}

/**
 * One class with a method for every channel.
 *
 * You might think splitting notifications by service is a good idea (one class
 * for SMS, one for email). The issue is in NotificationSender: it becomes a lot
 * of if/else, and if we want to get rid of a channel tomorrow we have to come
 * here and remove that method too. Segregating the classes this way is not the
 * best solution either. Here comes the Open/Closed Principle.
 */
public class Notification {

    public void sendSMSNotification(String message) {
        System.out.println("MEssage " + message);
    }

    public void sendEmailNotification(String message) {
        System.out.println("Email Notification " + message);
    }

    public void sendPushNotification(String message) {
        System.out.println("Push " + message);
    }

    public void sendWhatsAppNotification(String message) {
        System.out.println("message " + message);
    }
}
