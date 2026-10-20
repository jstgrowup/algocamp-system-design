const { RefundStrategy } = require("./refund-strategy");

class WalletRefundStrategy extends RefundStrategy {
  // @Override
  doRefund() {
    console.log("Refund initiated to the wallet");
  }
}

module.exports = { WalletRefundStrategy };
