const { CreditCard } = require("./credit-card");

class MasterCardCreditCard extends CreditCard {
  // @Override
  swipeAndPay() {
    console.log("MasterCard Credit Card swipeAndPay");
  }

  // @Override
  doRefund() {
    console.log("MasterCard Credit Card doRefund");
  }

  // @Override
  onlinePayment() {
    console.log("MasterCard Credit Card onlinePayment");
  }

  // @Override
  tapAndPay() {
    console.log("MasterCard Credit Card tapAndPay");
  }

  // @Override
  // upiPayment() { console.log("MasterCard Credit Card upiPayment"); }

  // @Override
  intlPayment() {
    console.log("MasterCard Credit Card intlPayment");
  }

  // @Override
  // Not supported, but the parent forces us to define it anyway (ISP violation).
  intlPinPayment() {
    throw new Error("MasterCard does not accept PIN for international payments.");
  }
}

module.exports = { MasterCardCreditCard };
