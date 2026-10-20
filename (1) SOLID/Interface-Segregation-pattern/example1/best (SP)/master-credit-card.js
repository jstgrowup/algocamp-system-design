const { CreditCard } = require("./credit-card");
const {
  BankAccountRefundStrategy,
} = require("./strategies/refund/bank-account-refund-strategy");
const { RefundCompatible } = require("./refund-compatible");

// Master: base + refund capability.
class MasterCreditCard extends RefundCompatible(CreditCard) {
  constructor() {
    super();
    this.refundStrategy = new BankAccountRefundStrategy();
  }

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
    this.refundStrategy.doRefund();
  }
}

module.exports = { MasterCreditCard };
