package di.example2.problem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

/**
 * PROBLEM 2: creating a new Email object for every order.
 *
 * The email body is an HTML template kept in a file. Every time an order is
 * created we make a new Email, and its constructor reads that file from disk.
 *
 * What is wrong
 * -------------
 * File I/O is slow, and the template never changes between emails. Only the
 * data filled into it (customer name, order id) differs, yet every
 * `new Email()` reads the same file again. Same shape as the pawn problem:
 * object creation is expensive and it is repeated for no reason.
 *
 * The repetition (what the Node file does at the bottom):
 *     createOrder("Asha", 101);   // new Email -> reads the file (read #1)
 *     createOrder("Ravi", 102);   // new Email -> reads the file (read #2)
 *     ...                         // 4 orders, 4 reads of the same unchanged file
 */
public class Email {

    static int fileReads = 0;

    private final String template;
    private String body;

    public Email(String templateName) throws IOException {
        fileReads++;
        System.out.println("  reading " + templateName + " from disk (read #" + fileReads + ")...");
        // expensive: disk read on every single creation
        this.template = Files.readString(Path.of(templateName));
        this.body = this.template;
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
