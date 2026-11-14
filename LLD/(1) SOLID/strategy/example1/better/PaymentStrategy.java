package strategy.example1.better;

/** Strategy interface: one way of paying. */
public interface PaymentStrategy {
    void pay(double amount);
}
