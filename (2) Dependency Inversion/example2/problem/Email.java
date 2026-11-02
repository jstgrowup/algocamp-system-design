package example2.problem;

import java.util.Map;

/**
 * PROBLEM 2: creating a new Email object for every order.
 *
 * The email body is an HTML template (normally kept in a file). Every time an order is
 * created we make a new Email, and its constructor reads that file from disk.
 *
 * What is wrong
 * -------------
 * File I/O is slow, and the template never changes between emails. Only the
 * data filled into it (customer name, order id) differs, yet every
 * `new Email()` reads the same file again. Same shape as the pawn problem:
 * object creation is expensive and it is repeated for no reason.
 *
 * The repetition (see Main):
 *     new Email(...)   // for order 101 -> reads the file (read #1)
 *     new Email(...)   // for order 102 -> reads the file (read #2)
 *     ...              // 4 orders, 4 reads of the same unchanged template
 */
public class Email {

    static int fileReads = 0; // counts reads so Main can show the waste

    private final String template; // raw HTML with {{placeholders}}
    private String body;            // template with the placeholders filled in

    public Email(String templateName) {
        fileReads++;
        System.out.println("  reading " + templateName + " from disk (read #" + fileReads + ")...");
        // expensive: disk read on every single creation
        this.template = readTemplate(templateName);
        this.body = this.template;
    }

    // Stands in for reading order-confirmation.html from disk (kept inline so the
    // example runs without any file). In real code: Files.readString(Path.of(name)).
    private static String readTemplate(String templateName) {
        return """
                <html>
                  <body>
                    <h1>Thanks for your order, {{customer}}!</h1>
                    <p>Your order #{{orderId}} has been placed.</p>
                  </body>
                </html>
                """;
    }

    /** Fills {{key}} placeholders in the template with the given data. */
    public Email populate(Map<String, Object> data) {
        String result = template;
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            result = result.replace("{{" + entry.getKey() + "}}", String.valueOf(entry.getValue()));
        }
        this.body = result;
        return this;
    }

    public void send(String to) {
        System.out.println("  sent to " + to + ": " + body.replaceAll("\\s+", " ").trim());
    }
}
