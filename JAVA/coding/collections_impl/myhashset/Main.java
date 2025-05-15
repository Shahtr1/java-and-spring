package collections_impl.myhashset;

public class Main {
    public static void main(String[] args) {
        MyHashSet<String> set = new MyHashSet<>();

        // ✅ Basic insertion
        set.add("apple");
        set.add("banana");
        set.add("cherry");
        System.out.println("Contains 'apple': " + set.contains("apple")); // true
        System.out.println("Contains 'banana': " + set.contains("banana")); // true
        System.out.println("Contains 'date': " + set.contains("date")); // false

        // ✅ Duplicate insert (should not add again)
        boolean added = set.add("apple");
        System.out.println("Adding 'apple' again: " + added); // false

        // ✅ Size and empty checks
        System.out.println("Size: " + set.size()); // 3
        System.out.println("Is empty: " + set.isEmpty()); // false

        // ✅ Remove elements
        set.remove("banana");
        System.out.println("Contains 'banana' after removal: " + set.contains("banana")); // false
        System.out.println("Size after removal: " + set.size()); // 2

        // ✅ Add more elements to test resize
        for (int i = 0; i < 20; i++) {
            set.add("val" + i);
        }
        System.out.println("Size after bulk insert: " + set.size());

        // ✅ Internal structure
        System.out.println("\nBucket State:");
        set.printBuckets();

        for (String text : set) {
            System.out.println("text is " + text);
        }

        // ✅ Clear the set
        set.clear();
        System.out.println("\nAfter clear():");
        System.out.println("Size: " + set.size());
        System.out.println("Is empty: " + set.isEmpty());
    }
}
