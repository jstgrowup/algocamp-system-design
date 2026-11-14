package example5.better;

/** Runs the BETTER version: one Switch, any device. */
public class Main {

    public static void main(String[] args) {
        // 1. Same Switch class, different devices
        new Switch(new Bulb()).press();
        new Switch(new Fan()).press();

        // 2. One step further: a device Switch has never heard of.
        //    Switchable has two methods, so it is not a lambda target.
        //    We use an anonymous class; a new Heater class would work the same way.
        Switchable heater = new Switchable() {
            @Override
            public void turnOn() {
                System.out.println("Heater is warming up");
            }

            @Override
            public void turnOff() {
                System.out.println("Heater is cooling down");
            }
        };
        Switch heaterSwitch = new Switch(heater);
        heaterSwitch.press();
        heaterSwitch.press();
        // Switch.java was not edited to support the Heater.
    }
}
