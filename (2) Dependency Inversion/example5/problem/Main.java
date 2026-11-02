package example5.problem;

/** Runs the PROBLEM version: this Switch can only ever control a Bulb. */
public class Main {

    public static void main(String[] args) {
        Switch lightSwitch = new Switch();
        lightSwitch.press();
        lightSwitch.press();

        // A Fan or Heater cannot be plugged in without editing Switch.
    }
}
