# SRP example 6: Library lending

## Interview question
"Design a library system. `LibraryService` lets members borrow books, calculates late fines on return and emails the member. Review this design."

## What is wrong
**Single Responsibility Principle** is broken: `LibraryService` changes for three different reasons.
- Borrowing rules (max books), fine calculation (price per day) and e-mail sending are all inside one class.
- Business rules are coupled to an infrastructure detail (SMTP), so testing fines triggers e-mails.
- Many people edit the same file for unrelated tickets.

## Fix
- `BorrowingPolicy`: who can borrow
- `FineCalculator`: fine maths (pure, easy to test)
- `EmailNotifier`: sending messages
- `LibraryService`: only orchestrates the workflow and receives the others via its constructor

## Follow-up questions
- *Isn't `LibraryService` still doing a lot?* It has one job, coordination; the decisions live in the collaborators.
- *How would you add SMS?* Extract a `Notifier` interface, add `SmsNotifier` (also touches Open/Closed and Dependency Inversion).
- *How do you test fines?* Call `FineCalculator.fineFor(5)` directly; no mail, no member needed.

## Run
From the `(1) SOLID` folder:
```
javac -d out $(find single_responsibility/example6 -name '*.java')
java -cp out single_responsibility.example6.problem.Main
java -cp out single_responsibility.example6.better.Main
```
