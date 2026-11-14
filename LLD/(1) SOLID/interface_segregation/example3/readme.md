# ISP example 3: Worker

## Interview question
"A `Worker` interface has `work`, `eat` and `sleep`. Now you need a `Robot` worker. What is the problem and how do you fix it?"

## What is wrong
**Interface Segregation Principle**: `Robot` is forced to implement `eat()` and `sleep()`, which it doesn't need, so it throws or does nothing. A method like `lunchBreak(List<Worker>)` compiles fine and then crashes at runtime on the robot (an LSP break too).

## Fix
Split into `Workable`, `Eatable`, `Sleepable`. `HumanWorker` implements all three; `Robot` only `Workable`. Methods take the small interface they need: `lunchBreak(List<? extends Eatable>)` simply cannot receive a robot.

## Follow-up questions
- **How does ISP relate to LSP?** Fat interfaces push classes to fake or throw methods, which breaks substitutability; small interfaces avoid that.
- **Can a class implement many interfaces?** Yes, in Java a class can implement any number of interfaces, so composing small ones is cheap.
- **How small is small enough?** Split by the clients' needs: if different clients use different subsets of methods, split them.

## Run
From the `(1) SOLID` folder:
```
javac -d out interface_segregation/example3/problem/*.java interface_segregation/example3/better/*.java
java -cp out interface_segregation.example3.problem.Main
java -cp out interface_segregation.example3.better.Main
```
