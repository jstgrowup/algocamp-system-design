package solid.isp.example1.better;

/** Rupay: base + refund + UPI capabilities. */
public class RupayCreditCard extends CreditCard implements UpiCompatibleCreditCard, RefundCompatibleCreditCard {

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
        System.out.println("Rupay Credit Card doRefund");
    }
}
