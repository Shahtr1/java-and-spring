package Creational.factory;

import Creational.factory.factories.LinuxOperatingSystem;
import Creational.factory.factories.OperatingSystem;
import Creational.factory.factories.WindowsOperatingSystem;

public class OperatingSystemFactory {

    private OperatingSystemFactory() {
    }

    public static OperatingSystem getInstance(String type, String version, String architecture) {
        return switch (type) {
            case "WINDOWS" -> new WindowsOperatingSystem(version, architecture);
            case "LINUX" -> new LinuxOperatingSystem(version, architecture);
            default -> throw new IllegalArgumentException("Unsupported Operating System type");
        };
    }
}
