package example3.implementation.impl;

import example3.implementation.models.Expense;
import example3.implementation.rules.ExpenseRule;
import example3.implementation.rules.Violation;
import java.util.Optional;

/**
 * Rejects an expense that is more than the allowed amount.
 * The limit is passed in, so the same class can be used for $75, $200, ... and
 * can be reused by any expense type.
 */
public class MaxAmountRule implements ExpenseRule {

    private final double maxAmount;

    public MaxAmountRule(double maxAmount) {
        this.maxAmount = maxAmount;
    }

    @Override
    public Optional<Violation> check(Expense e) {
        if (e.getAmountUsd() > maxAmount) {
            return Optional.of(Violation.of("Expense amount is greater than the max amount"));
        }
        return Optional.empty();
    }
}
