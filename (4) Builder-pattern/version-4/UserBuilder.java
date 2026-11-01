// The Builder: a temporary object that collects the values for a User.
//
// - It holds every attribute of User, so the caller sets them by NAME
//   (setFirstName, setAge, ...) instead of remembering a parameter ORDER.
// - The validation lives here, in the setters. A bad value is rejected the moment it
//   is set, long before a User exists.
// - Optional attributes (company, phone) are simply not set. No null to pass by hand,
//   and no extra constructor versions.
// - Each setter returns `this`, so calls can be chained (the usual Builder style).
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

    public UserBuilder setFirstName(String firstName) {
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("firstName must not be empty");
        }
        this.firstName = firstName;
        return this;
    }

    public UserBuilder setLastName(String lastName) {
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("lastName must not be empty");
        }
        this.lastName = lastName;
        return this;
    }

    public UserBuilder setAge(int age) {
        if (age < 18) {
            throw new IllegalArgumentException("age must be at least 18");
        }
        this.age = age;
        return this;
    }

    public UserBuilder setEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("email must contain @");
        }
        this.email = email;
        return this;
    }

    public UserBuilder setPassword(String password) {
        if (password == null || password.length() < 8) {
            throw new IllegalArgumentException("password must be at least 8 characters");
        }
        this.password = password;
        return this;
    }

    public UserBuilder setCompany(String company) {
        if (company == null || company.isBlank() || company.length() > 50) {
            throw new IllegalArgumentException("company must be 1 to 50 characters");
        }
        this.company = company;
        return this;
    }

    public UserBuilder setPhone(String phone) {
        if (phone == null || !phone.matches("\\d{10}")) {
            throw new IllegalArgumentException("phone must be exactly 10 digits");
        }
        this.phone = phone;
        return this;
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
