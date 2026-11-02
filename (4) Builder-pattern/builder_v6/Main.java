package builder_v6;

public class Main {
    public static void main(String[] args) {
        // The example from the readme: one expression, every value named, ending in build().
        User u = new UserBuilder()
                .age(27)
                .firstName("Sanket")
                .lastName("Singh")
                .email("sanket@x.com")
                .password("s3cret-pass")
                .build();
        System.out.println("created " + u.getFirstName() + " " + u.getLastName() + ", age " + u.getAge()
                + ", company = " + u.getCompany());

        // Optional attributes: add them to the chain only when we have them.
        User withOptional = new UserBuilder()
                .firstName("Aditya")
                .lastName("Sharma")
                .age(25)
                .email("aditya@x.com")
                .password("s3cret-pass")
                .company("Acme")
                .phone("9876543210")
                .build();
        System.out.println("created " + withOptional.getFirstName() + ", company = " + withOptional.getCompany()
                + ", phone = " + withOptional.getPhone());

        // The builder can still be kept in a variable and filled step by step,
        // since each call also works as a plain statement.
        UserBuilder b = new UserBuilder();
        b.firstName("Riya");
        b.lastName("Verma");
        b.age(30).email("riya@x.com").password("p4ssword-ok"); // chaining and statements can mix
        System.out.println("created " + b.build().getFirstName());

        // Validation still happens in each method, so a bad value stops the chain.
        try {
            new UserBuilder().firstName("Meera").age(15).build();
        } catch (IllegalArgumentException e) {
            System.out.println("rejected -> " + e.getMessage());
        }

        // Solution 6 does not stop this: the User constructor is still public.
        // User sneaky = new User(b);   // compiles; solution 7 is about preventing it
    }
}
