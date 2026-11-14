package strategy.example1.better;

public class Main {
    public static void main(String[] args) {
        // The client (here: main) picks the strategy.
        new Checkout(new CardPayment()).pay(1000);
        new Checkout(new UpiPayment()).pay(500);
        new Checkout(new WalletPayment()).pay(250);
        new Checkout(new NetBankingPayment()).pay(3000);

        // New method "Pay Later" added WITHOUT touching Checkout or any strategy
        // (PaymentStrategy has one method, so a lambda is enough).
        PaymentStrategy payLater = amount -> System.out.println("PayLater: " + amount + " due in 15 days");
        new Checkout(payLater).pay(800);
    }
}
