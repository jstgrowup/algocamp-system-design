package di.example3.impl;

import di.example3.models.Expense;
import di.example3.rules.ExpenseRule;
import di.example3.rules.Violation;
import java.util.Optional;

/**
 * Rejects an expense just for being of a certain type.
 * The RuleRegistry decides WHICH expense types get this rule, so the rule itself
 * doesn't need to know about types (a rule is not tied to one expense type).
 */
public class DisallowRule implements ExpenseRule {

    @Override
    public Optional<Violation> check(Expense e) {
        return Optional.of(Violation.of("Expense Type " + e.getExpenseType()));
    }
}
