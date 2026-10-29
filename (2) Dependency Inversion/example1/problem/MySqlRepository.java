package di.example1.problem;

/**
 * Repository layer: ONLY database-level queries live here. No business rules.
 * Everything that knows "we are on MySQL" is kept inside this class.
 */
public class MySqlRepository {

    public Order save(Order order) {
        // In real code: a JDBC "INSERT INTO orders ..." statement
        System.out.println("MySQL: INSERT INTO orders (id, customer, total) VALUES ("
                + order.id() + ", '" + order.customer() + "', " + order.total() + ")");
        return order;
    }
}
