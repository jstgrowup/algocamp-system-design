package builder_v4;

// SOLUTION 4: the Builder pattern.
//
// Instead of one big constructor (solution 2) or many constructors (solution 3),
// User has ONE constructor that takes a UserBuilder:
//
//     UserBuilder b = new UserBuilder();
//     b.setFirstName("Sanket");
//     b.setLastName("Singh");
//     b.setAge(27);
//     User u = new User(b);
//
// The builder already validated every value in its setters, so this constructor does
// NOT need to validate anything. It just copies the values. That is much cleaner.
//
// WHAT THIS FIXES
//   - Parameter order: values are set by name, in any order.
//   - Optional attributes: just don't call the setter. No null, no extra constructors.
//   - Constructor bloat: User's constructor is a few plain assignments.
//
// NOTE (not part of this step): because User trusts the builder, a builder that was
// never given a firstName still produces a User with firstName == null, and a rule
// that compares fields (password must not contain the name) can't be checked in a
// single setter, since the other value may not be set yet. Those need one last check
// before the User is created.
public class User {
    private final String firstName;
    private final String lastName;
    private final int age;
    private final String email;
    private final String password;
    private final String company; // optional
    private final String phone; // optional

    public User(UserBuilder builder) {
        this.firstName = builder.getFirstName();
        this.lastName = builder.getLastName();
        this.age = builder.getAge();
        this.email = builder.getEmail();
        this.password = builder.getPassword();
        this.company = builder.getCompany();
        this.phone = builder.getPhone();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getCompany() {
        return company;
    }

    public String getPhone() {
        return phone;
    }
}
