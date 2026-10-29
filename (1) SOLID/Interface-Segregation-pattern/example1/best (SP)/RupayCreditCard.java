package solid.isp.example1.best;

import solid.isp.example1.best.strategies.refund.SameInstrumentRefundStrategy;
import solid.isp.example1.best.strategies.refund.RefundStrategy;

/** Rupay: base + refund + UPI capabilities. */
public class RupayCreditCard extends CreditCard implements UpiCompatibleCreditCard, RefundCompatibleCreditCard {

    private final RefundStrategy refundStrategy;

    public RupayCreditCard() {
        this.refundStrategy = new SameInstrumentRefundStrategy();
    }

    @Override
    public void swipeAndPay() {
        System.out.println("Rupay Credit Card swipeAndPay");
    }

    @Override
    public void onlinePayment() {
        System.out.println("Rupay Credit Card onlinePayment");
    }

    @Override
    public void tapAndPay() {
        System.out.println("Rupay Credit Card tapAndPay");
    }

    // capability: UpiCompatibleCreditCard
    @Override
    public void upiPayment() {
        System.out.println("Rupay Credit Card upiPayment");
    }

    // capability: RefundCompatibleCreditCard
    @Override
    public void doRefund() {
        // Printed in the source repo too (kept to stay close to it).
        System.out.println("Rupay Credit Card doRefund");
        this.refundStrategy.doRefund();
    }
}
