package example4.problem;

/**
 * PROBLEM: the high-level NotificationService creates its own low-level sender.
 *
 * Why this violates DIP
 *   The service holds the "what to notify" policy (the important part), yet it
 *   is welded to a concrete detail: `new EmailSender()`.
 *
 * What it costs you
 *   - Sending an SMS means editing NotificationService.
 *   - A unit test cannot pass a fake sender, so every test sends a real email.
 */
public class NotificationService {

    private final EmailSender sender = new EmailSender(); // hard-wired

    public void notifyUser(String user, String message) {
        if (message == null || message.isBlank()) {
            throw new IllegalArgumentException("Message must not be empty");
        }
        sender.send(user, message);
    }
}
