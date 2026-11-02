package strategy.example1.better;

/** Concrete strategy: net banking. */
public class NetBankingPayment implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("NetBanking: redirecting to bank for " + amount);
    }
}
