package solid.isp.example1.problem;

/**
 * PROBLEM: one "fat" parent that every card must fully implement.
 *
 * What is wrong here
 * ------------------
 * 1. ISP (Interface Segregation) is violated.
 *    CreditCard lists every payment behaviour (swipe, refund, online, tap, intl),
 *    so every child is FORCED to implement all of them, even ones it can't do.
 *    Rupay can't do international payments but still has to define intlPayment().
 *    Diners isn't qualified for refunds but still has to define doRefund().
 *    Tomorrow Amex and Visa accept PIN for international payments: intlPinPayment()
 *    goes on the parent, so Diners, MasterCard and Rupay must define it too and
 *    can only throw. A class should never be forced to depend on methods it
 *    doesn't need.
 *
 * 2. LSP (Liskov Substitution) is violated.
 *    A child must work anywhere the parent is used. Code that holds a CreditCard
 *    and calls card.intlPayment() works for Visa but crashes for Rupay, because
 *    RupayCreditCard.intlPayment() throws. Likewise card.doRefund() works for
 *    Visa but crashes for Diners. Neither is safely substitutable.
 *
 * 3. OCP (Open/Closed) is violated.
 *    Say a new behaviour (UPI) arrives. Adding upiPayment() to the parent means
 *    editing the parent AND every child. See the commented-out upiPayment()
 *    in the parent and in each child file: that is the edit you'd have to make
 *    in six places for one new feature. New behaviour should be an extension,
 *    not a modification of code that already works.
 *
 * 4. Polymorphism is broken for UPI.
 *    Rupay adds upiPayment() but it isn't on the parent, so a caller holding a
 *    CreditCard can't call it without checking the concrete class first.
 *
 * 5. Copy-paste. Five near-identical classes, and a fix in one is easy to miss
 *    in the others. (The original Java has slips like intlPayment printing
 *    "upiPayment".)
 *
 * About @Override
 * ---------------
 * In Java, @Override tells the compiler "this replaces a parent method", and it
 * fails to compile if the parent has no such method. So a misspelled method name
 * (say swipeAndpay) is caught right away.
 */
public abstract class CreditCard {

    private String cardNumber;
    private String cardHolder;
    private String expiryDate;
    private int cvv;

    // Getters and Setters

    public abstract void swipeAndPay();

    public abstract void doRefund();

    public abstract void onlinePayment();

    public abstract void tapAndPay();

    // Not every card can do this -> ISP / LSP problem
    public abstract void intlPayment();

    // NEW requirement: Amex and Visa now accept PIN for international transactions.
    // Only 2 of 5 cards support it, yet all 5 are forced to implement it (ISP):
    // code is being forced to carry a method it doesn't need.
    public abstract void intlPinPayment();

    // public abstract void upiPayment(); // adding this = edit parent + all 5 children (OCP)
}
