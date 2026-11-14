package builder_v1;

// STEP 0 (the starting problem): all the attributes were public.
//
//     public class User {
//         public String firstName; public String lastName; public String email;
//         public String password;  public int age;
//     }
//
// Anyone could write u.firstName = "" or u.age = -5 from outside, and the class
// had no place to enforce a single rule.
//
// SOLUTION 1: make the fields private and add getters / setters.
//
// Now nobody can touch the fields directly, and each setter can validate its value.
// The new problem is that the object already exists before any setter runs
// (see Main.java), so it can be invalid, and a rule that needs two fields
// ("password must not contain the first name") can't be enforced reliably.
public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int age;

    // No constructor written, so Java gives us a default one: new User() with every
    // field empty (null / 0). The object is created WITHOUT going through any validation.

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("firstName must not be empty");
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("lastName must not be empty");
        }
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("email must contain @");
        }
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null || password.length() < 8) {
            throw new IllegalArgumentException("password must be at least 8 characters");
        }
        // Rule that needs ANOTHER field: the password can't contain the first name.
        // It only works if firstName was already set. Otherwise it is null and the check is skipped.
        if (firstName != null && password.toLowerCase().contains(firstName.toLowerCase())) {
            throw new IllegalArgumentException("password must not contain the first name");
        }
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 18) {
            throw new IllegalArgumentException("age must be at least 18");
        }
        this.age = age;
    }
}
