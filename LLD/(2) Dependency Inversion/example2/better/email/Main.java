package example2.better.email;

import java.util.Map;

/** Prototype pattern: one base Email loads the template, every order gets a clone. */
public class Main {

    public static void main(String[] args) {
        Email baseEmail = new Email("order-confirmation.html"); // the only "file read"

        String[] customers = {"Asha", "Ravi", "Meera", "Kabir"};
        for (int i = 0; i < customers.length; i++) {
            int orderId = 101 + i;
            System.out.println("Order " + orderId + " created");
            // A clone of the base email, not a new Email.
            baseEmail.clone()
                    .populate(Map.of("customer", customers[i], "orderId", orderId))
                    .send(customers[i].toLowerCase() + "@example.com");
        }
    }
}
