# OCP example 6: Report exporter

## Interview question
"Build a `ReportExporter` that can export a report as PDF, CSV or JSON. Next sprint the client wants XML. How easily can your design handle that?"

## What is wrong
**Open/Closed Principle**: adding behaviour should not require modifying working code.
- `export(report, format)` is a `switch` on a format string; XML means editing that method.
- One class knows every format, so any change risks breaking the others.
- A typo in the format string is only caught at runtime.

## Fix
Define `Exporter { String export(Report report); }` and give each format its own class (`PdfExporter`, `CsvExporter`, `JsonExporter`). `XmlExporter` is added as a new file in `better/`; the existing exporters are untouched and only the wiring in `Main` gains one line.

## Follow-up questions
- *Where does the choice of format happen now?* At the composition root (here a `Map` in `Main`); the exporters hold no switch.
- *Which patterns is this?* Strategy (interchangeable algorithms), often paired with a factory/registry.
- *Doesn't the registry still change?* Yes, but it is one wiring line, not logic inside tested classes; with DI or `ServiceLoader` even that is automatic.

## Run
From the `(1) SOLID` folder:
```
javac -d out $(find open_close/example6 -name '*.java')
java -cp out open_close.example6.problem.Main
java -cp out open_close.example6.better.Main
```
