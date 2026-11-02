package builder_v6;

// The User, built from a UserBuilder. Unchanged from solution 5: the constructor only
// copies the values, because the builder already validated them.
//
// STILL OPEN (solution 7 in the readme): this constructor is public, so anyone can
// call it directly, for example new User(someBuilder) from anywhere, or new User(null),
// which crashes. To prevent that it should be private, but then UserBuilder could not
// call it either.
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
