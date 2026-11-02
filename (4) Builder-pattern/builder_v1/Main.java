package builder_v1;

public class Main {
    public static void main(String[] args) {
        // 1. Each setter validates, so a bad value is rejected.
        User a = new User();
        try {
            a.setFirstName("");
        } catch (IllegalArgumentException e) {
            System.out.println("rejected: " + e.getMessage());
        }

        // 2. But the object already exists, and it is invalid right now:
        System.out.println("a.getFirstName() = " + a.getFirstName() + ", a.getAge() = " + a.getAge());
        // null and 0: nothing forced us to call the setters, so nothing was validated.

        // 3. Rules that combine fields depend on the ORDER of the setter calls.
        User b = new User();
        b.setPassword("aditya123");   // passes: firstName is not set yet, so the check is skipped
        b.setFirstName("Aditya");     // now the password contains the first name, and nobody re-checks
        System.out.println("b ended up with firstName=" + b.getFirstName()
                + " and password=" + b.getPassword() + " (rule broken, no error)");
    }
}
