package open_close.example6.better;

public class CsvExporter implements Exporter {
    @Override
    public String export(Report report) {
        return String.join(",", report.lines());
    }
}
