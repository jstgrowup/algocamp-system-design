package example1.best;

import java.util.ArrayList;
import java.util.List;

/**
 * Runs the BEST version. This class is the only place that knows the concrete
 * databases (the "composition root"): it picks one and injects it into the service.
 * Run with an argument to choose: `java ...best.Main mongo` (default is MySQL).
 */
public class Main {

    public static void main(String[] args) {
        List<Order.Item> items = List.of(
                new Order.Item("keyboard", 50, 1),
                new Order.Item("mouse", 20, 2));

        // Decide at runtime which repository the service gets.
        OrderDBRepository repository = args.length > 0 && args[0].equals("mongo")
                ? new MongoRepository()
                : new MySqlRepository();
        new OrderService(repository).createOrder("Asha", items);

        // Same service code, any repository: just construct it with another one.
        new OrderService(new MongoRepository()).createOrder("Asha", items);

        // Testing: a fake repository, no database needed. The interface has one
        // method, so a lambda is enough (like passing an arrow function in JS).
        List<Order> saved = new ArrayList<>();
        OrderDBRepository fake = order -> {
            saved.add(order);
            return order;
        };
        new OrderService(fake).createOrder("Asha", items);
        System.out.println("in-memory orders: " + saved.size() + ", total: " + saved.get(0).total());

        // The service refuses a missing repository.
        try {
            new OrderService(null);
        } catch (NullPointerException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
    }
}
