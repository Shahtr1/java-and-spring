package Creational.prototype;

import java.util.HashMap;
import java.util.Map;

public class VehicleRegistry {
    private static Map<String, Vehicle> prototypeVehicles = new HashMap<>();

    static {
        prototypeVehicles.put("TWO", new TwoWheelerVehicle("120", "royal", 120000, false));
        prototypeVehicles.put("FOUR", new FourWheelerVehicle("120", "bmw", 120000, true, true));
    }

    public Vehicle getVehicle(String vehicle) throws CloneNotSupportedException {
        return prototypeVehicles.get(vehicle).clone();
    }
}
