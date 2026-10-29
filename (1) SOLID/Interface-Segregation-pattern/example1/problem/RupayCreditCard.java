package solid.isp.example1.problem;

public class RupayCreditCard extends CreditCard {

    @Override
    public void swipeAndPay() {
        System.out.println("Rupay Credit Card swipeAndPay");
    }

    @Override
    public void doRefund() {
        System.out.println("Rupay Credit Card doRefund");
    }

    @Override
    public void onlinePayment() {
        System.out.println("Rupay Credit Card onlinePayment");
    }

    @Override
    public void tapAndPay() {
        System.out.println("Rupay Credit Card tapAndPay");
    }

    // NOT an override: UPI isn't on the parent, so a caller holding a CreditCard can't reach it
    public void upiPayment() {
        System.out.println("Rupay Credit Card upiPayment");
    }

    @Override
    public void intlPayment() {
        // Rupay can't do international payments but the parent forces the method on it.
        // Throwing here is the tell-tale sign of an ISP + LSP violation.
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void intlPinPayment() {
        // Not supported, but the parent forces us to define it anyway (ISP violation).
        throw new UnsupportedOperationException("Rupay does not accept PIN for international payments.");
    }
}
