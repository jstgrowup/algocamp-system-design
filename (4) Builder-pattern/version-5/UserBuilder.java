// SOLUTION 5: the builder creates the User itself with build().
//
// In solution 4 the caller had to know about both classes and wire them together:
//
//     User u = new User(b);
//
// Now the builder owns that step:
//
//     User u = b.build();
//
// The client only talks to the builder. The setters are still void (chaining comes
// in solution 6), and the validation still lives in the setters.
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

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("firstName must not be empty");
        }
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("lastName must not be empty");
        }
        this.lastName = lastName;
    }

    public void setAge(int age) {
        if (age < 18) {
            throw new IllegalArgumentException("age must be at least 18");
        }
        this.age = age;
    }

    public void setEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("email must contain @");
        }
        this.email = email;
    }

    public void setPassword(String password) {
        if (password == null || password.length() < 8) {
            throw new IllegalArgumentException("password must be at least 8 characters");
        }
        this.password = password;
    }

    public void setCompany(String company) {
        if (company == null || company.isBlank() || company.length() > 50) {
            throw new IllegalArgumentException("company must be 1 to 50 characters");
        }
        this.company = company;
    }

    public void setPhone(String phone) {
        if (phone == null || !phone.matches("\\d{10}")) {
            throw new IllegalArgumentException("phone must be exactly 10 digits");
        }
        this.phone = phone;
    }

    // The new method: hands the collected values to User and returns the finished object.
    // This is also the natural place for a last check before the User is created.
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
