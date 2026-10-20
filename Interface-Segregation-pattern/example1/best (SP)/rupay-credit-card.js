const { CreditCard } = require("./credit-card");
const { SameInstrumentRefundStrategy } = require("./strategies/refund/same-instrument-refund-strategy");
const { UpiCompatible } = require("./upi-compatible");
const { RefundCompatible } = require("./refund-compatible");

// Rupay: base + refund + UPI capabilities.
class RupayCreditCard extends UpiCompatible(RefundCompatible(CreditCard)) {
  constructor() {
    super();
    this.refundStrategy = new SameInstrumentRefundStrategy();
  }

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
    // Printed in the source repo too (kept to stay close to it).
    console.log("Rupay Credit Card doRefund");
    this.refundStrategy.doRefund();
  }

  // @Override (UpiCompatible)
  upiPayment() {
    console.log("Rupay Credit Card upiPayment");
  }
}

module.exports = { RupayCreditCard };
