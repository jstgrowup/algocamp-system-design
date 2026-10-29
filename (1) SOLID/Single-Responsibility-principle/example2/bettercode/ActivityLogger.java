package solid.srp.example2.better;

/**
 * Single responsibility: logging user activity.
 * Changes only when the logging requirement changes.
 */
public class ActivityLogger {

    public void activityLogger(User user) {
        System.out.println("logging");
    }
}
