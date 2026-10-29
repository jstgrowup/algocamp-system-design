package di.example1.best;

import java.util.List;
import java.util.Objects;

/**
 * BEST: OrderService depends on the abstraction (OrderDBRepository), not on a
 * specific database.
 *
 * Dependency Inversion, in this file
 * ----------------------------------
 * - There is no reference to MySqlRepository or MongoRepository here, and no
 *   `new MySqlRepository()`. The service doesn't know which databases exist.
 * - The repository is handed in through the constructor (dependency injection).
 *   Whoever builds the service decides, at construct time, whether it gets
 *   MySQL, Mongo or anything else that implements OrderDBRepository.
 * - Both the service (high-level) and the repositories (low-level) now depend
 *   on the same abstraction, so the dependency arrow no longer points from the
 *   business rules down to a database detail. It is "inverted".
 *
 * What this fixes
 * ---------------
 * - Switching MySQL -> Mongo: change one line where the service is created:
 *       new OrderService(new MongoRepository());
 *   OrderService is never edited (OCP and DIP).
 * - Testing: pass a fake repository, no real database needed.
 */
public class OrderService {

    private final OrderDBRepository repository;

    public OrderService(OrderDBRepository repository) {
        // The Java type system already guarantees an OrderDBRepository; we only reject null.
        this.repository = Objects.requireNonNull(repository, "OrderService needs an OrderDBRepository");
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
        return this.repository.createOrder(order);
    }
}
