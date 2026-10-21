const { OrderService } = require("./order-service");

const service = new OrderService();
service.createOrder("Asha", [
  { name: "keyboard", price: 50, quantity: 1 },
  { name: "mouse", price: 20, quantity: 2 },
]);

// Business rule still works the same:
try {
  service.createOrder("Asha", []);
} catch (e) {
  console.log("Rejected:", e.message);
}

// The problem: there is no way to run createOrder against another store
// (Postgres, in-memory for tests...) without editing order-service.js.
