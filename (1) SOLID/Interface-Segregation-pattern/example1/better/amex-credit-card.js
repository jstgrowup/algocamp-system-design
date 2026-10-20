const { CreditCard } = require("./credit-card");
const { IntlPinCompatible } = require("./intl-pin-compatible");
const { RefundCompatible } = require("./refund-compatible");

// Amex: base + refund + intl PIN capabilities. (A new file, nothing existing edited.)
class AmexCreditCard extends IntlPinCompatible(RefundCompatible(CreditCard)) {
  // @Override
  swipeAndPay() {
    console.log("Amex Credit Card swipeAndPay");
  }

  // @Override
  onlinePayment() {
    console.log("Amex Credit Card onlinePayment");
  }

  // @Override
  tapAndPay() {
    console.log("Amex Credit Card tapAndPay");
  }

  // @Override (RefundCompatible)
  doRefund() {
    console.log("Amex Credit Card doRefund");
  }

  // @Override (IntlPinCompatible)
  intlPinPayment() {
    console.log("Amex Credit Card intlPinPayment");
  }
}

module.exports = { AmexCreditCard };
