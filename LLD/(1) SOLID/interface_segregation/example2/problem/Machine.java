package interface_segregation.example2.problem;

/** "Fat" interface: every machine must implement every operation, even the ones it doesn't have. */
public interface Machine {

    void print(String doc);

    void scan(String doc);

    void fax(String doc);

    void staple(String doc);
}
