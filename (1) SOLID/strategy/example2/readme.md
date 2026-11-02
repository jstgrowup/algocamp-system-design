# Example 2: Pricing / discount rules

## Interview question
"An e-commerce cart gives different discounts to festival shoppers, members and bulk buyers. Design it so marketing can add new rules, and change a customer's rule at runtime."

## What is wrong
`problem/PriceCalculator.java` picks the discount with if/else on `CustomerType`.
- **OCP**: a new rule (student, coupon) edits the same method.
- **SRP**: one method owns every rule, so each is hard to test alone, and a rule can't be swapped at runtime.

## Fix (Strategy)
- **Context**: `Cart` holds the current `DiscountStrategy` and computes `total()`.
- **Strategy interface**: `DiscountStrategy.apply(total, quantity)`, marked `@FunctionalInterface`.
- **Concrete strategies**: `NoDiscount`, `FestivalDiscount`, `MemberDiscount`, `BulkDiscount`.
- **Client picks**: `Main.pick(...)` chooses one, `setDiscount` swaps it later, and a lambda defines a one-off coupon.

## Strategy vs other patterns
- vs **State**: a discount does not change because of what the cart did; the client decides it.
- vs **Decorator**: Strategy replaces one rule; Decorator stacks several (e.g. member + festival).

## Follow-up questions
- *Can discounts stack?* Yes: a composite strategy or Decorator chaining strategies.
- *How to avoid the switch in `pick`?* A `Map<String, DiscountStrategy>` or Spring-style registration.
- *Why a lambda?* The interface has one method, so small rules need no class.

## Run
From the `(1) SOLID` folder:
```
javac -d out strategy/example2/problem/*.java strategy/example2/better/*.java
java -cp out strategy.example2.problem.Main
java -cp out strategy.example2.better.Main
```
