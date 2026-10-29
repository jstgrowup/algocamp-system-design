package di.example2.problem;

import java.util.HashMap;
import java.util.Map;

/**
 * PROBLEM 1: creating many pawns from scratch.
 *
 * A chess board needs 8 pawns per side. Each one has its own position, so each
 * is configured when it is created.
 *
 * What is wrong
 * -------------
 * Building a Pawn is expensive (here: loading its movement rules). Every
 * `new Pawn(...)` repeats that same expensive work, even though all pawns of a
 * colour start with identical rules. Only x, y and colour differ.
 *
 * The repetition (what the Node file does at the bottom):
 *     Pawn pawn1 = new Pawn(0, 1, "white");   // expensive setup #1
 *     Pawn pawn2 = new Pawn(1, 1, "white");   // expensive setup #2
 *     ... up to pawn8                         // expensive setup #8
 */
public class Pawn {

    private static final long SETUP_MS = 25; // pretend loading the rules takes a while
    static int expensiveSetups = 0;

    private final Map<String, Boolean> rules;
    private int x;
    private int y;
    private String color;

    public Pawn(int x, int y, String color) {
        this.rules = loadMovementRules(); // expensive, repeated for every pawn
        this.x = x;
        this.y = y;
        this.color = color;
    }

    static Map<String, Boolean> loadMovementRules() {
        expensiveSetups++;
        System.out.println("  loading movement rules (setup #" + expensiveSetups + ")...");
        long end = System.currentTimeMillis() + SETUP_MS;
        while (System.currentTimeMillis() < end) {
            // busy wait, stands in for real loading work
        }
        Map<String, Boolean> rules = new HashMap<>();
        rules.put("forwardOnly", true);
        rules.put("firstMoveTwoSquares", true);
        rules.put("capturesDiagonally", true);
        return rules;
    }
}
