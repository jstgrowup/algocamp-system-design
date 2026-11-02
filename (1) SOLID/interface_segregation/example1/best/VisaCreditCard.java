package interface_segregation.example1.best;

import interface_segregation.example1.best.strategies.refund.BankAccountRefundStrategy;
import interface_segregation.example1.best.strategies.refund.RefundStrategy;

/** Visa: base + refund + intl PIN capabilities; refund goes through the BankAccount strategy. */
public class VisaCreditCard extends CreditCard implements RefundCompatibleCreditCard, IntlPinCompatibleCreditCard {

    private final RefundStrategy refundStrategy;

    public VisaCreditCard() {
        this.refundStrategy = new BankAccountRefundStrategy();
    }

    @Override
    public void swipeAndPay() {
        System.out.println("Visa Credit Card swipeAndPay");
    }

    @Override
    public void onlinePayment() {
        System.out.println("Visa Credit Card onlinePayment");
    }

    @Override
    public void tapAndPay() {
        System.out.println("Visa Credit Card tapAndPay");
    }

    // capability: RefundCompatibleCreditCard
    @Override
    public void doRefund() {
        this.refundStrategy.doRefund();
    }

    // capability: IntlPinCompatibleCreditCard
    @Override
    public void intlPinPayment() {
        System.out.println("Visa Credit Card intlPinPayment");
    }
}
