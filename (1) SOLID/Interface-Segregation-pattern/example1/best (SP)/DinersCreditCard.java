package solid.isp.example1.best;

import solid.isp.example1.best.strategies.refund.WalletRefundStrategy;
import solid.isp.example1.best.strategies.refund.RefundStrategy;

/** Diners: base + refund capability (Wallet strategy, as in the source repo). */
public class DinersCreditCard extends CreditCard implements RefundCompatibleCreditCard {

    private final RefundStrategy refundStrategy;

    public DinersCreditCard() {
        this.refundStrategy = new WalletRefundStrategy();
    }

    @Override
    public void swipeAndPay() {
        System.out.println("Diners Credit Card swipeAndPay");
    }

    @Override
    public void onlinePayment() {
        System.out.println("Diners Credit Card onlinePayment");
    }

    @Override
    public void tapAndPay() {
        System.out.println("Diners Credit Card tapAndPay");
    }

    // capability: RefundCompatibleCreditCard
    @Override
    public void doRefund() {
        this.refundStrategy.doRefund();
    }
}
