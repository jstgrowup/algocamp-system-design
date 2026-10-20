const { CreditCard } = require("./credit-card");

class AmexCreditCard extends CreditCard {
  // @Override
  swipeAndPay() {
    console.log("Amex Credit Card swipeAndPay");
  }

  // @Override
  doRefund() {
    console.log("Amex Credit Card doRefund");
  }

  // @Override
  onlinePayment() {
    console.log("Amex Credit Card onlinePayment");
  }

  // @Override
  tapAndPay() {
    console.log("Amex Credit Card tapAndPay");
  }

  // @Override
  // upiPayment() { console.log("Amex Credit Card upiPayment"); }

  // @Override
  intlPayment() {
    console.log("Amex Credit Card intlPayment");
  }

  // @Override
  intlPinPayment() {
    console.log("Amex Credit Card intlPinPayment");
  }
}

module.exports = { AmexCreditCard };
