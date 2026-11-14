package strategy.example4.problem;

public class Main {
    public static void main(String[] args) {
        ParkingLot lot = new ParkingLot();
        System.out.println("Car hourly : " + lot.fee(PricingType.HOURLY, VehicleType.CAR, 5));
        System.out.println("Car flat   : " + lot.fee(PricingType.FLAT, VehicleType.CAR, 5));
        System.out.println("Bike weekend: " + lot.fee(PricingType.WEEKEND, VehicleType.BIKE, 5));
    }
}
