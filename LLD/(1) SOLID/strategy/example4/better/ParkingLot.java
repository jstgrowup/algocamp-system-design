package strategy.example4.better;

import java.time.DayOfWeek;
import java.time.LocalDate;

/** Issues tickets and picks the strategy by day. It does no fee maths itself. */
public class ParkingLot {

    // Picking by time: weekend -> weekend pricing, otherwise hourly.
    public Ticket issue(VehicleType vehicle, LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        boolean weekend = day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
        return new Ticket(vehicle, weekend ? new WeekendPricing() : new HourlyPricing());
    }

    // Picking by vehicle: e.g. bikes always pay flat.
    public Ticket issueByVehicle(VehicleType vehicle) {
        return new Ticket(vehicle, vehicle == VehicleType.BIKE ? new FlatPricing() : new HourlyPricing());
    }
}
