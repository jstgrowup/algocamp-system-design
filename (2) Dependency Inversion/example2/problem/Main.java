package example2.problem;

import java.util.Map;

/** Shows both problems: repeated expensive creation of Pawns and of Emails. */
public class Main {

    public static void main(String[] args) {
        // PROBLEM 1: 8 pawns, 8 identical expensive setups.
        long start = System.currentTimeMillis();
        Pawn[] pawns = new Pawn[8];
        for (int i = 0; i < 8; i++) {
            pawns[i] = new Pawn(i, 1, "white");
        }
        System.out.println(pawns.length + " pawns created, " + Pawn.expensiveSetups
                + " expensive setups, took ~" + (System.currentTimeMillis() - start) + "ms");
        System.out.println("The rules are identical for every pawn, only x, y and colour differ.\n");

        // PROBLEM 2: 4 orders, 4 reads of the same unchanged template.
        String[] customers = {"Asha", "Ravi", "Meera", "Kabir"};
        for (int i = 0; i < customers.length; i++) {
            int orderId = 101 + i;
            System.out.println("Order " + orderId + " created");
            new Email("order-confirmation.html")
                    .populate(Map.of("customer", customers[i], "orderId", orderId))
                    .send(customers[i].toLowerCase() + "@example.com");
        }
        System.out.println(customers.length + " orders, " + Email.fileReads
                + " template file reads. The template never changed.");
    }
}
