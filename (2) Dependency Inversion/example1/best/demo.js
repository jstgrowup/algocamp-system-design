const { OrderService } = require("./order-service");
const { OrderDBRepository } = require("./order-db-repository");
const { MySqlRepository } = require("./mysql-repository");
const { MongoRepository } = require("./mongo-repository");

const items = [
  { name: "keyboard", price: 50, quantity: 1 },
  { name: "mouse", price: 20, quantity: 2 },
];

// This file is the only place that knows about concrete databases.
// It decides at runtime which one the service gets: `DB=mongo node demo.js`
const repository = process.env.DB === "mongo" ? new MongoRepository() : new MySqlRepository();
new OrderService(repository).createOrder("Asha", items);

// Same service code, any repository: just construct it with another one.
new OrderService(new MongoRepository()).createOrder("Asha", items);

// Testing: a fake repository, no database needed.
class InMemoryRepository extends OrderDBRepository {
  orders = [];
  createOrder(order) {
    this.orders.push(order);
    return order;
  }
}
const fake = new InMemoryRepository();
new OrderService(fake).createOrder("Asha", items);
console.log("in-memory orders:", fake.orders.length, "total:", fake.orders[0].total);

// The service refuses anything that isn't an OrderDBRepository.
try {
  new OrderService({});
} catch (e) {
  console.log("Rejected:", e.message);
}
