# Example 3: expense policy rules engine

Design a system that checks business expenses submitted by employees, so managers can stop misuse of corporate cards. It should:

1. Evaluate individual expenses against a set of rules.
2. Evaluate aggregated trip-level expenses against a set of rules.
3. Flag violations clearly, and be easy to extend.

**Input:** a list of expenses (`expenseId`, `tripId`, `amountUsd`, `expenseType`: restaurant, airfare, entertainment).

**Basic rules**
1. No restaurant expense can exceed $75.
2. No airfare expenses are allowed.
3. No entertainment expenses are allowed.
4. No single expense can exceed $200.

**Extended (trip-level) rules:** a trip cannot exceed $2000 in total; total meal expenses per trip cannot exceed $1000.

**Output:** each expense is APPROVED or REJECTED with reasons; each trip is OK or has VIOLATIONS with reasons.

## Why not one big if / else-if?

A loop with `if (rule 1) ... else if (rule 2) ...` breaks the Open/Closed Principle (every new rule means editing it) and Single Responsibility (one place holds all rule logic, and one rule can be complex).

Having one class per expense type is no better: the same rule (e.g. a max amount) applies to many types, so logic gets duplicated. **A rule must not be tied to one expense type.**

## Design: Strategy pattern + registry

- Each rule is a small strategy class behind the `ExpenseRule` interface. The engine only knows the interface (Dependency Inversion).
- `RuleRegistry` says which rules apply to which `ExpenseType`, and which apply to every expense. A policy change is a registry change, the rule classes stay untouched.
- Rules are tested in isolation: one rule, one small test.

| Package | Class | Role |
| --- | --- | --- |
| `...implementation.models` | `Expense`, `ExpenseType` | immutable data, no rules (ideally built with a builder) |
| `...implementation.rules` | `ExpenseRule`, `TripRule`, `Violation` | the abstractions and the result of a failed check |
| `...implementation.impl` | `DisallowRule`, `MaxAmountRule` | concrete rules |
| `...implementation.registry` | `RuleRegistry` | which rules apply where |
| `...implementation` | `Main` | runs the registry over sample expenses |

(`...` is `example3`.)

```java
public interface ExpenseRule {
    Optional<Violation> check(Expense e);       // empty = follows the rule
}

public interface TripRule {
    Optional<List<Violation>> check(List<Expense> expenses);
}

public class MaxAmountRule implements ExpenseRule {
    private final double maxAmount;
    public MaxAmountRule(double maxAmount) { this.maxAmount = maxAmount; }

    @Override
    public Optional<Violation> check(Expense e) {
        if (e.getAmountUsd() > maxAmount) {
            return Optional.of(Violation.of("Expense amount is greater than the max amount"));
        }
        return Optional.empty();
    }
}

// registry: rules per expense type, plus rules for every expense
registry.put(ExpenseType.RESTAURANT, List.of(new MaxAmountRule(75)));
registry.put(ExpenseType.AIRFARE, List.of(new DisallowRule()));
```

Run: `java -cp <out> example3.implementation.Main`.
