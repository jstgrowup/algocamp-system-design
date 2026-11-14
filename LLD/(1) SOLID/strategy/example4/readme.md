# Example 4: Parking lot pricing

## Interview question
"Design a parking lot. Fees can be hourly, flat per day or weekend pricing, and differ for bikes and cars. How do you add a monthly pass later?"

## What is wrong
`problem/ParkingLot.java` calculates the fee itself with nested if/else on `PricingType` and `VehicleType`.
- **OCP**: a new scheme (monthly pass) edits the fee method.
- **SRP**: the lot handles tickets and money rules together.

## Fix (Strategy)
- **Context**: `Ticket` is given a `PricingStrategy` and computes the fee on `close(hours)`.
- **Strategy interface**: `PricingStrategy.fee(vehicle, hours)`.
- **Concrete strategies**: `HourlyPricing`, `FlatPricing`, `WeekendPricing`, and later `MonthlyPassPricing`.
- **Client picks**: `ParkingLot` picks by date or vehicle when issuing a ticket; `ticket.setPricing(...)` swaps it at runtime.

## Strategy vs other patterns
- vs **State**: the pricing does not change by itself as the ticket moves on; someone outside chooses it. A ticket going ISSUED -> PAID -> CLOSED is State.
- vs plain polymorphism: subclassing `Ticket` per pricing would blow up with vehicle x pricing combinations.

## Follow-up questions
- *Where does the choice logic live?* In the lot (or a small factory), so tickets and strategies stay clean.
- *How to combine weekend and vehicle rules?* Compose strategies (see `WeekendPricing` reusing `HourlyPricing`).
- *What if the price must be fixed at entry?* Pass the strategy in at issue time and keep it final.

## Run
From the `(1) SOLID` folder:
```
javac -d out strategy/example4/problem/*.java strategy/example4/better/*.java
java -cp out strategy.example4.problem.Main
java -cp out strategy.example4.better.Main
```
