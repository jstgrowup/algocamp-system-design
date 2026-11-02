package strategy.example2.problem;

/**
 * PROBLEM: discount rules are hard-wired by customer type.
 * A new rule (say "student") = edit this method again (OCP),
 * and the rules can't be swapped or tested on their own (SRP).
 */
public class PriceCalculator {

    public double finalPrice(double price, int quantity, CustomerType type) {
        double total = price * quantity;
        if (type == CustomerType.FESTIVAL) {
            return total * 0.80;                    // flat 20% off
        } else if (type == CustomerType.MEMBER) {
            return total * 0.90;                    // 10% off
        } else if (type == CustomerType.BULK) {
            return quantity >= 10 ? total * 0.85 : total; // 15% off for 10+ items
        }
        return total;                               // REGULAR: no discount
    }
}
