package di.example3.rules;

import di.example3.models.Expense;
import java.util.List;
import java.util.Optional;

/**
 * A rule that looks at ALL the expenses of a trip together
 * (for example "a trip cannot exceed $2000 in total").
 *
 * check() returns the list of violations when the trip breaks the rule,
 * or Optional.empty() when it doesn't.
 */
public interface TripRule {

    Optional<List<Violation>> check(List<Expense> expenses);
}
