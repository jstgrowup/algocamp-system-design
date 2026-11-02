package open_close.example6.better;

public class JsonExporter implements Exporter {
    @Override
    public String export(Report report) {
        return "{\"title\":\"" + report.title() + "\",\"lines\":" + report.lines() + "}";
    }
}
