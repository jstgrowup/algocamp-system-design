// SOLUTION 2: put all the validations in the constructor.
//
// If any check fails the constructor throws, so the object is never created.
// A User that exists is always valid. The fields are `final`, so they are set once,
// and the combination rule (password vs names) is checked at the one moment all the
// values are available together.
//
// BUT this solution brings new problems (see Main.java for each one):
//
//   1. Too many parameters. The caller has to remember the ORDER of firstName,
//      lastName, age, email, password, company. Two Strings next to each other
//      (firstName / lastName, email / password) can be swapped and it still
//      compiles. Adding a new parameter is a headache, and the call site is hard
//      to read: new User("Aditya", "Sharma", 25, "a@x.com", "s3cret-pass", null).
//
//   2. An optional attribute needs its own validation. `company` is not mandatory,
//      but IF it is given it must be valid. That `if (company != null && ...)` block
//      is in the constructor, and every new optional field adds another one.
//      The constructor keeps growing and becomes unmanageable.
//
//   3. For the parameters we don't need we must still pass null by hand
//      (a user without a company: ..., "s3cret-pass", null).
public class User {
    private final String firstName;
    private final String lastName;
    private final int age;
    private final String email;
    private final String password;
    private final String company; // optional: null means "no company"

    public User(String firstName, String lastName, int age, String email, String password, String company) {
        // Single-field rules
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("firstName must not be empty");
        }
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("lastName must not be empty");
        }
        if (age < 18) {
            throw new IllegalArgumentException("age must be at least 18");
        }
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("email must contain @");
        }
        if (password == null || password.length() < 8) {
            throw new IllegalArgumentException("password must be at least 8 characters");
        }

        // Rule that combines several fields: all of them are known here, in any order.
        String lowerPassword = password.toLowerCase();
        if (lowerPassword.contains(firstName.toLowerCase()) || lowerPassword.contains(lastName.toLowerCase())) {
            throw new IllegalArgumentException("password must not contain the first or last name");
        }

        // Optional attribute: no company is fine, but a given company must be valid.
        // (Problem 2: this block exists only because of ONE optional field.)
        if (company != null && (company.isBlank() || company.length() > 50)) {
            throw new IllegalArgumentException("company must be 1 to 50 characters when given");
        }

        // Only now, after every check passed, do we assign the fields.
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.password = password;
        this.company = company;
    }

    // Getters only: no setters, so a valid User can't be turned invalid later.
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
}
