package solid.isp.example1.better;

/** Amex: base + refund + intl PIN capabilities. (A new class, nothing existing edited.) */
public class AmexCreditCard extends CreditCard implements RefundCompatibleCreditCard, IntlPinCompatibleCreditCard {

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
        System.out.println("Amex Credit Card doRefund");
    }

    // capability: IntlPinCompatibleCreditCard
    @Override
    public void intlPinPayment() {
        System.out.println("Amex Credit Card intlPinPayment");
    }
}
