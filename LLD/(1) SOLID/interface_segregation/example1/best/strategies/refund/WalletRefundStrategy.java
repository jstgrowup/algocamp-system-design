package interface_segregation.example1.best.strategies.refund;

/** Concrete strategy: refund goes to the wallet. Cards pick the strategy they want. */
public class WalletRefundStrategy implements RefundStrategy {

    @Override
    public void doRefund() {
        System.out.println("Refund initiated to the wallet");
    }
}
