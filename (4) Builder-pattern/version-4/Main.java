public class Main {
    public static void main(String[] args) {
        // 1. The flow from the readme: fill a builder, then create the User from it.
        UserBuilder b = new UserBuilder();
        b.setFirstName("Sanket");
        b.setLastName("Singh");
        b.setAge(27);
        b.setEmail("sanket@x.com");
        b.setPassword("s3cret-pass");
        User u = new User(b);
        System.out.println("created " + u.getFirstName() + " " + u.getLastName() + ", age " + u.getAge()
                + ", company = " + u.getCompany());

        // 2. The order doesn't matter, and we only name what we set. Each value is clear
        //    at the call site, unlike new User("Sanket", "Singh", 27, "sanket@x.com", ...).
        UserBuilder b2 = new UserBuilder();
        b2.setPassword("p4ssword-ok");
        b2.setEmail("riya@x.com");
        b2.setAge(30);
        b2.setLastName("Verma");
        b2.setFirstName("Riya");
        System.out.println("created " + new User(b2).getFirstName() + " (set in a different order)");

        // 3. Optional attributes: set them only when we have them. No null, no extra constructors.
        UserBuilder b3 = new UserBuilder();
        b3.setFirstName("Aditya");
        b3.setLastName("Sharma");
        b3.setAge(25);
        b3.setEmail("aditya@x.com");
        b3.setPassword("s3cret-pass");
        b3.setCompany("Acme");
        b3.setPhone("9876543210");
        User withOptional = new User(b3);
        System.out.println("created " + withOptional.getFirstName() + ", company = " + withOptional.getCompany()
                + ", phone = " + withOptional.getPhone());

        // 4. Because each setter returns the builder, the calls can also be chained.
        User chained = new User(new UserBuilder()
                .setFirstName("Meera")
                .setLastName("Rao")
                .setAge(27)
                .setEmail("meera@x.com")
                .setPassword("m33ra-pass-ok"));
        System.out.println("created " + chained.getFirstName() + " (chained)");

        // 5. Validation happens in the builder's setters, so a bad value is rejected
        //    before a User even exists.
        try {
            new UserBuilder().setAge(15);
        } catch (IllegalArgumentException e) {
            System.out.println("rejected -> " + e.getMessage());
        }
        try {
            new UserBuilder().setEmail("not-an-email");
        } catch (IllegalArgumentException e) {
            System.out.println("rejected -> " + e.getMessage());
        }
    }
}
