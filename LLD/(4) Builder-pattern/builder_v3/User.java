package builder_v3;

// SOLUTION 3: multiple constructors (constructor overloading).
//
// Instead of one constructor where the caller passes null for what they don't need,
// we write several constructors with different parameter lists:
//
//     new User(first, last, age, email, password)                    // no company, no phone
//     new User(first, last, age, email, password, company)           // with company
//     new User(first, last, age, email, password, company, phone)    // everything
//
// Java picks the right one from the number and types of the arguments.
// (This is called constructor overloading.)
//
// To avoid repeating the validations in every constructor, the short ones delegate to
// the full one with `this(...)` (constructor chaining). All validation lives in ONE place.
//
// WHAT THIS FIXES
//   - Problem 3 of solution 2: a user without a company no longer needs a null.
//
// WHAT IS STILL WRONG (see Main.java and the commented-out constructor below)
//   - If there are N optional attributes we need more and more constructor versions,
//     and the number of orders / combinations to support grows quickly.
//   - Overloading is decided by the parameter TYPES. Two optional Strings (company,
//     phone) look the same to Java, so we can't have a "phone but no company" version.
//   - The full constructor still has 7 positional parameters, so the order problem
//     (swapped Strings that compile fine) is still there.
//   - We want validation, but without a bloated constructor.
public class User {
    private final String firstName;
    private final String lastName;
    private final int age;
    private final String email;
    private final String password;
    private final String company; // optional
    private final String phone; // optional

    // Version 1: only the mandatory attributes.
    public User(String firstName, String lastName, int age, String email, String password) {
        this(firstName, lastName, age, email, password, null, null);
    }

    // Version 2: mandatory attributes + company.
    public User(String firstName, String lastName, int age, String email, String password, String company) {
        this(firstName, lastName, age, email, password, company, null);
    }

    // Version 3: every attribute. All the validations live here.
    public User(String firstName, String lastName, int age, String email, String password,
            String company, String phone) {
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

        // Rule that combines several fields.
        String lowerPassword = password.toLowerCase();
        if (lowerPassword.contains(firstName.toLowerCase()) || lowerPassword.contains(lastName.toLowerCase())) {
            throw new IllegalArgumentException("password must not contain the first or last name");
        }

        // Optional attributes: fine to be missing, but must be valid when given.
        if (company != null && (company.isBlank() || company.length() > 50)) {
            throw new IllegalArgumentException("company must be 1 to 50 characters when given");
        }
        if (phone != null && !phone.matches("\\d{10}")) {
            throw new IllegalArgumentException("phone must be exactly 10 digits when given");
        }

        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.password = password;
        this.company = company;
        this.phone = phone;
    }

    // Version 4 (does NOT compile, so it is commented out): "phone but no company".
    // Its parameter list (String, String, int, String, String, String) is exactly the same
    // as Version 2's, only the meaning differs. Java only looks at the types, so it
    // reports a duplicate constructor. The only way to give a phone and no company is
    // Version 3 with an explicit null:  new User(..., null, "9876543210").
    //
    // public User(String firstName, String lastName, int age, String email, String password, String phone) {
    //     this(firstName, lastName, age, email, password, null, phone);
    // }

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
