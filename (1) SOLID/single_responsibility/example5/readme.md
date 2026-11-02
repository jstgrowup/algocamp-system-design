# SRP example 5: Invoice

## Interview question
"Design an `Invoice` class that holds line items, calculates the total with tax, prints itself and saves itself to the database. Now tell me what is wrong with your design."

## What is wrong
**Single Responsibility Principle**: a class should have only one reason to change. `Invoice` has three:
- calculation (tax/discount rules), presentation (`print`), persistence (`saveToDatabase`)
- A tax change, a new print format and a DB migration all edit the same file.
- You cannot unit test the maths without printing/DB code attached.

## Fix
Split by responsibility (see `better/`):
- `Invoice` + `LineItem`: data only
- `InvoiceCalculator`: tax and total
- `InvoicePrinter`: output format
- `InvoiceRepository`: storage

## Follow-up questions
- *Does SRP mean one method per class?* No, one reason to change (one actor/stakeholder), not one method.
- *Where would a PDF printer go?* A new class (e.g. `PdfInvoicePrinter`); extract an `InvoicePrinter` interface if there are several.
- *Is it OK to keep `calculateTotal()` inside Invoice?* Only if the rule is a stable part of the invoice data; tax rules change for business reasons, so keep them separate.

## Run
From the `(1) SOLID` folder:
```
javac -d out $(find single_responsibility/example5 -name '*.java')
java -cp out single_responsibility.example5.problem.Main
java -cp out single_responsibility.example5.better.Main
```
