package single_responsibility.example2.better;

/** Runner: managing users and logging activity are now two separate classes. */
public class Main {

    public static void main(String[] args) {
        UserManager manager = new UserManager();
        ActivityLogger logger = new ActivityLogger();
        User adult = new User(30);

        manager.add(adult);
        logger.logUserActivity(adult);
        try {
            manager.add(new User(15));
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
    }
}
