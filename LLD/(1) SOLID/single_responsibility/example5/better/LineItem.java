package single_responsibility.example5.better;

/** One row on an invoice. A record = immutable data holder (auto constructor, getters, equals). */
public record LineItem(String name, int quantity, double unitPrice) {}
