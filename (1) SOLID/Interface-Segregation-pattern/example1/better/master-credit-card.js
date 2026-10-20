const { CreditCard } = require("./credit-card");
const { RefundCompatible } = require("./refund-compatible");

// Master: base + refund capability.
class MasterCreditCard extends RefundCompatible(CreditCard) {
  // @Override
  swipeAndPay() {
    console.log("Master Credit Card swipeAndPay");
  }

  // @Override
  onlinePayment() {
    console.log("Master Credit Card onlinePayment");
  }

  // @Override
  tapAndPay() {
    console.log("Master Credit Card tapAndPay");
  }

  // @Override (RefundCompatible)
  doRefund() {
    console.log("Master Credit Card doRefund");
  }
}

module.exports = { MasterCreditCard };
