package example5.better;

public class Fan implements Switchable {

    @Override
    public void turnOn() {
        System.out.println("Fan is spinning");
    }

    @Override
    public void turnOff() {
        System.out.println("Fan stopped");
    }
}
