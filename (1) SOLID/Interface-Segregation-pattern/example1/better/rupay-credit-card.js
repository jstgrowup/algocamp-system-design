const { CreditCard } = require("./credit-card");
const { UpiCompatible } = require("./upi-compatible");
const { RefundCompatible } = require("./refund-compatible");

// Rupay: base + refund + UPI capabilities.
class RupayCreditCard extends UpiCompatible(RefundCompatible(CreditCard)) {
  // @Override
  swipeAndPay() {
    console.log("Rupay Credit Card swipeAndPay");
  }

  // @Override
  onlinePayment() {
    console.log("Rupay Credit Card onlinePayment");
  }

  // @Override
  tapAndPay() {
    console.log("Rupay Credit Card tapAndPay");
  }

  // @Override (RefundCompatible)
  doRefund() {
    console.log("Rupay Credit Card doRefund");
  }

  // @Override (UpiCompatible)
  upiPayment() {
    console.log("Rupay Credit Card upiPayment");
  }
}

module.exports = { RupayCreditCard };
