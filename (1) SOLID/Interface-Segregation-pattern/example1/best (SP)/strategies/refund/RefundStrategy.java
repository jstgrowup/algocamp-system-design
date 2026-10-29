package solid.isp.example1.best.strategies.refund;

/**
 * STRATEGY PATTERN: the refund behaviour is pulled out of the cards.
 *
 * The problem: Master, Visa, Amex (and any card that refunds the same way) each
 * carried their own copy of the refund logic. Change the logic and you edit
 * every card; add a new way to refund and you edit the cards again.
 *
 * The fix: put each way of refunding in its own small class (a "strategy")
 * behind one common contract. A card holds a strategy and delegates to it
 * (this.refundStrategy.doRefund()). Cards that refund the same way share the
 * same strategy class instead of copy-pasting code.
 */
public interface RefundStrategy {

    void doRefund();
}
