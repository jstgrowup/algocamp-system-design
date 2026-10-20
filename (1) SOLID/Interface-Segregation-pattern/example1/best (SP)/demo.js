const { isRefundCompatible } = require("./refund-compatible");
const { isUpiCompatible } = require("./upi-compatible");
const { isIntlPinCompatible } = require("./intl-pin-compatible");
const { AmexCreditCard } = require("./amex-credit-card");
const { DinersCreditCard } = require("./diners-credit-card");
const { MasterCreditCard } = require("./master-credit-card");
const { VisaCreditCard } = require("./visa-credit-card");
const { RupayCreditCard } = require("./rupay-credit-card");

const cards = [new AmexCreditCard(), new DinersCreditCard(), new MasterCreditCard(), new VisaCreditCard(), new RupayCreditCard()];

for (const card of cards) {
  card.swipeAndPay(); // every card supports the base behaviours, no try/catch needed (LSP)
  // Optional capabilities are asked for first, so nothing is ever "not supported" (ISP)
  if (isRefundCompatible(card)) card.doRefund();
  if (isUpiCompatible(card)) card.upiPayment();
  if (isIntlPinCompatible(card)) card.intlPinPayment(); // only Amex and Visa
}
