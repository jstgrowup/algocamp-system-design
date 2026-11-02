package strategy.example3.better;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Lookup table replaces the switch: vehicle name -> strategy.
        Map<String, FareStrategy> fares = Map.of(
                "BIKE", new BikeFare(),
                "AUTO", new AutoFare(),
                "CAB", new CabFare(),
                "LUXURY", new LuxuryFare()); // new entry only; no existing code changed

        for (String vehicle : new String[]{"BIKE", "AUTO", "CAB", "LUXURY"}) {
            Ride ride = new Ride(8, 2, fares.get(vehicle));
            System.out.println(vehicle + ": " + ride.fare());
        }
    }
}
