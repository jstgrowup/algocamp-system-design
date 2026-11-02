package single_responsibility.example2.better;

/**
 * Single responsibility: logging user activity.
 * Changes only when the logging requirement changes.
 */
public class ActivityLogger {

    public void logUserActivity(User user) {
        System.out.println("User activity logged");
    }
}
