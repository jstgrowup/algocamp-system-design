const fs = require("fs");
const path = require("path");

// PROBLEM 2: creating a new Email object for every order.   Run: node email.js
//
// The email body is an HTML template kept in a file. Every time an order is
// created we make a new Email, and its constructor reads that file from disk.
//
// What is wrong
// -------------
// File I/O is slow, and the template never changes between emails. Only the
// data filled into it (customer name, order id) differs, yet every
// `new Email()` reads the same file again. Same shape as the pawn problem:
// object creation is expensive and it is repeated for no reason.
class Email {
  static fileReads = 0;

  constructor(templateName) {
    Email.fileReads++;
    console.log(`  reading ${templateName} from disk (read #${Email.fileReads})...`);
    // expensive: disk read on every single creation
    this.template = fs.readFileSync(path.join(__dirname, templateName), "utf8");
    this.body = this.template;
  }

  populate(data) {
    this.body = this.template.replace(/\{\{(\w+)\}\}/g, (_, key) => data[key]);
    return this;
  }

  send(to) {
    console.log(`  sent to ${to}: ${this.body.replace(/\s+/g, " ").trim()}`);
  }
}

// Called whenever an order is created: a brand-new Email every time.
function createOrder(customer, orderId) {
  console.log(`Order ${orderId} created`);
  new Email("order-confirmation.html").populate({ customer, orderId }).send(`${customer.toLowerCase()}@example.com`);
}

// The repetition: 4 orders, 4 reads of the same unchanged file.
createOrder("Asha", 101);
createOrder("Ravi", 102);
createOrder("Meera", 103);
createOrder("Kabir", 104);

console.log(`4 orders, ${Email.fileReads} template file reads. The template never changed.`);
