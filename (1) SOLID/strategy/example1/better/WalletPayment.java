package strategy.example1.better;

/** Concrete strategy: wallet. */
public class WalletPayment implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("Wallet: deducted " + amount + " from balance");
    }
}
