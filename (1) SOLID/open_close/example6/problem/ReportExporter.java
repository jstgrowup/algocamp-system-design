package open_close.example6.problem;

/**
 * PROBLEM: violates the Open/Closed Principle.
 * The switch on format lives inside the class, so every new format means editing it.
 */
public class ReportExporter {

    public String export(Report report, String format) {
        switch (format) {
            case "PDF":
                return "[PDF] " + report.title() + " | " + String.join(", ", report.lines());
            case "CSV":
                return String.join(",", report.lines());
            case "JSON":
                return "{\"title\":\"" + report.title() + "\",\"lines\":" + report.lines() + "}";
            // To add XML we must come back and add: case "XML": ...
            default:
                throw new IllegalArgumentException("Unsupported format: " + format);
        }
    }
}
