package strategy.example2.better;

/** 10% off for members. */
public class MemberDiscount implements DiscountStrategy {
    @Override
    public double apply(double total, int quantity) {
        return total * 0.90;
    }
}
