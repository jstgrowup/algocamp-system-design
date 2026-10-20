// Capability: a card that opts in by extending this mixin. (In Java: a new interface, e.g. IntlPinCompatibleCreditCard.)
// A mixin is plain JS: a function that takes a class and returns a subclass.
// intlPinPayment() is abstract here, so a card that opts in must override it.
// NEW requirement: Amex and Visa accept PIN for international transactions.
// Only the 2 cards that support it use this mixin, so no card is forced to carry
// one it doesn't need (ISP). The parent, Diners, Master and Rupay are untouched (a bonus: OCP).
const IntlPinCompatible = (Base) =>
  class extends Base {
    intlPinPayment() {
      throw new Error("abstract");
    }
  };

// For callers: only call intlPinPayment() on cards that opted in.
const isIntlPinCompatible = (card) => typeof card.intlPinPayment === "function";

module.exports = { IntlPinCompatible, isIntlPinCompatible };
