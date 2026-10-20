// Capability: a card that opts in by extending this mixin. (In Java: interface RefundCompatibleCreditCard.)
// A mixin is plain JS: a function that takes a class and returns a subclass.
// doRefund() is abstract here, so a card that opts in must override it.
const RefundCompatible = (Base) =>
  class extends Base {
    doRefund() {
      throw new Error("abstract");
    }
  };

// For callers: only call doRefund() on cards that opted in.
const isRefundCompatible = (card) => typeof card.doRefund === "function";

module.exports = { RefundCompatible, isRefundCompatible };
