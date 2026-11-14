package example5.better;

/**
 * FIX: Switch depends on the Switchable abstraction, not on Bulb.
 * The device is handed in through the constructor.
 */
public class Switch {

    private final Switchable device;
    private boolean on = false;

    public Switch(Switchable device) {
        this.device = device;
    }

    public void press() {
        on = !on;
        if (on) {
            device.turnOn();
        } else {
            device.turnOff();
        }
    }
}
