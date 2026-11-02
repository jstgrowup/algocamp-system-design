# OCP example 5: Shape area calculator

## Interview question
"Write an `AreaCalculator` that returns the area of a Circle or a Rectangle. Now the product team wants Triangle, then Hexagon. What happens to your code?"

## What is wrong
**Open/Closed Principle**: software should be open for extension, closed for modification.
- `AreaCalculator` has an `if / else if` chain on the shape type; each new shape means editing it.
- Shapes share no common type, so the calculator must check `instanceof` for each.
- A forgotten branch fails at runtime ("Unknown shape"), not at compile time.
- Every edit risks breaking shapes that already worked.

## Fix
Introduce `Shape { double area(); }`. Each shape implements its own area; `AreaCalculator` just calls `shape.area()` (polymorphism). Adding `Triangle` is one new file in `better/`, with zero edits to existing classes.

## Follow-up questions
- *Where did the if/else go?* Into the type system: the JVM picks the right `area()` at runtime (dynamic dispatch).
- *Is the OCP ever acceptable to break?* For a tiny, stable set of cases a `switch` is fine; abstract when you see the second or third variant.
- *Could a sealed interface do this?* Yes, and an exhaustive `switch` gives compile-time safety when the set is closed; the interface fits better when others must add shapes.

## Run
From the `(1) SOLID` folder:
```
javac -d out $(find open_close/example5 -name '*.java')
java -cp out open_close.example5.problem.Main
java -cp out open_close.example5.better.Main
```
