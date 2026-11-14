package strategy.example2.better;

/**
 * Strategy interface. @FunctionalInterface = exactly one abstract method,
 * so a lambda like (total, qty) -> total * 0.5 can be used as a strategy.
 */
@FunctionalInterface
public interface DiscountStrategy {
    double apply(double total, int quantity);
}
