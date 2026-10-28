const { Email } = require("./email");

// Run: node create-orders.js
const baseEmail = new Email("order-confirmation.html"); // the only file read

// Called whenever an order is created: a clone of the base email, not a new Email.
function createOrder(customer, orderId) {
  console.log(`Order ${orderId} created`);
  baseEmail.clone().populate({ customer, orderId }).send(`${customer.toLowerCase()}@example.com`);
}

createOrder("Asha", 101);
createOrder("Ravi", 102);
createOrder("Meera", 103);
createOrder("Kabir", 104);
