package example3.implementation.rules;

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

    // Lets callers read the reason.
    public String getMessage() {
        return message;
    }
}
