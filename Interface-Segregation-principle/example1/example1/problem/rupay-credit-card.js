const { CreditCard } = require("./credit-card");

class RupayCreditCard extends CreditCard {
  // @Override
  swipeAndPay() {
    console.log("Rupay Credit Card swipeAndPay");
  }

  // @Override
  doRefund() {
    console.log("Rupay Credit Card doRefund");
  }

  // @Override
  onlinePayment() {
    console.log("Rupay Credit Card onlinePayment");
  }

  // @Override
  tapAndPay() {
    console.log("Rupay Credit Card tapAndPay");
  }

  // NOT an override: UPI isn't on the parent, so a caller holding a CreditCard can't reach it
  upiPayment() {
    console.log("Rupay Credit Card upiPayment");
  }

  // @Override
  // Rupay can't do international payments but the parent forces the method on it.
  // Throwing here is the tell-tale sign of an ISP + LSP violation.
  intlPayment() {
    throw new Error("Not supported yet.");
  }

  // @Override
  // Not supported, but the parent forces us to define it anyway (ISP violation).
  intlPinPayment() {
    throw new Error("Rupay does not accept PIN for international payments.");
  }
}

module.exports = { RupayCreditCard };
