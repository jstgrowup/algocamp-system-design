package di.example2.better.pawn;

import java.util.HashMap;
import java.util.Map;

/** The expensive part. Runs once for the base pawn, never for clones. */
public class MovementRules {

    private static final long SETUP_MS = 25; // pretend loading the rules takes a while

    public static Map<String, Boolean> loadMovementRules() {
        System.out.println("  loading movement rules...");
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
