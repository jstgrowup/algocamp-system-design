package interface_segregation.example1.problem;

/** Amex: implements every method of the fat parent (it supports them all). */
public class AmexCreditCard extends CreditCard {

    @Override
    public void swipeAndPay() {
        System.out.println("Amex Credit Card swipeAndPay");
    }

    @Override
    public void doRefund() {
        System.out.println("Amex Credit Card doRefund");
    }

    @Override
    public void onlinePayment() {
        System.out.println("Amex Credit Card onlinePayment");
    }

    @Override
    public void tapAndPay() {
        System.out.println("Amex Credit Card tapAndPay");
    }

    // @Override
    // public void upiPayment() { System.out.println("Amex Credit Card upiPayment"); }

    @Override
    public void intlPayment() {
        System.out.println("Amex Credit Card intlPayment");
    }

    @Override
    public void intlPinPayment() {
        System.out.println("Amex Credit Card intlPinPayment");
    }
}
