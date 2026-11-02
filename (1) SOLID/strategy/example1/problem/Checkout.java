package strategy.example1.problem;

/**
 * PROBLEM: every payment method lives inside one if/else chain.
 * Adding "Pay Later" means EDITING this class (breaks OCP),
 * and the class knows card rules, UPI rules, wallet rules... (breaks SRP).
 */
public class Checkout {

    public void pay(PaymentType type, double amount) {
        if (type == PaymentType.CARD) {
            double fee = amount * 0.02; // 2% gateway fee
            System.out.println("Card: charged " + (amount + fee) + " (fee " + fee + ")");
        } else if (type == PaymentType.UPI) {
            System.out.println("UPI: collect request sent for " + amount);
        } else if (type == PaymentType.WALLET) {
            System.out.println("Wallet: deducted " + amount + " from balance");
        } else if (type == PaymentType.NET_BANKING) {
            System.out.println("NetBanking: redirecting to bank for " + amount);
        } else {
            throw new IllegalArgumentException("Unknown payment type: " + type);
        }
    }
}
