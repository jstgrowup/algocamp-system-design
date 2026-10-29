package solid.isp.example1.better;

/** Master: base + refund capability. */
public class MasterCreditCard extends CreditCard implements RefundCompatibleCreditCard {

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
        System.out.println("Master Credit Card doRefund");
    }
}
