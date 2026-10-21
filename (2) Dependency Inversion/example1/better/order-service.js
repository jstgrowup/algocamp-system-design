const { MySqlRepository } = require("./mysql-repository");
const { MongoRepository } = require("./mongo-repository");

// BETTER, but not done yet.
//
// What improved (OCP on the repository side)
// ------------------------------------------
// MySqlRepository and MongoRepository both implement OrderDBRepository and both
// have createOrder(). Adding another database (Postgres, an API, ...) is a NEW
// class that implements the same contract. No existing repository is edited.
//
// What is still wrong (DIP is still violated)
// -------------------------------------------
// OrderService, the high-level class with the business rules, still names a
// concrete low-level class: `new MySqlRepository()` (and it `require`s both
// files). The dependency still points from business logic down to a database
// detail.
//   - Tomorrow we switch to Mongo? We open OrderService and edit this file.
//   - Testing createOrder still needs a real repository, since the service
//     builds it itself.
//
// The next step (the "best" folder) is to stop creating the repository here and
// have it handed in, so OrderService depends only on OrderDBRepository.
class OrderService {
  constructor() {
    this.repository = new MySqlRepository();
    // Tomorrow: this.repository = new MongoRepository();  <- editing the service
  }

  createOrder(customer, items) {
    if (!items || items.length === 0) {
      throw new Error("An order needs at least one item");
    }
    const total = items.reduce((sum, item) => sum + item.price * item.quantity, 0);
    const order = { id: Date.now(), customer, items, total };

    return this.repository.createOrder(order);
  }
}

module.exports = { OrderService };
