package strategy.example1.better;

/**
 * Context: knows NOTHING about card/UPI/etc. It just calls the strategy it is given.
 * Never needs editing when a new payment method arrives (OCP), and has one job (SRP).
 */
public class Checkout {

    private final PaymentStrategy strategy;

    public Checkout(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void pay(double amount) {
        strategy.pay(amount);
    }
}
