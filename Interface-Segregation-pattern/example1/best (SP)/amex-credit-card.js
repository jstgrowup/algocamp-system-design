const { CreditCard } = require("./credit-card");
const {
  BankAccountRefundStrategy,
} = require("./strategies/refund/bank-account-refund-strategy");
const { IntlPinCompatible } = require("./intl-pin-compatible");
const { RefundCompatible } = require("./refund-compatible");

// Amex: base + refund + intl PIN capabilities. (A new file, nothing existing edited.)
class AmexCreditCard extends IntlPinCompatible(RefundCompatible(CreditCard)) {
  constructor() {
    super();
    this.refundStrategy = new BankAccountRefundStrategy();
  }

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
    this.refundStrategy.doRefund();
  }

  // @Override (IntlPinCompatible)
  intlPinPayment() {
    console.log("Amex Credit Card intlPinPayment");
  }
}

module.exports = { AmexCreditCard };
