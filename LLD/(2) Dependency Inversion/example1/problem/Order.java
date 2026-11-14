package example1.problem;

import java.util.List;

/**
 * A plain data holder for an order. A Java record is an immutable class: the
 * compiler generates the constructor, getters (id(), customer(), ...), equals and toString.
 */
public record Order(long id, String customer, List<Item> items, double total) {

    /** One line of an order. */
    public record Item(String name, double price, int quantity) {
    }
}
