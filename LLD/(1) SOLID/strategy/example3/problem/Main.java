package strategy.example3.problem;

public class Main {
    public static void main(String[] args) {
        FareCalculator calc = new FareCalculator();
        System.out.println("Bike: " + calc.fare(VehicleType.BIKE, 8, 2));
        System.out.println("Auto: " + calc.fare(VehicleType.AUTO, 8, 2));
        System.out.println("Cab : " + calc.fare(VehicleType.CAB, 8, 2));
    }
}
