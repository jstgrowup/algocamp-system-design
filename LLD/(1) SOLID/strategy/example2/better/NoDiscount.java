package strategy.example2.better;

public class NoDiscount implements DiscountStrategy {
    @Override
    public double apply(double total, int quantity) {
        return total;
    }
}
