package liskov_substitution.example1.better;

/** Only birds that can really fly promise fly(). Callers that need flying ask for a Flyable. */
public interface Flyable {

    void fly();
}
