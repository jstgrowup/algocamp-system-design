package solid.isp.example1.best;

/**
 * BEST (Strategy pattern added on top of BETTER): a small parent plus optional capabilities.
 *
 * What is followed here
 * ---------------------
 * 1. ISP (Interface Segregation) is followed.
 *    The parent has only what EVERY card can do: swipe, online, tap.
 *    Refund, UPI and intl PIN are optional capabilities, each its own interface
 *    (RefundCompatibleCreditCard, UpiCompatibleCreditCard,
 *    IntlPinCompatibleCreditCard). A card opts in with `implements`, e.g.
 *    class RupayCreditCard extends CreditCard
 *            implements UpiCompatibleCreditCard, RefundCompatibleCreditCard
 *    so no card is forced to carry behaviour it doesn't have.
 *
 * 2. LSP (Liskov Substitution) is followed.
 *    Every child fully honours the parent's three methods and none of them
 *    throws "not supported". Code holding a CreditCard can call swipeAndPay(),
 *    onlinePayment() or tapAndPay() on ANY card safely.
 *
 * 3. OCP (Open/Closed) is followed.
 *    A new card is a new class. A new capability (say, EMI) is a new interface
 *    used by only the cards that support it. The parent and the existing cards are
 *    not edited. Example: when Amex and Visa started accepting PIN for
 *    international transactions, only those two got intlPinPayment(); nothing
 *    else changed.
 *
 * Trade-offs, to be honest about
 * ------------------------------
 * - A caller can't assume every card has doRefund(), so it asks first with
 *   `card instanceof RefundCompatibleCreditCard`. That is the price of not
 *   forcing refund on everyone. (The Node version used isRefundCompatible().)
 * - International payment (intlPayment) from the problem version is dropped
 *   because only some cards support it. If you need it, model it as its own
 *   capability, the same way as refund and UPI.
 *
 * Strategy pattern (the "best" step)
 * ----------------------------------
 * Refund logic is no longer written inside every card. Each way of refunding is a
 * RefundStrategy (see strategies/refund/). A card holds a strategy and its
 * doRefund() just delegates to it, so cards that refund the same way share one
 * strategy class instead of copy-pasting code.
 */
public abstract class CreditCard {

    private String cardNumber;
    private String cardHolder;
    private String expiryDate;
    private int cvv;

    // Getters and Setters

    // Only what EVERY card supports lives here.
    public abstract void swipeAndPay();

    public abstract void onlinePayment();

    public abstract void tapAndPay();
}
