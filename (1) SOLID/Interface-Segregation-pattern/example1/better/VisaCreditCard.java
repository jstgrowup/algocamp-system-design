package solid.isp.example1.better;

/** Visa: base + refund + intl PIN capabilities. */
public class VisaCreditCard extends CreditCard implements RefundCompatibleCreditCard, IntlPinCompatibleCreditCard {

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
        System.out.println("Visa Credit Card doRefund");
    }

    // capability: IntlPinCompatibleCreditCard
    @Override
    public void intlPinPayment() {
        System.out.println("Visa Credit Card intlPinPayment");
    }
}
