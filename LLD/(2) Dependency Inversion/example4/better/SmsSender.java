package example4.better;

/** A new channel: added without touching NotificationService. */
public class SmsSender implements MessageSender {

    @Override
    public void send(String to, String message) {
        System.out.println("SMS to " + to + ": " + message);
    }
}
