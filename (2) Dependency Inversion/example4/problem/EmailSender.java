package example4.problem;

/** Low-level detail: knows how to talk to an email provider. */
public class EmailSender {

    public void send(String to, String message) {
        // In real code: SMTP / SendGrid call
        System.out.println("EMAIL to " + to + ": " + message);
    }
}
