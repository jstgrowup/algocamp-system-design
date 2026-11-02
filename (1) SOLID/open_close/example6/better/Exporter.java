package open_close.example6.better;

/** The abstraction: one implementation per format. */
public interface Exporter {
    String export(Report report);
}
