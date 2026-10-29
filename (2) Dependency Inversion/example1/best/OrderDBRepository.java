package di.example1.best;

/**
 * The abstraction: what OrderService needs from ANY database.
 * MySQL, Mongo, Postgres, ... all implement this one contract, so they are
 * interchangeable from the service's point of view.
 */
public interface OrderDBRepository {

    Order createOrder(Order order);
}
