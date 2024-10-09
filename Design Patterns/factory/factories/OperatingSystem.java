package factory.factories;

public abstract class OperatingSystem {
    private final String version;
    private final String architecture;

    public OperatingSystem(String version, String architecture) {
        this.version = version;
        this.architecture = architecture;
    }

    public String getVersion() {
        return version;
    }

    public String getArchitecture() {
        return architecture;
    }

    public abstract void changeDir(String dir);

    public abstract void removeDir(String dir);
}
