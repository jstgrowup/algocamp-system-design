// PROBLEM: one "fat" parent that every card must fully implement.
//
// What is wrong here
// ------------------
// 1. ISP (Interface Segregation) is violated.
//    CreditCard lists every payment behaviour (swipe, refund, online, tap, intl),
//    so every child is FORCED to implement all of them, even ones it can't do.
//    Rupay can't do international payments but still has to define intlPayment().
//    Diners isn't qualified for refunds but still has to define doRefund().
//    Tomorrow Amex and Visa accept PIN for international payments: intlPinPayment()
//    goes on the parent, so Diners, MasterCard and Rupay must define it too and
//    can only throw. A class should never be forced to depend on methods it
//    doesn't need.
//
// 2. LSP (Liskov Substitution) is violated.
//    A child must work anywhere the parent is used. Code that holds a CreditCard
//    and calls card.intlPayment() works for Visa but crashes for Rupay, because
//    RupayCreditCard.intlPayment() throws. Likewise card.doRefund() works for
//    Visa but crashes for Diners. Neither is safely substitutable.
//
// 3. OCP (Open/Closed) is violated.
//    Say a new behaviour (UPI) arrives. Adding upiPayment() to the parent means
//    editing the parent AND every child. See the commented-out upiPayment()
//    in the parent and in each child file: that is the edit you'd have to make
//    in six places for one new feature. New behaviour should be an extension,
//    not a modification of code that already works.
//
// 4. Polymorphism is broken for UPI.
//    Rupay adds upiPayment() but it isn't on the parent, so a caller holding a
//    CreditCard can't call it without checking the concrete class first.
//
// 5. Copy-paste. Five near-identical classes, and a fix in one is easy to miss
//    in the others. (The original Java has slips like intlPayment printing
//    "upiPayment".)
//
// About "@Override"
// -----------------
// In Java, @Override tells the compiler "this replaces a parent method", and it
// fails to compile if the parent has no such method. JS has no such annotation,
// so the "// @Override" comments in the child files are documentation only. If
// you misspell a method name in JS (say swipeAndpay), it silently becomes a NEW
// method and the parent's version that throws "abstract" runs instead.
class CreditCard {
  constructor(cardNumber, cardHolder, expiryDate, cvv) {
    this.cardNumber = cardNumber;
    this.cardHolder = cardHolder;
    this.expiryDate = expiryDate;
    this.cvv = cvv;
  }

  // JS has no abstract methods, so "abstract" = a method that throws if not overridden.
  swipeAndPay() {
    throw new Error("abstract");
  }
  doRefund() {
    throw new Error("abstract");
  }
  onlinePayment() {
    throw new Error("abstract");
  }
  tapAndPay() {
    throw new Error("abstract");
  }
  intlPayment() {
    throw new Error("abstract");
  } // not every card can do this -> ISP / LSP problem
  // NEW requirement: Amex and Visa now accept PIN for international transactions.
  // Only 2 of 5 cards support it, yet all 5 are forced to implement it (ISP):
  // code is being forced to carry a method it doesn't need.
  intlPinPayment() {
    throw new Error("abstract");
  }
  // upiPayment() { throw new Error("abstract"); } // adding this = edit parent + all 5 children (OCP)
}

module.exports = { CreditCard };
