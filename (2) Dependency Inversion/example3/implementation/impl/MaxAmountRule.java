package di.example3.impl;

import di.example3.models.Expense;
import di.example3.rules.ExpenseRule;
import di.example3.rules.Violation;
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
