package di.example3.rules;

/**
 * A broken rule, carrying the reason. Create it with Violation.of("message").
 *
 * The constructor is private so `of()` is the only way to build a Violation.
 */
public class Violation {

    private final String message;

    private Violation(String message) {
        this.message = message;
    }

    public static Violation of(String message) {
        return new Violation(message);
    }

    // Added so callers can read the reason (the readme's Java has the field but no getter).
    public String getMessage() {
        return message;
    }
}
