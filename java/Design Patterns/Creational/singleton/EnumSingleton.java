package Creational.singleton;

/*
 * ENUM's constructor gets invoked by JVM not by User
 * Also Thread safe
 * It also solves the problem of Serialization as JVM takes care of that
 * */

public enum EnumSingleton {
    INSTANCE;

    public void doSomething() {
        System.out.println("Cool");
    }
}
