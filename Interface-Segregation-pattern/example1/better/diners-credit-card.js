const { CreditCard } = require("./credit-card");

// Diners: base behaviours only, no refund and no UPI, and nothing is faked.
class DinersCreditCard extends CreditCard {
  // @Override
  swipeAndPay() {
    console.log("Diners Credit Card swipeAndPay");
  }

  // @Override
  onlinePayment() {
    console.log("Diners Credit Card onlinePayment");
  }

  // @Override
  tapAndPay() {
    console.log("Diners Credit Card tapAndPay");
  }
}

module.exports = { DinersCreditCard };
