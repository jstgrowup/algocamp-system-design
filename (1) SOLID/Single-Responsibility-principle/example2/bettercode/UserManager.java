package solid.srp.example2.better;

/**
 * BETTER: UserManager only manages users.
 * Logging moved to ActivityLogger, so each class has one reason to change.
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
