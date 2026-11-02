package example1.better;

import java.util.List;

/** Runs the BETTER version: one repository contract, but the service still picks MySQL itself. */
public class Main {

    public static void main(String[] args) {
        List<Order.Item> items = List.of(
                new Order.Item("keyboard", 50, 1),
                new Order.Item("mouse", 20, 2));
        Order order = new Order(1, "Asha", items, 90);

        // 1. Both repositories honour the same OrderDBRepository contract,
        //    so either can be used the same way (OCP on the repository side).
        for (OrderDBRepository repository : List.of(new MySqlRepository(), new MongoRepository())) {
            repository.createOrder(order);
        }

        // 2. But the service still decides which one to use on its own.
        //    To use Mongo we would have to edit OrderService (DIP not solved yet).
        new OrderService().createOrder("Asha", items);
    }
}
