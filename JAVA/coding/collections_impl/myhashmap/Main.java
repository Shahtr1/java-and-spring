package collections_impl.myhashmap;

public class Main {

    public static void main(String[] args) {
        MyHashMap<String, Integer> map = new MyHashMap<>();

        // ✅ 1. Basic put() and get()
        map.put("apple", 1);
        map.put("banana", 2);
        System.out.println("apple => " + map.get("apple")); // Should print 1
        System.out.println("banana => " + map.get("banana")); // Should print 2

        // ✅ 2. Overwriting existing key
        map.put("apple", 10); // Update existing key
        System.out.println("apple (updated) => " + map.get("apple")); // Should print 10

        // ✅ 3. Trigger internal resizing (capacity doubles from 16 to 32)
        for (int i = 0; i < 20; i++) {
            map.put("key" + i, i); // Should cause resize when size > threshold
        }
        System.out.println("Size after inserts: " + map.size()); // Should be 22 (apple, 20 keys, Aa, BB)

        // ✅ 4. Hash collision test: some known keys like \"Aa\" and \"BB\" collide
        // under certain hash strategies
        map.put("Aa", 100);
        map.put("BB", 200);
        System.out.println("Aa => " + map.get("Aa")); // Should print 100
        System.out.println("BB => " + map.get("BB")); // Should print 200

        // ✅ 5. remove() behavior
        map.remove("banana");
        System.out.println("banana (removed) => " + map.get("banana")); // Should print null

        // ✅ 6. containsKey() checks
        System.out.println("map.containsKey('apple') => " + map.containsKey("apple")); // true
        System.out.println("map.containsKey('banana') => " + map.containsKey("banana")); // false

        // ✅ 7. size() and isEmpty() verification
        System.out.println("map.size() => " + map.size()); // Should be 22
        System.out.println("map.isEmpty() => " + map.isEmpty()); // Should be false

        // ✅ 8. Print internal state for debugging
        System.out.println("\nBucket State:");
        map.printBuckets(); // See how data is bucketed, especially after resizing

        for (MyHashMap.EntryView<String, Integer> view : map) {
            System.out.println(view.getKey() + " : " + view.getValue());
        }

        // ✅ 9. clear() test
        map.clear();
        System.out.println("\nAfter clear():");
        System.out.println("map.size() => " + map.size()); // 0
        System.out.println("map.isEmpty() => " + map.isEmpty()); // true
        map.printBuckets(); // Should show only nulls

        // ✅ 10. null key support test (your implementation allows 1 null key)
        System.out.println("\nNull key tests:");
        map.put(null, 99); // Should not throw — your version supports one null key
        System.out.println("null => " + map.get(null)); // 99
        System.out.println("map.containsKey(null) => " + map.containsKey(null)); // true
        map.remove(null);
        System.out.println("null (after remove) => " + map.get(null)); // null

        // Optional: Validate null-key cleanup
        System.out.println("map.containsKey(null) => " + map.containsKey(null)); // false
        System.out.println("Final size => " + map.size()); // Should be 0

    }
}
