package di.example1.better;

import java.util.List;

/**
 * BETTER, but not done yet.
 *
 * What improved (OCP on the repository side)
 * ------------------------------------------
 * MySqlRepository and MongoRepository both implement OrderDBRepository and both
 * have createOrder(). Adding another database (Postgres, an API, ...) is a NEW
 * class that implements the same contract. No existing repository is edited.
 *
 * What is still wrong (DIP is still violated)
 * -------------------------------------------
 * OrderService, the high-level class with the business rules, still names a
 * concrete low-level class: `new MySqlRepository()`. The dependency still points
 * from business logic down to a database detail.
 *   - Tomorrow we switch to Mongo? We open OrderService and edit this file.
 *   - Testing createOrder still needs a real repository, since the service
 *     builds it itself.
 *
 * The next step (the "best" folder) is to stop creating the repository here and
 * have it handed in, so OrderService depends only on OrderDBRepository.
 */
public class OrderService {

    private final OrderDBRepository repository;

    public OrderService() {
        this.repository = new MySqlRepository();
        // Tomorrow: this.repository = new MongoRepository();  <- editing the service
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
