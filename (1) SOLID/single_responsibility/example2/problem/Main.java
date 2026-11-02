package single_responsibility.example2.problem;

/** Runner: UserManager both manages users and logs activity (two jobs in one class). */
public class Main {

    public static void main(String[] args) {
        UserManager manager = new UserManager();
        User adult = new User(30);

        manager.add(adult);
        manager.logUserActivity(adult); // not the manager's job -> SRP violation
        try {
            manager.add(new User(15));
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
    }
}
