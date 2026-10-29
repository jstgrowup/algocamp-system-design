package di.example2.better.pawn;

import java.util.HashMap;
import java.util.Map;

/**
 * BETTER (Prototype pattern): build one pawn, clone the rest.
 * `new Pawn(...)` does the expensive setup. clone() makes a cheap copy without it.
 *
 * In Java the usual way is a "copy constructor" (a private constructor that takes
 * another Pawn). It avoids the quirks of java.lang.Cloneable.
 */
public class Pawn {

    private Map<String, Boolean> rules;
    private int x;
    private int y;
    private String color;

    public Pawn(int x, int y, String color) {
        this.rules = MovementRules.loadMovementRules(); // expensive
        this.x = x;
        this.y = y;
        this.color = color;
    }

    // Copy constructor: copies the plain fields and skips the expensive setup.
    private Pawn(Pawn source) {
        this.x = source.x;
        this.y = source.y;
        this.color = source.color;
        // its own rules, so changing one pawn doesn't change the others
        this.rules = new HashMap<>(source.rules);
    }

    /** Cheap copy. `newX` / `newY` set what differs on the copy, for pawns the position. */
    public Pawn clone(int newX, int newY) {
        Pawn copy = new Pawn(this);
        copy.x = newX;
        copy.y = newY;
        return copy;
    }
}
