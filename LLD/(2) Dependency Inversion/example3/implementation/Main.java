package example3.implementation;

import example3.implementation.models.Expense;
import example3.implementation.models.ExpenseType;
import example3.implementation.registry.RuleRegistry;
import example3.implementation.rules.ExpenseRule;
import example3.implementation.rules.Violation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Runs the rule registry over a few sample expenses. This is the "engine":
 * it knows only the ExpenseRule interface, never a concrete rule, so new rules
 * are added in RuleRegistry without touching this loop (OCP).
 */
public class Main {

    public static void main(String[] args) {
        Map<ExpenseType, List<ExpenseRule>> byType = RuleRegistry.getExpenseRuleRegistry();
        List<ExpenseRule> forAll = RuleRegistry.getAllExpenseRulesRegistry();

        List<Expense> expenses = List.of(
                new Expense("e1", "t1", 60.0, ExpenseType.RESTAURANT),     // ok
                new Expense("e2", "t1", 90.0, ExpenseType.RESTAURANT),     // over the $75 meal limit
                new Expense("e3", "t1", 500.0, ExpenseType.AIRFARE),       // airfare not allowed (+ over the $200 global limit)
                new Expense("e4", "t1", 40.0, ExpenseType.ENTERTAINMENT)); // entertainment not allowed

        for (Expense expense : expenses) {
            // Rules for this expense type + rules that apply to every expense.
            List<ExpenseRule> rules = new ArrayList<>(byType.getOrDefault(expense.getExpenseType(), List.of()));
            rules.addAll(forAll);

            List<String> reasons = new ArrayList<>();
            for (ExpenseRule rule : rules) {
                Optional<Violation> violation = rule.check(expense);
                violation.ifPresent(v -> reasons.add(v.getMessage()));
            }
            System.out.println(expense.getExpenseId() + " " + expense.getExpenseType() + " $"
                    + expense.getAmountUsd() + " -> " + (reasons.isEmpty() ? "APPROVED" : "REJECTED " + reasons));
        }
    }
}
