package solid.srp.example2.problem;

/**
 * PROBLEM: UserManager also logs activity.
 *
 * add / delete / update / get are the "manage users" job.
 * logUserActivity is a different job (logging). If the logging needs to change,
 * we would have to edit the class that manages users. That is a second reason
 * to change, so SRP is violated.
 */
public class UserManager {

    public void add(User user) {
        if (user.getAge() < 18) {
            throw new IllegalArgumentException("User is not adult");
        }
        System.out.println("User added");
    }

    public void delete(User user) {
        System.out.println("User deleted");
    }

    public void update(User user) {
        if (user.getAge() < 18) {
            throw new IllegalArgumentException("User is not adult");
        }
        System.out.println("User updated");
    }

    public void get(User user) {
        System.out.println("User returned");
    }

    public void logUserActivity(User user) {
        // This is a violation of SRP
        System.out.println("User activity logged");
    }
}

/** Minimal stand-in for the User class the course code uses. */
class User {
    private final int age;

    User(int age) {
        this.age = age;
    }

    int getAge() {
        return age;
    }
}
