package example1.problem;

import java.util.List;

/** Runs the PROBLEM version: OrderService is welded to MySQL. */
public class Main {

    public static void main(String[] args) {
        OrderService service = new OrderService();
        service.createOrder("Asha", List.of(
                new Order.Item("keyboard", 50, 1),
                new Order.Item("mouse", 20, 2)));

        // The business rule still works...
        try {
            service.createOrder("Asha", List.of());
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }

        // ...but there is no way to run createOrder against another store
        // (Postgres, an in-memory fake for tests) without editing OrderService.
    }
}
