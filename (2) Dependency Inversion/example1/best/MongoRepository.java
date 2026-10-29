package di.example1.best;

/**
 * DB-level queries only. Everything Mongo-specific stays in here.
 * Implements the same OrderDBRepository contract as MySqlRepository, so the two
 * are interchangeable from the service's point of view.
 */
public class MongoRepository implements OrderDBRepository {

    @Override
    public Order createOrder(Order order) {
        System.out.println("Mongo: db.orders.insertOne({id: " + order.id()
                + ", customer: '" + order.customer() + "', total: " + order.total() + "})");
        return order;
    }
}
