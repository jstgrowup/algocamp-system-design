const { OrderService } = require("./order-service");
const { MySqlRepository } = require("./mysql-repository");
const { MongoRepository } = require("./mongo-repository");

const items = [
  { name: "keyboard", price: 50, quantity: 1 },
  { name: "mouse", price: 20, quantity: 2 },
];
const order = { id: 1, customer: "Asha", total: 90 };

// 1. Both repositories honour the same OrderDBRepository contract,
//    so either can be used the same way (OCP on the repository side).
for (const repository of [new MySqlRepository(), new MongoRepository()]) {
  repository.createOrder(order);
}

// 2. But the service still decides which one to use on its own.
//    To use Mongo we would have to edit order-service.js (DIP not solved yet).
new OrderService().createOrder("Asha", items);
