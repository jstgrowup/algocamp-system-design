package example4.better;

/** Low-level detail, now depends on the abstraction by implementing it. */
public class EmailSender implements MessageSender {

    @Override
    public void send(String to, String message) {
        System.out.println("EMAIL to " + to + ": " + message);
    }
}
