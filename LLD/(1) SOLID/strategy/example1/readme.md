# Example 1: Payment methods

## Interview question
"Design a checkout that supports card, UPI, wallet and net banking. Tomorrow product wants Pay Later. How do you add it without touching existing code?"

## What is wrong
`problem/Checkout.java` has one if/else on `PaymentType`.
- **OCP**: every new method means editing `pay()`, risking the others.
- **SRP**: one class knows card fees, UPI flow, wallet balance and bank redirects.

## Fix (Strategy)
- **Context**: `Checkout` holds a `PaymentStrategy` and just calls `pay(amount)`.
- **Strategy interface**: `PaymentStrategy` with one method, `pay`.
- **Concrete strategies**: `CardPayment`, `UpiPayment`, `WalletPayment`, `NetBankingPayment`.
- **Client picks**: `Main` chooses a strategy and passes it in; Pay Later is added as a lambda with no edits elsewhere.

## Strategy vs other patterns
- vs **State**: same shape, but the client chooses a strategy once; in State the object moves between states itself.
- vs plain polymorphism: Strategy is polymorphism applied to a behaviour you inject, not to the main class hierarchy.

## Follow-up questions
- *How does the client pick the strategy without if/else?* A `Map<PaymentType, PaymentStrategy>` or a factory.
- *Where do per-method validations go?* Inside each strategy.
- *Can one order use two methods (split payment)?* Yes: a composite strategy holding a list of strategies.

## Run
From the `(1) SOLID` folder:
```
javac -d out strategy/example1/problem/*.java strategy/example1/better/*.java
java -cp out strategy.example1.problem.Main
java -cp out strategy.example1.better.Main
```
