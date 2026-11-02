# ISP example 2: Multifunction machine

## Interview question
"You have a `Machine` interface with `print`, `scan`, `fax` and `staple`. A `BasicPrinter` only prints. What is wrong, and how do you redesign it?"

## What is wrong
**Interface Segregation Principle**: no class should be forced to depend on methods it doesn't use. `BasicPrinter` has to implement `scan`, `fax` and `staple`, so it throws or does nothing. Callers holding a `Machine` can't trust it, which also breaks LSP. Any new method on `Machine` forces a change in every implementer.

## Fix
Split into small role interfaces: `Printer`, `Scanner`, `Fax`, `Stapler`. `BasicPrinter` implements `Printer` only; `OfficeMachine` implements all four. Methods ask for the smallest interface they need, e.g. `scanContract(Scanner)`.

## Follow-up questions
- **Can't we just throw `UnsupportedOperationException`?** It hides the problem until runtime; with small interfaces the compiler stops the mistake.
- **Is one-method-per-interface always right?** No, group methods that always change and are used together (e.g. `Printer` with `print` and `cancel`).
- **How do you add a new capability?** Add a new interface (e.g. `Emailer`); existing classes stay untouched (also OCP).

## Run
From the `(1) SOLID` folder:
```
javac -d out interface_segregation/example2/problem/*.java interface_segregation/example2/better/*.java
java -cp out interface_segregation.example2.problem.Main
java -cp out interface_segregation.example2.better.Main
```
