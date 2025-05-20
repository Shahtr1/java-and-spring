package Creational.singleton;

import java.io.Serial;
import java.io.Serializable;

public class SerializableSingleton implements Serializable {
    private static SerializableSingleton instance;

    private SerializableSingleton() {
    }

    public static SerializableSingleton getInstance() {
        if (instance == null) {
            instance = new SerializableSingleton();
        }
        return instance;
    }

    /**
     * This is the key method responsible during deserialization process
     * This method get invoked, and we are returning already created object
     *
     * @return
     */
    @Serial
    private Object readResolve() {
        return instance;
    }
}
