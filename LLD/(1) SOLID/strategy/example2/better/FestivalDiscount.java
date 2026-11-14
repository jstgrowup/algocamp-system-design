package strategy.example2.better;

/** Flat 20% off. */
public class FestivalDiscount implements DiscountStrategy {
    @Override
    public double apply(double total, int quantity) {
        return total * 0.80;
    }
}
