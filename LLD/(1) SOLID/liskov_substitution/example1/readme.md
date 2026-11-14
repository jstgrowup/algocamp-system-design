# LSP example 1: Bird that can't fly

## Interview question
"You model `Bird` with a `fly()` method. Now add `Ostrich` and `Penguin`. What goes wrong, and how would you design it?"

## What is wrong
**Liskov Substitution Principle**: anywhere a `Bird` is expected, any subclass must work without surprises.
`Bird` promises `fly()`. `Ostrich` throws and `Penguin` silently does nothing, so code written against `Bird` crashes or misbehaves. The subclass weakens the postcondition ("the bird is flying afterwards"). The hierarchy modelled "is-a" in the real world but not "behaves-as".

## Fix
Keep only what every bird can do in `Bird` (`eat()`). Put `fly()` in a separate `Flyable` interface that only `Sparrow` implements. Code that needs flying asks for `Flyable`, so an `Ostrich` cannot even be passed (caught at compile time).

## Follow-up questions
- **Is-a vs behaves-as?** Inheritance must follow behavior the caller relies on, not real-world taxonomy.
- **Which design-by-contract rules does a subclass obey?** Preconditions cannot be strengthened, postconditions cannot be weakened, invariants must be kept, and no new checked exceptions.
- **Is throwing `UnsupportedOperationException` an acceptable fix?** No, it only moves the failure to runtime and forces callers to use `instanceof`/try-catch.
- **Which other principle is nearby?** ISP: a fat `Bird` with `fly()` is also an interface that is too wide.

## Run
From the `(1) SOLID` folder:
```
javac -d out liskov_substitution/example1/problem/*.java liskov_substitution/example1/better/*.java
java -cp out liskov_substitution.example1.problem.Main
java -cp out liskov_substitution.example1.better.Main
```
