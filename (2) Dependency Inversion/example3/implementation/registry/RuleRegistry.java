package di.example3.registry;

import di.example3.impl.DisallowRule;
import di.example3.impl.MaxAmountRule;
import di.example3.models.ExpenseType;
import di.example3.rules.ExpenseRule;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Says which rules apply to which expense type, and which apply to every expense.
 * To change the policy, change this registry (the rule classes stay untouched).
 */
public class RuleRegistry {

    // Rules for a specific expense type: Map<ExpenseType, List<ExpenseRule>>
    // (The readme declares this method `void` but it returns the map, so the return type is fixed here.)
    public static Map<ExpenseType, List<ExpenseRule>> getExpenseRuleRegistry() {
        Map<ExpenseType, List<ExpenseRule>> registry = new HashMap<>();
        registry.put(ExpenseType.RESTAURANT, List.of(new DisallowRule(), new MaxAmountRule(75)));
        registry.put(ExpenseType.AIRFARE, List.of(new DisallowRule()));
        return registry;
    }

    // Rules applied to every expense, whatever its type
    public static List<ExpenseRule> getAllExpenseRulesRegistry() {
        return List.of(new MaxAmountRule(200));
    }
}
