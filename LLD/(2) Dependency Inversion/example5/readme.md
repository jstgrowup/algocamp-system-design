# Example 5: Light switch (interview style)

## Interview question
"Design a `Switch` that turns a light on and off. Now the client also wants it to control a fan and a heater. How does your design handle that?"

## What is wrong
`Switch` (high-level) creates and controls a concrete `Bulb` (low-level) with `new Bulb()`. That breaks the Dependency Inversion Principle: the switching logic depends on a detail instead of an abstraction. A Fan or Heater means copying or editing `Switch`.

Note: DIP is a design principle. It is not the same thing as dependency injection, which is just a way to hand the abstraction in.

## Fix
- Add a `Switchable` interface with `turnOn()` and `turnOff()`.
- `Bulb` and `Fan` implement it.
- `Switch` holds a `Switchable` received through its constructor.
- A new device (a `Heater` class or an anonymous class) works with no change to `Switch`.

## Follow-up questions
- **DIP vs DI vs IoC?** DIP is the principle, DI is passing dependencies in, IoC is giving up control of creation to a caller or framework.
- **Who creates the Bulb now?** The code that builds the `Switch` (here `Main`).
- **How does it help testing?** Pass a fake `Switchable` that records turnOn/turnOff calls and assert on them.
- **Is this also OCP?** Yes: `Switch` is closed for modification, open for new devices.

## Run
From `(2) Dependency Inversion`:
```
javac -d out $(find example5 -name '*.java')
java -cp out example5.problem.Main
java -cp out example5.better.Main
```
