package builder_v2;

public class Main {
    public static void main(String[] args) {
        // WHAT SOLUTION 2 FIXES: invalid users can't be created at all.
        System.out.println("--- validation in the constructor works ---");
        User ok = new User("Aditya", "Sharma", 25, "aditya@x.com", "s3cret-pass", "Acme");
        System.out.println("created: " + ok.getFirstName() + " " + ok.getLastName() + ", company " + ok.getCompany());

        tryCreate("empty first name", "", "Sharma", 25, "aditya@x.com", "s3cret-pass", "Acme");
        tryCreate("underage", "Aditya", "Sharma", 15, "aditya@x.com", "s3cret-pass", "Acme");
        tryCreate("password has first name", "Aditya", "Sharma", 25, "aditya@x.com", "aditya123", "Acme");
        tryCreate("invalid company", "Aditya", "Sharma", 25, "aditya@x.com", "s3cret-pass", "");

        // PROBLEM 3: a user with no company still has to pass null by hand.
        System.out.println("\n--- problem 3: passing null for what we don't need ---");
        User noCompany = new User("Riya", "Verma", 30, "riya@x.com", "p4ssword-ok", null);
        System.out.println("created without a company: company = " + noCompany.getCompany());

        // PROBLEM 1: parameter order. Both names are Strings, so swapping them compiles
        // and "works", and nothing tells us it is wrong.
        System.out.println("\n--- problem 1: parameter order ---");
        User swapped = new User("Sharma", "Aditya", 25, "aditya@x.com", "s3cret-pass", "Acme");
        System.out.println("firstName = " + swapped.getFirstName() + ", lastName = " + swapped.getLastName()
                + "  <- swapped, no error");
        // Reading the call site is also hard: which String is the email? which is the password?
        //     new User("Aditya", "Sharma", 25, "aditya@x.com", "s3cret-pass", "Acme")
        // And if we add one more parameter tomorrow, EVERY call like the ones above must change.

        // PROBLEM 2 is inside User.java: the `company` check is an extra block in the
        // constructor, and every new optional attribute adds another one.
    }

    static void tryCreate(String label, String first, String last, int age, String email, String password, String company) {
        try {
            new User(first, last, age, email, password, company);
            System.out.println(label + ": created (unexpected)");
        } catch (IllegalArgumentException e) {
            System.out.println(label + ": rejected -> " + e.getMessage());
        }
    }
}
