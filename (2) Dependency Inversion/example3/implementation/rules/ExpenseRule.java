package di.example3.rules;

import di.example3.models.Expense;
import java.util.Optional;

/**
 * A rule that looks at ONE expense (Strategy pattern: each rule is a strategy).
 *
 * check() returns:
 *   - Optional.of(violation) when the expense breaks the rule
 *   - Optional.empty()       when the expense follows the rule
 * Optional makes "no violation" explicit instead of returning null.
 */
public interface ExpenseRule {

    Optional<Violation> check(Expense e);
}
