package strategy.example4.better;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        ParkingLot lot = new ParkingLot();

        // strategy picked by date (2026-11-02 is Monday, 2026-11-07 is Saturday)
        System.out.println("Car weekday : " + lot.issue(VehicleType.CAR, LocalDate.of(2026, 11, 2)).close(5));
        System.out.println("Car weekend : " + lot.issue(VehicleType.CAR, LocalDate.of(2026, 11, 7)).close(5));

        // strategy picked by vehicle
        System.out.println("Bike (flat) : " + lot.issueByVehicle(VehicleType.BIKE).close(5));

        // swap at runtime on the same ticket
        Ticket ticket = lot.issue(VehicleType.CAR, LocalDate.of(2026, 11, 2));
        System.out.println("Before pass : " + ticket.close(5));
        ticket.setPricing(new MonthlyPassPricing()); // new class, no existing code edited
        System.out.println("With pass   : " + ticket.close(5));
    }
}
