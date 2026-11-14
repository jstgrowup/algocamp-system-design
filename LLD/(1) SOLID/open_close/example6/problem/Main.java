package open_close.example6.problem;

import java.util.List;

/** Runner: old formats work; the new one fails until we modify ReportExporter. */
public class Main {

    public static void main(String[] args) {
        Report report = new Report("Sales", List.of("Jan=10", "Feb=20"));
        ReportExporter exporter = new ReportExporter();

        for (String format : List.of("PDF", "CSV", "JSON", "XML")) {
            try {
                System.out.println(format + " -> " + exporter.export(report, format));
            } catch (IllegalArgumentException e) {
                // Pain: the fix is to edit ReportExporter and re-test PDF, CSV and JSON as well.
                System.out.println(format + " -> FAILED: " + e.getMessage());
            }
        }
    }
}
