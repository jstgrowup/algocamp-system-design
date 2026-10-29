package solid.isp.example1.best;

import solid.isp.example1.best.strategies.refund.BankAccountRefundStrategy;
import solid.isp.example1.best.strategies.refund.RefundStrategy;

/** Master: base + refund capability. */
public class MasterCreditCard extends CreditCard implements RefundCompatibleCreditCard {

    private final RefundStrategy refundStrategy;

    public MasterCreditCard() {
        this.refundStrategy = new BankAccountRefundStrategy();
    }

    @Override
    public void swipeAndPay() {
        System.out.println("Master Credit Card swipeAndPay");
    }

    @Override
    public void onlinePayment() {
        System.out.println("Master Credit Card onlinePayment");
    }

    @Override
    public void tapAndPay() {
        System.out.println("Master Credit Card tapAndPay");
    }

    // capability: RefundCompatibleCreditCard
    @Override
    public void doRefund() {
        this.refundStrategy.doRefund();
    }
}
