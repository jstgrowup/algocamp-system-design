package strategy.example2.better;

/** Context: holds a strategy and lets you swap it at runtime. */
public class Cart {

    private final double unitPrice;
    private final int quantity;
    private DiscountStrategy discount = new NoDiscount(); // sensible default

    public Cart(double unitPrice, int quantity) {
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public void setDiscount(DiscountStrategy discount) {
        this.discount = discount;
    }

    public double total() {
        return discount.apply(unitPrice * quantity, quantity);
    }
}
