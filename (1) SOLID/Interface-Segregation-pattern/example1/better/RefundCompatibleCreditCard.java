package solid.isp.example1.better;

/**
 * Capability: a card that can refund.
 * A card opts in with `implements RefundCompatibleCreditCard`. For callers: only call doRefund()
 * on a card that is `instanceof RefundCompatibleCreditCard`.
 */
public interface RefundCompatibleCreditCard {

    void doRefund();
}
