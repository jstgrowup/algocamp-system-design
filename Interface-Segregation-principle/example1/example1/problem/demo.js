const { AmexCreditCard } = require("./amex-credit-card");
const { DinersCreditCard } = require("./diners-credit-card");
const { MasterCardCreditCard } = require("./master-card-credit-card");
const { VisaCreditCard } = require("./visa-credit-card");
const { RupayCreditCard } = require("./rupay-credit-card");

// Every card is used through the common CreditCard contract (what LSP promises
// should be safe). The loop treats them all the same.
const cards = [new AmexCreditCard(), new DinersCreditCard(), new MasterCardCreditCard(), new VisaCreditCard(), new RupayCreditCard()];

for (const card of cards) {
  for (const action of ["doRefund", "intlPayment", "intlPinPayment"]) {
    try {
      card[action]();
    } catch (e) {
      // Diners (refund) and Rupay (intl) land here: they can't substitute for the parent -> LSP violation
      console.log(`${card.constructor.name}.${action}() blew up: ${e.message}`);
    }
  }
}
