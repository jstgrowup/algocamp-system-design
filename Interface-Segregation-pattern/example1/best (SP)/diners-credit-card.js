const { CreditCard } = require("./credit-card");
const { RefundCompatible } = require("./refund-compatible");
const {
  WalletRefundStrategy,
} = require("./strategies/refund/wallet-refund-strategy");

// Diners: base + refund capability (Wallet strategy, as in the source repo).
class DinersCreditCard extends RefundCompatible(CreditCard) {
  constructor() {
    super();
    this.refundStrategy = new WalletRefundStrategy();
  }

  // @Override
  swipeAndPay() {
    console.log("Diners Credit Card swipeAndPay");
  }

  // @Override
  onlinePayment() {
    console.log("Diners Credit Card onlinePayment");
  }

  // @Override
  tapAndPay() {
    console.log("Diners Credit Card tapAndPay");
  }

  // @Override (RefundCompatible)
  doRefund() {
    this.refundStrategy.doRefund();
  }
}

module.exports = { DinersCreditCard };
