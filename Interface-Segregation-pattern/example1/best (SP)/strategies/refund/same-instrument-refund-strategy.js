const { RefundStrategy } = require("./refund-strategy");

class SameInstrumentRefundStrategy extends RefundStrategy {
  // @Override
  doRefund() {
    console.log("Refund initiated to the same instrument");
  }
}

module.exports = { SameInstrumentRefundStrategy };
