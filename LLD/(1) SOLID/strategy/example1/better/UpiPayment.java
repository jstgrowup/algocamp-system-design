package strategy.example1.better;

/** Concrete strategy: UPI. */
public class UpiPayment implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("UPI: collect request sent for " + amount);
    }
}
