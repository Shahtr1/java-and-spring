package Creational.prototype;

public class Main {
    public static void main(String[] args) {
        try {
            // Create a vehicle registry
            VehicleRegistry registry = new VehicleRegistry();

            // Clone a TwoWheelerVehicle creational.prototype
            TwoWheelerVehicle clonedTwoWheeler = (TwoWheelerVehicle) registry.getVehicle("TWO");
            System.out.println("Cloned Two Wheeler: ");
            System.out.println("Engine: " + clonedTwoWheeler.getEngine());
            System.out.println("Model: " + clonedTwoWheeler.getModel());
            System.out.println("Price: " + clonedTwoWheeler.getPrice());

            // Clone a FourWheelerVehicle creational.prototype
            FourWheelerVehicle clonedFourWheeler = (FourWheelerVehicle) registry.getVehicle("FOUR");
            System.out.println("\nCloned Four Wheeler: ");
            System.out.println("Engine: " + clonedFourWheeler.getEngine());
            System.out.println("Model: " + clonedFourWheeler.getModel());
            System.out.println("Price: " + clonedFourWheeler.getPrice());

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
