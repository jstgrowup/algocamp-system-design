# Example 4: Notification service (interview style)

## Interview question
"Our `NotificationService` sends an email when an order ships. Tomorrow product wants SMS too, and QA wants to test the service without sending real emails. Look at this code and tell me what you would change and why."

## What is wrong
`NotificationService` (high-level policy) does `new EmailSender()` (low-level detail). That breaks the Dependency Inversion Principle: high-level modules should not depend on low-level modules, both should depend on an abstraction. Result: adding SMS means editing the service, and a test cannot swap in a fake.

Note: DIP is a design principle (depend on abstractions). It is not the same thing as dependency injection, which is just one technique to achieve it.

## Fix
- Add a `MessageSender` interface with `send(to, message)`.
- `EmailSender` and `SmsSender` implement it.
- `NotificationService` takes a `MessageSender` in its constructor (constructor injection) and never uses `new` on a concrete sender.
- In a test, pass a lambda that records the message.

## Follow-up questions
- **DIP vs DI vs IoC?** DIP is the principle (depend on abstractions), DI is the technique (pass dependencies in), IoC is the wider idea that a framework or caller controls creation and flow.
- **Who creates the sender now?** The caller (`Main`, a factory, or a DI container like Spring), not the service.
- **How does it help testing?** You inject a fake or lambda, so tests are fast, offline and can assert on what was sent.
- **Why constructor injection?** The dependency is required, the field can be `final`, and the object is never half-built.

## Run
From `(2) Dependency Inversion`:
```
javac -d out $(find example4 -name '*.java')
java -cp out example4.problem.Main
java -cp out example4.better.Main
```
