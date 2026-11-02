package strategy.example2.better;

/** 15% off when buying 10 or more items. */
public class BulkDiscount implements DiscountStrategy {
    @Override
    public double apply(double total, int quantity) {
        return quantity >= 10 ? total * 0.85 : total;
    }
}
