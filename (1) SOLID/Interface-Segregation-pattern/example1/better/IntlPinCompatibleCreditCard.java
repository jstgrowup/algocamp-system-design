package solid.isp.example1.better;

/**
 * Capability: a card that accepts PIN for international transactions.
 * NEW requirement: Amex and Visa accept it. Only the cards that support it implement
 * this, so no card is forced to carry a method it doesn't need (ISP). The parent,
 * Diners, Master and Rupay are untouched (a bonus: OCP).
 * A card opts in with `implements IntlPinCompatibleCreditCard`. For callers: only call intlPinPayment()
 * on a card that is `instanceof IntlPinCompatibleCreditCard`.
 */
public interface IntlPinCompatibleCreditCard {

    void intlPinPayment();
}
