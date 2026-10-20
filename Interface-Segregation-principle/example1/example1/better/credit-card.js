// BETTER: a small parent plus optional capabilities.
//
// What is followed here
// ---------------------
// 1. ISP (Interface Segregation) is followed.
//    The parent has only what EVERY card can do: swipe, online, tap.
//    Refund, UPI and intl PIN are optional capabilities, each in its own file
//    (refund-compatible.js, upi-compatible.js, intl-pin-compatible.js). A card opts
//    in by extending the capability mixin, e.g.
//    class RupayCreditCard extends UpiCompatible(RefundCompatible(CreditCard)),
//    so no card is forced to carry behaviour it doesn't have.
//    In Java these are the interfaces RefundCompatibleCreditCard and
//    UpiCompatibleCreditCard.
//
// 2. LSP (Liskov Substitution) is followed.
//    Every child fully honours the parent's three methods and none of them
//    throws "not supported". Code holding a CreditCard can call swipeAndPay(),
//    onlinePayment() or tapAndPay() on ANY card safely.
//
// 3. OCP (Open/Closed) is followed.
//    A new card is a new file. A new capability (say, EMI) is a new mixin file
//    used by only the cards that support it. The parent and the existing cards are not
//    edited. Example: when Amex and Visa started accepting PIN for international
//    transactions, only those two got intlPinPayment(); nothing else changed.
//
// Trade-offs, to be honest about
// ------------------------------
// - A caller can't assume every card has doRefund(), so it asks first
//   (isRefundCompatible). That is the price of not forcing refund on everyone.
//   In Java the compiler enforces this via the interface type.
// - JS has no interfaces: the check is duck typing (does the method exist?) and
//   a mixin is only a native stand-in for `implements`. If a card extends
//   RefundCompatible but forgets to override doRefund(), calling it throws
//   "abstract" instead of silently doing nothing.
// - International payment (intlPayment) from the problem version is dropped
//   because only some cards support it. If you need it, model it as its own
//   capability, the same way as refund and UPI.
class CreditCard {
  constructor(cardNumber, cardHolder, expiryDate, cvv) {
    this.cardNumber = cardNumber;
    this.cardHolder = cardHolder;
    this.expiryDate = expiryDate;
    this.cvv = cvv;
  }

  // JS has no abstract methods, so "abstract" = a method that throws if not overridden.
  // Only what EVERY card supports lives here.
  swipeAndPay() {
    throw new Error("abstract");
  }
  onlinePayment() {
    throw new Error("abstract");
  }
  tapAndPay() {
    throw new Error("abstract");
  }
}

module.exports = { CreditCard };
