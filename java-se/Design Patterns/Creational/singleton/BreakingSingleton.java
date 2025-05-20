package Creational.singleton;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class BreakingSingleton {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        exampleSerialization();
        exampleReflection();
    }

    private static void exampleSerialization() throws IOException, ClassNotFoundException {
        LazySingleton lazySingleton = LazySingleton.getInstance();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new FileOutputStream("object.obj")
        );
        objectOutputStream.writeObject(lazySingleton);
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("object.obj"));
        LazySingleton deserializedLazy = (LazySingleton) objectInputStream.readObject();

        objectOutputStream.close();
        objectInputStream.close();

        System.out.println("Object 1: " + lazySingleton.hashCode());
        System.out.println("Object 2: " + deserializedLazy.hashCode());
    }

    private static void exampleReflection() throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor[] constructors = LazySingleton.class.getDeclaredConstructors();
        if (constructors.length > 0) {
            Constructor constructor = constructors[0];
            constructor.setAccessible(true);
            LazySingleton lazySingleton = (LazySingleton) constructor.newInstance();
            LazySingleton instance = LazySingleton.getInstance();
            System.out.println("Reflected hashcode creational.singleton : " + lazySingleton.hashCode());
            System.out.println("Singleton hashcode : " + instance.hashCode());
        }


    }
}
