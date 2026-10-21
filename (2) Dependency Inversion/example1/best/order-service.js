const { OrderDBRepository } = require("./order-db-repository");

// BEST: OrderService depends on the abstraction (OrderDBRepository), not on a
// specific database.
//
// Dependency Inversion, in this file
// ----------------------------------
// - There is no `require` of MySqlRepository or MongoRepository here, and no
//   `new MySqlRepository()`. The service doesn't know which databases exist.
// - The repository is handed in through the constructor (dependency injection).
//   Whoever builds the service decides, at construct time, whether it gets
//   MySQL, Mongo or anything else that implements OrderDBRepository.
// - Both the service (high-level) and the repositories (low-level) now depend
//   on the same abstraction, so the dependency arrow no longer points from the
//   business rules down to a database detail. It is "inverted".
//
// What this fixes
// ---------------
// - Switching MySQL -> Mongo: change one line where the service is created.
//   order-service.js is never edited (OCP and DIP).
// - Testing: pass a fake repository, no real database needed.
class OrderService {
  constructor(repository) {
    if (!(repository instanceof OrderDBRepository)) {
      throw new Error("OrderService needs an OrderDBRepository");
    }
    this.repository = repository; // injected, never created here
  }

  createOrder(customer, items) {
    if (!items || items.length === 0) {
      throw new Error("An order needs at least one item");
    }
    const total = items.reduce(
      (sum, item) => sum + item.price * item.quantity,
      0,
    );
    const order = { id: Date.now(), customer, items, total };

    return this.repository.createOrder(order);
  }
}

module.exports = { OrderService };
