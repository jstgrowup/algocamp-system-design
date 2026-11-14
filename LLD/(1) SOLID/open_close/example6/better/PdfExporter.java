package open_close.example6.better;

public class PdfExporter implements Exporter {
    @Override
    public String export(Report report) {
        return "[PDF] " + report.title() + " | " + String.join(", ", report.lines());
    }
}
