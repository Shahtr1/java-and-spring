package Creational.singleton;

public class EagerSingleton {
    private static final EagerSingleton instance = new EagerSingleton();

    private EagerSingleton() {
    }

    // solved multithreading problem, but if we are not using this, still there would be an instance present
    public static EagerSingleton getInstance() {
        return instance;
    }
}
