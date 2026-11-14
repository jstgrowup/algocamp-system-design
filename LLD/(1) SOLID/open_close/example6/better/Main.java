package open_close.example6.better;

import java.util.Map;

/**
 * Runner: code that uses an Exporter never changes when a format is added.
 * Only this wiring (the registry) lists the available formats; the exporters themselves stay closed.
 */
public class Main {

    public static void main(String[] args) {
        Report report = new Report("Sales", java.util.List.of("Jan=10", "Feb=20"));

        Map<String, Exporter> exporters = Map.of(
            "PDF", new PdfExporter(),
            "CSV", new CsvExporter(),
            "JSON", new JsonExporter(),
            "XML", new XmlExporter() // <- the only line added to support XML
        );

        for (String format : new java.util.TreeSet<>(exporters.keySet())) {
            System.out.println(format + " -> " + exporters.get(format).export(report));
        }
    }
}
