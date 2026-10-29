package solid.isp.example1.best.strategies.refund;

/** One way to refund. Cards pick the strategy they want. */
public class SameInstrumentRefundStrategy implements RefundStrategy {

    @Override
    public void doRefund() {
        System.out.println("Refund initiated to the same instrument");
    }
}
