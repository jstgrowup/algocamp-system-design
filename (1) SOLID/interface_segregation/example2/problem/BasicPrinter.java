package interface_segregation.example2.problem;

/**
 * ISP violation: BasicPrinter only prints, but is forced to depend on scan/fax/staple.
 * It either throws (breaks callers, also an LSP problem) or does nothing (silently lies).
 */
public class BasicPrinter implements Machine {

    @Override
    public void print(String doc) {
        System.out.println("BasicPrinter prints " + doc);
    }

    @Override
    public void scan(String doc) {
        throw new UnsupportedOperationException("BasicPrinter cannot scan");
    }

    @Override
    public void fax(String doc) {
        throw new UnsupportedOperationException("BasicPrinter cannot fax");
    }

    @Override
    public void staple(String doc) {
        // no-op: the caller thinks the pages were stapled, but nothing happened
    }
}
