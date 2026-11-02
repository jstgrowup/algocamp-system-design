package example2.better.email;

import java.util.Map;

/**
 * BETTER (Prototype pattern): load the template once into a "base" Email, then
 * clone that email for each order instead of building a new one from scratch.
 * Copying an in-memory String is far cheaper than repeating the file read.
 */
public class Email {

    private final String template; // never changes after construction
    private String body;            // template with {{placeholders}} filled in

    public Email(String templateName) {
        System.out.println("  reading " + templateName + " from disk..."); // expensive, now done only once
        this.template = readTemplate(templateName);
        this.body = this.template;
    }

    // Copy constructor: no file read. The template is a String (immutable), so sharing it is safe.
    private Email(Email source) {
        this.template = source.template;
        this.body = source.template;
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

    /**
     * Cheap copy of the prepared email. (Overrides Object.clone() but uses the copy
     * constructor, so there is no Cloneable / CloneNotSupportedException to deal with.)
     */
    public Email clone() {
        return new Email(this);
    }

    /** Fills {{key}} placeholders. Only changes this email's body, never the template. */
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
