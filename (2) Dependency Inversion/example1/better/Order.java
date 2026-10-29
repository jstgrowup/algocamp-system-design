package di.example1.better;

import java.util.List;

/**
 * A plain data holder for an order (a Java record: immutable, with getters).
 * The Node version used a plain object { id, customer, items, total }.
 */
public record Order(long id, String customer, List<Item> items, double total) {

    /** One line of an order. */
    public record Item(String name, double price, int quantity) {
    }
}
