const { OrderDBRepository } = require("./order-db-repository");

// DB-level queries only. Everything Mongo-specific stays in here.
class MongoRepository extends OrderDBRepository {
  // @Override
  createOrder(order) {
    console.log(`Mongo: db.orders.insertOne(${JSON.stringify({ id: order.id, customer: order.customer, total: order.total })})`);
    return order;
  }
}

module.exports = { MongoRepository };
