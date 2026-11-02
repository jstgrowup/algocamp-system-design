package builder_v6;

// SOLUTION 6: the builder methods return the builder, so calls can be chained.
//
// In solution 5 every setter returned void, so each value needed its own statement:
//
//     UserBuilder b = new UserBuilder();
//     b.setFirstName("Sanket");
//     b.setLastName("Singh");
//     User u = b.build();
//
// Now each method returns `this` (the same builder), so the next call can be made
// directly on the result:
//
//     User u = new UserBuilder().firstName("Sanket").lastName("Singh").age(27).build();
//
// The methods are named after the attribute (age, firstName, ...) instead of
// setAge, setFirstName, ... which reads like a sentence. Validation still lives in
// each method, and build() still creates the User.
public class UserBuilder {
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private String password;
    private String company; // optional
    private String phone; // optional

    public UserBuilder() {
    }

    public UserBuilder firstName(String firstName) {
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("firstName must not be empty");
        }
        this.firstName = firstName;
        return this; // return the builder so the next call can follow
    }

    public UserBuilder lastName(String lastName) {
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("lastName must not be empty");
        }
        this.lastName = lastName;
        return this;
    }

    public UserBuilder age(int age) {
        if (age < 18) {
            throw new IllegalArgumentException("age must be at least 18");
        }
        this.age = age;
        return this;
    }

    public UserBuilder email(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("email must contain @");
        }
        this.email = email;
        return this;
    }

    public UserBuilder password(String password) {
        if (password == null || password.length() < 8) {
            throw new IllegalArgumentException("password must be at least 8 characters");
        }
        this.password = password;
        return this;
    }

    public UserBuilder company(String company) {
        if (company == null || company.isBlank() || company.length() > 50) {
            throw new IllegalArgumentException("company must be 1 to 50 characters");
        }
        this.company = company;
        return this;
    }

    public UserBuilder phone(String phone) {
        if (phone == null || !phone.matches("\\d{10}")) {
            throw new IllegalArgumentException("phone must be exactly 10 digits");
        }
        this.phone = phone;
        return this;
    }

    // Hands the collected values to User and returns the finished object.
    public User build() {
        return new User(this);
    }

    // Package-private getters: only User (same package) needs to read the values.
    String getFirstName() {
        return firstName;
    }

    String getLastName() {
        return lastName;
    }

    int getAge() {
        return age;
    }

    String getEmail() {
        return email;
    }

    String getPassword() {
        return password;
    }

    String getCompany() {
        return company;
    }

    String getPhone() {
        return phone;
    }
}
