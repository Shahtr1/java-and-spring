package singleton;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class FixingSingleton {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        exampleSerialization();
    }

    private static void exampleSerialization() throws IOException, ClassNotFoundException {
        SerializableSingleton lazySingleton = SerializableSingleton.getInstance();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new FileOutputStream("object.obj")
        );
        objectOutputStream.writeObject(lazySingleton);
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("object.obj"));
        SerializableSingleton deserializedLazy = (SerializableSingleton) objectInputStream.readObject();

        objectOutputStream.close();
        objectInputStream.close();

        System.out.println("Object 1: " + lazySingleton.hashCode());
        System.out.println("Object 2: " + deserializedLazy.hashCode());
    }

    private static void exampleReflection(){
        // just use ENUMSingleton for this

    }
}
