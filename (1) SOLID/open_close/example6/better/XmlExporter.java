package open_close.example6.better;

/** The "new requirement": added as a NEW file. PdfExporter, CsvExporter and JsonExporter were not touched. */
public class XmlExporter implements Exporter {
    @Override
    public String export(Report report) {
        StringBuilder xml = new StringBuilder("<report title=\"" + report.title() + "\">");
        for (String line : report.lines()) {
            xml.append("<line>").append(line).append("</line>");
        }
        return xml.append("</report>").toString();
    }
}
