const { CreditCard } = require("./credit-card");

class VisaCreditCard extends CreditCard {
  // @Override
  swipeAndPay() {
    console.log("Visa Credit Card swipeAndPay");
  }

  // @Override
  doRefund() {
    console.log("Visa Credit Card doRefund");
  }

  // @Override
  onlinePayment() {
    console.log("Visa Credit Card onlinePayment");
  }

  // @Override
  tapAndPay() {
    console.log("Visa Credit Card tapAndPay");
  }

  // @Override
  // upiPayment() { console.log("Visa Credit Card upiPayment"); }

  // @Override
  intlPayment() {
    console.log("Visa Credit Card intlPayment");
  }

  // @Override
  intlPinPayment() {
    console.log("Visa Credit Card intlPinPayment");
  }
}

module.exports = { VisaCreditCard };
