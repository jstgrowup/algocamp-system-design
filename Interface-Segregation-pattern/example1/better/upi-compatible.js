// Capability: a card that opts in by extending this mixin. (In Java: interface UpiCompatibleCreditCard.)
// A mixin is plain JS: a function that takes a class and returns a subclass.
// upiPayment() is abstract here, so a card that opts in must override it.
const UpiCompatible = (Base) =>
  class extends Base {
    upiPayment() {
      throw new Error("abstract");
    }
  };

// For callers: only call upiPayment() on cards that opted in.
const isUpiCompatible = (card) => typeof card.upiPayment === "function";

module.exports = { UpiCompatible, isUpiCompatible };
