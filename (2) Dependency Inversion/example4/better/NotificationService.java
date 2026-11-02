package example4.better;

/**
 * FIX: the service depends on the MessageSender abstraction, and the sender is
 * handed in through the constructor (constructor injection).
 *
 * The service no longer says `new` for any concrete sender. Whoever creates the
 * service (here: Main, in a real app: a DI container or a factory) chooses it.
 */
public class NotificationService {

    private final MessageSender sender;

    public NotificationService(MessageSender sender) {
        this.sender = sender;
    }

    public void notifyUser(String user, String message) {
        if (message == null || message.isBlank()) {
            throw new IllegalArgumentException("Message must not be empty");
        }
        sender.send(user, message);
    }
}
