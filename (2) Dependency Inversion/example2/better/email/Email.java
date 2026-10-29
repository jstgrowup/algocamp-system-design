package di.example2.better.email;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

/**
 * BETTER (Prototype pattern): read the template file once, clone the email for
 * each order.
 */
public class Email {

    private final String template;
    private String body;

    public Email(String templateName) throws IOException {
        System.out.println("  reading " + templateName + " from disk..."); // expensive, now done only once
        this.template = Files.readString(Path.of(templateName));
        this.body = this.template;
    }

    // Copy constructor: no file read. The template is a String (immutable), so sharing it is safe.
    private Email(Email source) {
        this.template = source.template;
        this.body = source.template;
    }

    /** Cheap copy of the prepared email. */
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
