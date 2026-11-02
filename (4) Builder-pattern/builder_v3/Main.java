package builder_v3;

public class Main {
    public static void main(String[] args) {
        // GOOD: no more null for a missing company, there is a constructor for it.
        System.out.println("--- version 1: mandatory attributes only ---");
        User plain = new User("Riya", "Verma", 30, "riya@x.com", "p4ssword-ok");
        System.out.println("created " + plain.getFirstName() + ", company = " + plain.getCompany());

        System.out.println("\n--- version 2: with a company ---");
        User withCompany = new User("Aditya", "Sharma", 25, "aditya@x.com", "s3cret-pass", "Acme");
        System.out.println("created " + withCompany.getFirstName() + ", company = " + withCompany.getCompany());

        System.out.println("\n--- version 3: everything ---");
        User full = new User("Kabir", "Singh", 28, "kabir@x.com", "k4bir-pass-ok", "Acme", "9876543210");
        System.out.println("created " + full.getFirstName() + ", phone = " + full.getPhone());

        // Validation still works, and it lives in one constructor.
        System.out.println("\n--- validation (shared by all versions) ---");
        try {
            new User("Aditya", "Sharma", 25, "aditya@x.com", "aditya123");
        } catch (IllegalArgumentException e) {
            System.out.println("rejected -> " + e.getMessage());
        }
        try {
            new User("Aditya", "Sharma", 25, "aditya@x.com", "s3cret-pass", "Acme", "123");
        } catch (IllegalArgumentException e) {
            System.out.println("rejected -> " + e.getMessage());
        }

        // STILL WRONG 1: phone but no company. There is no 6-argument version for it (see
        // User.java, Version 4), so we must go back to passing null by hand.
        System.out.println("\n--- still wrong: phone without a company ---");
        User phoneOnly = new User("Meera", "Rao", 27, "meera@x.com", "m33ra-pass-ok", null, "9123456780");
        System.out.println("created " + phoneOnly.getFirstName() + ", company = " + phoneOnly.getCompany()
                + ", phone = " + phoneOnly.getPhone());

        // STILL WRONG 2: with 7 positional parameters the order problem is back.
        // Swapping company and phone is caught only because of the phone check; swapping
        // two Strings that both pass validation (first / last name) compiles and "works":
        User swapped = new User("Sharma", "Aditya", 25, "aditya@x.com", "s3cret-pass");
        System.out.println("firstName = " + swapped.getFirstName() + ", lastName = " + swapped.getLastName()
                + "  <- swapped, no error");

        // STILL WRONG 3: every new optional attribute (say, address) means more constructor
        // versions, and the combinations to support keep growing.
    }
}
