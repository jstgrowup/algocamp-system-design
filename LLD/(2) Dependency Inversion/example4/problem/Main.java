package example4.problem;

/** Runs the PROBLEM version: the service can only ever send email. */
public class Main {

    public static void main(String[] args) {
        NotificationService service = new NotificationService();
        service.notifyUser("asha@example.com", "Your order has shipped");

        // No way to use SMS, or to capture the message in a test,
        // without editing NotificationService.
    }
}
