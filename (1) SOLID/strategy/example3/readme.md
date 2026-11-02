# Example 3: Ride fare (Uber-like)

## Interview question
"Design fare calculation for a ride-hailing app with bike, auto and cab. We plan to add Luxury and EV cabs soon."

## What is wrong
`problem/FareCalculator.java` uses a `switch` on `VehicleType`.
- **OCP**: every new vehicle edits the switch.
- **SRP**: one class owns all pricing formulas, so changing the auto formula risks the cab one.

## Fix (Strategy)
- **Context**: `Ride` is built with a `FareStrategy` and returns `fare()`.
- **Strategy interface**: `FareStrategy.fare(km, waitMinutes)`.
- **Concrete strategies**: `BikeFare`, `AutoFare`, `CabFare`, and later `LuxuryFare`.
- **Client picks**: `Main` looks the strategy up in a `Map` by vehicle name; Luxury is only a new map entry.

## Same idea for shipping cost
A `ShippingCostStrategy.cost(weight, distance)` with `StandardShipping`, `ExpressShipping`, `InternationalShipping`. The `Order` (context) gets one, so adding "same-day delivery" is one new class.

## Strategy vs other patterns
- vs **Factory**: Factory decides which object to create; Strategy is the behaviour inside it. They are often paired (Map or factory picks the strategy).
- vs plain polymorphism: you could subclass `Ride` per vehicle, but then surge or night pricing would multiply the subclasses; Strategy keeps ride and pricing separate.

## Follow-up questions
- *How do you add surge pricing?* A `SurgeFare` wrapping any other `FareStrategy` (Decorator on a strategy).
- *Is a switch inside the factory still a violation?* It is a smaller, single place; a Map or registration removes it.
- *Can fare depend on time of day?* Pass a context object (time, city) into `fare(...)`.

## Run
From the `(1) SOLID` folder:
```
javac -d out strategy/example3/problem/*.java strategy/example3/better/*.java
java -cp out strategy.example3.problem.Main
java -cp out strategy.example3.better.Main
```
