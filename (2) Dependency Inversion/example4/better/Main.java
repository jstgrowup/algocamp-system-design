package example4.better;

import java.util.ArrayList;
import java.util.List;

/** Runs the BETTER version: the caller chooses the sender. */
public class Main {

    public static void main(String[] args) {
        // 1. Email
        new NotificationService(new EmailSender())
                .notifyUser("asha@example.com", "Your order has shipped");

        // 2. Swap to SMS: NotificationService is untouched
        new NotificationService(new SmsSender())
                .notifyUser("+91-98765-43210", "Your order has shipped");

        // 3. Test: pass a fake sender (a lambda) that records messages instead of sending
        List<String> sent = new ArrayList<>();
        MessageSender fake = (to, message) -> sent.add(to + ":" + message);

        new NotificationService(fake).notifyUser("test-user", "hello");
        System.out.println("Test check, captured = " + sent);
        if (!sent.equals(List.of("test-user:hello"))) {
            throw new AssertionError("fake sender did not capture the message");
        }
    }
}
