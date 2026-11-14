package interface_segregation.example1.best.strategies.refund;

/** Concrete strategy: refund goes to the same instrument the customer paid with. Cards pick the strategy they want. */
public class SameInstrumentRefundStrategy implements RefundStrategy {

    @Override
    public void doRefund() {
        System.out.println("Refund initiated to the same instrument");
    }
}
