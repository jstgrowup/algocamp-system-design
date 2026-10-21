const { OrderDBRepository } = require("./order-db-repository");

// DB-level queries only. Everything MySQL-specific stays in here.
class MySqlRepository extends OrderDBRepository {
  // @Override
  createOrder(order) {
    console.log(`MySQL: INSERT INTO orders (id, customer, total) VALUES (${order.id}, '${order.customer}', ${order.total})`);
    return order;
  }
}

module.exports = { MySqlRepository };
