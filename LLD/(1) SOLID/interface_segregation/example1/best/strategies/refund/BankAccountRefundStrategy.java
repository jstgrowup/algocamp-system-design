package interface_segregation.example1.best.strategies.refund;

/** Concrete strategy: refund goes to the bank account. Cards pick the strategy they want. */
public class BankAccountRefundStrategy implements RefundStrategy {

    @Override
    public void doRefund() {
        System.out.println("Refund initiated to the bank account");
    }
}
