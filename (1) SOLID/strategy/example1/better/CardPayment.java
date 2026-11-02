package strategy.example1.better;

/** Concrete strategy: card (adds a 2% gateway fee). */
public class CardPayment implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        double fee = amount * 0.02;
        System.out.println("Card: charged " + (amount + fee) + " (fee " + fee + ")");
    }
}
