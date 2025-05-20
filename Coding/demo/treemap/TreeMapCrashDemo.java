package demo.treemap;

import java.util.TreeMap;

public class TreeMapCrashDemo {
    public static void main(String[] args) {
        TreeMap<Object, String> map = new TreeMap<>();

        Object key1 = new Object();
        Object key2 = new Object();

        System.out.println("Inserting key1...");
        map.put(key1, "Value A"); // ✅ Works fine — first node, no comparison needed

        System.out.println("Inserting key2...");
        map.put(key2, "Value B"); // ❌ Fails — Java must compare key2 to key1
    }
}
