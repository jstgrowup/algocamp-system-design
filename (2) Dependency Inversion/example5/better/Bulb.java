package example5.better;

public class Bulb implements Switchable {

    @Override
    public void turnOn() {
        System.out.println("Bulb is ON");
    }

    @Override
    public void turnOff() {
        System.out.println("Bulb is OFF");
    }
}
