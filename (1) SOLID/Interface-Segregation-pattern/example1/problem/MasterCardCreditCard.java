package solid.isp.example1.problem;

public class MasterCardCreditCard extends CreditCard {

    @Override
    public void swipeAndPay() {
        System.out.println("MasterCard Credit Card swipeAndPay");
    }

    @Override
    public void doRefund() {
        System.out.println("MasterCard Credit Card doRefund");
    }

    @Override
    public void onlinePayment() {
        System.out.println("MasterCard Credit Card onlinePayment");
    }

    @Override
    public void tapAndPay() {
        System.out.println("MasterCard Credit Card tapAndPay");
    }

    // @Override
    // public void upiPayment() { System.out.println("MasterCard Credit Card upiPayment"); }

    @Override
    public void intlPayment() {
        System.out.println("MasterCard Credit Card intlPayment");
    }

    @Override
    public void intlPinPayment() {
        // Not supported, but the parent forces us to define it anyway (ISP violation).
        throw new UnsupportedOperationException("MasterCard does not accept PIN for international payments.");
    }
}
