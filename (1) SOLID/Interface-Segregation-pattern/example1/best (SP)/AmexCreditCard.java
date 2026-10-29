package solid.isp.example1.best;

import solid.isp.example1.best.strategies.refund.BankAccountRefundStrategy;
import solid.isp.example1.best.strategies.refund.RefundStrategy;

/** Amex: base + refund + intl PIN capabilities. (A new class, nothing existing edited.) */
public class AmexCreditCard extends CreditCard implements RefundCompatibleCreditCard, IntlPinCompatibleCreditCard {

    private final RefundStrategy refundStrategy;

    public AmexCreditCard() {
        this.refundStrategy = new BankAccountRefundStrategy();
    }

    @Override
    public void swipeAndPay() {
        System.out.println("Amex Credit Card swipeAndPay");
    }

    @Override
    public void onlinePayment() {
        System.out.println("Amex Credit Card onlinePayment");
    }

    @Override
    public void tapAndPay() {
        System.out.println("Amex Credit Card tapAndPay");
    }

    // capability: RefundCompatibleCreditCard
    @Override
    public void doRefund() {
        this.refundStrategy.doRefund();
    }

    // capability: IntlPinCompatibleCreditCard
    @Override
    public void intlPinPayment() {
        System.out.println("Amex Credit Card intlPinPayment");
    }
}
