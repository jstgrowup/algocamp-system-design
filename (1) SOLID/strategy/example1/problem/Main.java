package strategy.example1.problem;

public class Main {
    public static void main(String[] args) {
        Checkout checkout = new Checkout();
        checkout.pay(PaymentType.CARD, 1000);
        checkout.pay(PaymentType.UPI, 500);
        checkout.pay(PaymentType.WALLET, 250);
        checkout.pay(PaymentType.NET_BANKING, 3000);
    }
}
