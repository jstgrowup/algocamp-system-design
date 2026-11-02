package example3.implementation.registry;

import example3.implementation.impl.DisallowRule;
import example3.implementation.impl.MaxAmountRule;
import example3.implementation.models.ExpenseType;
import example3.implementation.rules.ExpenseRule;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Says which rules apply to which expense type, and which apply to every expense.
 * To change the policy, change this registry (the rule classes stay untouched).
 * Callers depend only on the ExpenseRule interface, never on a concrete rule:
 * that is the Dependency Inversion / OCP idea in this example.
 */
public class RuleRegistry {

    // Rules for a specific expense type: Map<ExpenseType, List<ExpenseRule>>
    public static Map<ExpenseType, List<ExpenseRule>> getExpenseRuleRegistry() {
        Map<ExpenseType, List<ExpenseRule>> registry = new HashMap<>();
        registry.put(ExpenseType.RESTAURANT, List.of(new MaxAmountRule(75)));  // meals: max $75
        registry.put(ExpenseType.AIRFARE, List.of(new DisallowRule()));        // airfare: not allowed
        registry.put(ExpenseType.ENTERTAINMENT, List.of(new DisallowRule()));  // entertainment: not allowed
        return registry;
    }

    // Rules applied to every expense, whatever its type
    public static List<ExpenseRule> getAllExpenseRulesRegistry() {
        return List.of(new MaxAmountRule(200));
    }
}
