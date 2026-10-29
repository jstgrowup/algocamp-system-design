package solid.isp.example1.best;

/**
 * Capability: a card that can pay through UPI.
 * A card opts in with `implements UpiCompatibleCreditCard`. For callers: only call upiPayment()
 * on a card that is `instanceof UpiCompatibleCreditCard`.
 */
public interface UpiCompatibleCreditCard {

    void upiPayment();
}
