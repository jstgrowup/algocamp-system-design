package solid.isp.example1.problem;

public class DinersCreditCard extends CreditCard {

    @Override
    public void swipeAndPay() {
        System.out.println("Diners Credit Card swipeAndPay");
    }

    @Override
    public void doRefund() {
        // Diners isn't qualified for refunds but the parent forces the method on it.
        // Throwing here is the tell-tale sign of an ISP + LSP violation.
        throw new UnsupportedOperationException("Diners card is not qualified for refund.");
    }

    @Override
    public void onlinePayment() {
        System.out.println("Diners Credit Card onlinePayment");
    }

    @Override
    public void tapAndPay() {
        System.out.println("Diners Credit Card tapAndPay");
    }

    // @Override
    // public void upiPayment() { System.out.println("Diners Credit Card upiPayment"); }

    @Override
    public void intlPayment() {
        System.out.println("Diners Credit Card intlPayment");
    }

    @Override
    public void intlPinPayment() {
        // Not supported, but the parent forces us to define it anyway (ISP violation).
        throw new UnsupportedOperationException("Diners card does not accept PIN for international payments.");
    }
}
