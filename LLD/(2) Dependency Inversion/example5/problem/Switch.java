package example5.problem;

/**
 * PROBLEM: Switch (high-level) creates and controls a concrete Bulb (low-level).
 *
 * Why this violates DIP
 *   The switching logic is welded to `new Bulb()`.
 *
 * What it costs you
 *   - A Fan or a Heater needs a copy of Switch, or edits to it.
 *   - You cannot test Switch without a real Bulb.
 */
public class Switch {

    private final Bulb bulb = new Bulb(); // hard-wired
    private boolean on = false;

    public void press() {
        on = !on;
        if (on) {
            bulb.on();
        } else {
            bulb.off();
        }
    }
}
