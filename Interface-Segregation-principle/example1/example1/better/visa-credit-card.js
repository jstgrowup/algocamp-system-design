const { CreditCard } = require("./credit-card");
const { IntlPinCompatible } = require("./intl-pin-compatible");
const { RefundCompatible } = require("./refund-compatible");

// Visa: base + refund + intl PIN capabilities.
class VisaCreditCard extends IntlPinCompatible(RefundCompatible(CreditCard)) {
  // @Override
  swipeAndPay() {
    console.log("Visa Credit Card swipeAndPay");
  }

  // @Override
  onlinePayment() {
    console.log("Visa Credit Card onlinePayment");
  }

  // @Override
  tapAndPay() {
    console.log("Visa Credit Card tapAndPay");
  }

  // @Override (RefundCompatible)
  doRefund() {
    console.log("Visa Credit Card doRefund");
  }

  // @Override (IntlPinCompatible)
  intlPinPayment() {
    console.log("Visa Credit Card intlPinPayment");
  }
}

module.exports = { VisaCreditCard };
