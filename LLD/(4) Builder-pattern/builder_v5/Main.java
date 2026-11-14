package builder_v5;

public class Main {
    public static void main(String[] args) {
        // The flow from the readme: set the values on the builder, then ask it for the User.
        UserBuilder b = new UserBuilder();
        b.setFirstName("Sanket");
        b.setLastName("Singh");
        b.setAge(27);
        b.setEmail("sanket@x.com");
        b.setPassword("s3cret-pass");
        User u = b.build(); // solution 5: no `new User(b)` here any more
        System.out.println("created " + u.getFirstName() + " " + u.getLastName() + ", age " + u.getAge()
                + ", company = " + u.getCompany());

        // Optional attributes: only set what we have, then build.
        UserBuilder b2 = new UserBuilder();
        b2.setFirstName("Aditya");
        b2.setLastName("Sharma");
        b2.setAge(25);
        b2.setEmail("aditya@x.com");
        b2.setPassword("s3cret-pass");
        b2.setCompany("Acme");
        b2.setPhone("9876543210");
        User withOptional = b2.build();
        System.out.println("created " + withOptional.getFirstName() + ", company = " + withOptional.getCompany()
                + ", phone = " + withOptional.getPhone());

        // Each setter is still a separate statement. Chaining (b.setAge(10).setFirstName(...))
        // is not possible yet because the setters return void. That is solution 6.

        // Validation still happens in the setters.
        try {
            new UserBuilder().setAge(15);
        } catch (IllegalArgumentException e) {
            System.out.println("rejected -> " + e.getMessage());
        }

        // Solution 5 does not stop this: the User constructor is still public.
        // User sneaky = new User(b);   // compiles; solution 7 is about preventing it
    }
}
