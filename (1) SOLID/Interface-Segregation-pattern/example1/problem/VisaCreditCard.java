package solid.isp.example1.problem;

public class VisaCreditCard extends CreditCard {

    @Override
    public void swipeAndPay() {
        System.out.println("Visa Credit Card swipeAndPay");
    }

    @Override
    public void doRefund() {
        System.out.println("Visa Credit Card doRefund");
    }

    @Override
    public void onlinePayment() {
        System.out.println("Visa Credit Card onlinePayment");
    }

    @Override
    public void tapAndPay() {
        System.out.println("Visa Credit Card tapAndPay");
    }

    // @Override
    // public void upiPayment() { System.out.println("Visa Credit Card upiPayment"); }

    @Override
    public void intlPayment() {
        System.out.println("Visa Credit Card intlPayment");
    }

    @Override
    public void intlPinPayment() {
        System.out.println("Visa Credit Card intlPinPayment");
    }
}
