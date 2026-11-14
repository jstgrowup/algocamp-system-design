package interface_segregation.example2.better;

/** Implements only what it can do: nothing to throw, nothing to fake. */
public class BasicPrinter implements Printer {

    @Override
    public void print(String doc) {
        System.out.println("BasicPrinter prints " + doc);
    }
}
