# LSP example 2: Rectangle / Square

## Interview question
"A square is a rectangle, so `Square extends Rectangle`. Is that a good design? What can go wrong?"

## What is wrong
**Liskov Substitution Principle** is broken. `Rectangle` has an implicit contract: after `setWidth(w)` the width is `w` and the height is unchanged. `Square` overrides the setters to change both sides, so a method taking a `Rectangle` that sets width 5 and height 4 expects area 20 but gets 16 for a `Square`. The subclass breaks the parent's postcondition.

## Fix
Do not inherit. Make `Shape` (with `area()`) the common type and let `Rectangle` and `Square` be immutable siblings. With no setters there is no mutation contract to violate.

## Follow-up questions
- **Is-a vs behaves-as?** A square is-a rectangle in maths, but a mutable `Square` does not behave like a mutable `Rectangle`; inheritance must follow behavior.
- **Which contract rule is broken?** The postcondition of `setWidth` ("height unchanged") is weakened, and the invariant "width and height are independent" is not kept.
- **Could we keep inheritance?** Only if the type is immutable and no caller relies on independent sides; a shared `Shape` interface is simpler and safer.

## Run
From the `(1) SOLID` folder:
```
javac -d out liskov_substitution/example2/problem/*.java liskov_substitution/example2/better/*.java
java -cp out liskov_substitution.example2.problem.Main
java -cp out liskov_substitution.example2.better.Main
```
