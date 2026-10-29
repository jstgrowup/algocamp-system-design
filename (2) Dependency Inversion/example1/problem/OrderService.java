package di.example1.problem;

import java.util.List;

/**
 * PROBLEM: the high-level OrderService creates its own low-level repository.
 *
 * What is wrong here
 * ------------------
 * DIP (Dependency Inversion) is violated.
 *   "High-level modules should not depend on low-level modules. Both should
 *   depend on abstractions."
 *   OrderService holds the business rules (the important part), yet it is
 *   welded to a concrete detail: `new MySqlRepository()`.
 *
 * What that costs you
 *   - Switching to Postgres, MongoDB or an API means opening OrderService and
 *     editing it, even though no business rule changed.
 *   - You can't unit test createOrder without MySQL, because the service builds
 *     the real repository itself and nothing can be swapped in.
 *   - The dependency points from business logic down to the detail.
 *
 * What is already fine
 *   The service does NOT run SQL itself. It talks to a repository, so DB
 *   queries are kept out of the business code (that is SRP). The missing piece
 *   is where the repository comes from.
 */
public class OrderService {

    private final MySqlRepository repository;

    public OrderService() {
        this.repository = new MySqlRepository(); // hard-wired to one concrete DB
    }

    public Order createOrder(String customer, List<Order.Item> items) {
        // Business rules live in the service
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("An order needs at least one item");
        }
        double total = 0;
        for (Order.Item item : items) {
            total += item.price() * item.quantity();
        }
        Order order = new Order(System.currentTimeMillis(), customer, items, total);

        // Delegate persistence to the repository
        return this.repository.save(order);
    }
}
