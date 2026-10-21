const { MySqlRepository } = require("./mysql-repository");

// PROBLEM: the high-level OrderService creates its own low-level repository.
//
// What is wrong here
// ------------------
// DIP (Dependency Inversion) is violated.
//   "High-level modules should not depend on low-level modules. Both should
//   depend on abstractions."
//   OrderService holds the business rules (the important part), yet it is
//   welded to a concrete detail: `new MySqlRepository()`.
//
// What that costs you
//   - Switching to Postgres, MongoDB or an API means opening OrderService and
//     editing it, even though no business rule changed.
//   - You can't unit test createOrder without MySQL, because the service builds
//     the real repository itself and nothing can be swapped in.
//   - The `require` at the top is the tell: the business layer imports the DB
//     layer, so the dependency points from business logic down to the detail.
//
// What is already fine
//   The service does NOT run SQL itself. It talks to a repository, so DB
//   queries are kept out of the business code (that is SRP). The missing piece
//   is where the repository comes from.
class OrderService {
  constructor() {
    this.repository = new MySqlRepository(); // hard-wired to one concrete DB
  }

  createOrder(customer, items) {
    // Business rules live in the service
    if (!items || items.length === 0) {
      throw new Error("An order needs at least one item");
    }
    const total = items.reduce(
      (sum, item) => sum + item.price * item.quantity,
      0,
    );
    const order = { id: Date.now(), customer, items, total };

    // Delegate persistence to the repository
    return this.repository.save(order);
  }
}

module.exports = { OrderService };
