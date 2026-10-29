package solid.ocp.example4.better;

/** One concrete notification type. Adding a new type means adding a class like this one. */
public class EmailNotification implements Notification {

    @Override
    public void sendMessage(String message) {
        System.out.println("Email notification: " + message);
    }
}
