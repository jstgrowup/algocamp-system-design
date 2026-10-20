const { CreditCard } = require("./credit-card");

class DinersCreditCard extends CreditCard {
  // @Override
  swipeAndPay() {
    console.log("Diners Credit Card swipeAndPay");
  }

  // @Override
  // Diners isn't qualified for refunds but the parent forces the method on it.
  // Throwing here is the tell-tale sign of an ISP + LSP violation.
  doRefund() {
    throw new Error("Diners card is not qualified for refund.");
  }

  // @Override
  onlinePayment() {
    console.log("Diners Credit Card onlinePayment");
  }

  // @Override
  tapAndPay() {
    console.log("Diners Credit Card tapAndPay");
  }

  // @Override
  // upiPayment() { console.log("Diners Credit Card upiPayment"); }

  // @Override
  intlPayment() {
    console.log("Diners Credit Card intlPayment");
  }

  // @Override
  // Not supported, but the parent forces us to define it anyway (ISP violation).
  intlPinPayment() {
    throw new Error("Diners card does not accept PIN for international payments.");
  }
}

module.exports = { DinersCreditCard };
