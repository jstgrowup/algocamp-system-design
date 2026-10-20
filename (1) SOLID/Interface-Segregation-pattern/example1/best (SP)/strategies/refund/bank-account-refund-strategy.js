const { RefundStrategy } = require("./refund-strategy");

class BankAccountRefundStrategy extends RefundStrategy {
  // @Override
  doRefund() {
    console.log("Refund initiated to the bank account");
  }
}

module.exports = { BankAccountRefundStrategy };
