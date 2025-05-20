package map;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

import set.MyHashSet;

@SuppressWarnings("unchecked")
public class MyHashMap<K, V> implements Iterable<MyHashMap.EntryView<K, V>> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    protected Entry<K, V>[] table;
    protected int size;
    private int capacity;
    private final float loadFactor;
    protected int threshold;
    protected int modCount = 0;

    // Support for one null key (Java's HashMap behavior)
    private V nullKeyValue = null;
    private boolean hasNullKey = false;

    protected static class Entry<K, V> {
        final K key;
        V value;
        Entry<K, V> next;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }

    public static class EntryView<K, V> implements Map.Entry<K, V> {
        private final K key;
        private final V value;

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public EntryView(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof EntryView entryView))
                return false;
            if (Objects.equals(key, entryView.key) && Objects.equals(value, entryView.value))
                return true;
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        @Override
        public V setValue(V value) {
            throw new UnsupportedOperationException("Entry is immutable");
        }

    }

    public MyHashMap() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public MyHashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    public MyHashMap(int initialCapacity, float loadFactor) {
        if (initialCapacity <= 0 || loadFactor <= 0) {
            throw new IllegalArgumentException("Invalid capacity or load factor");
        }
        this.capacity = initialCapacity;
        this.loadFactor = loadFactor;
        this.threshold = (int) (capacity * loadFactor);
        this.table = new Entry[capacity];
        this.size = 0;
    }

    protected int hash(Object key) {
        int h = Objects.hashCode(key);
        return h ^ (h >>> 16);
    }

    public void put(K key, V value) {
        if (key == null) {
            if (!hasNullKey) {
                size++;
                modCount++;
            }

            nullKeyValue = value;
            hasNullKey = true;

            return;
        }

        int hash = hash(key);
        int index = (table.length - 1) & hash;
        Entry<K, V> current = table[index];

        while (current != null) {
            if (Objects.equals(current.key, key)) {
                current.value = value;
                return;
            }
            current = current.next;
        }

        Entry<K, V> newNode = new Entry<>(key, value);
        newNode.next = table[index];
        table[index] = newNode;
        size++;
        modCount++;

        if (size >= threshold) {
            resize();
        }
    }

    public V get(K key) {
        if (key == null) {
            return hasNullKey ? nullKeyValue : null;
        }

        int hash = hash(key);
        int index = (table.length - 1) & hash;
        Entry<K, V> current = table[index];

        while (current != null) {
            if (Objects.equals(current.key, key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    public boolean containsKey(K key) {
        if (key == null)
            return hasNullKey;
        return get(key) != null;
    }

    public boolean remove(K key) {
        if (key == null) {
            if (!hasNullKey)
                return false;
            hasNullKey = false;
            nullKeyValue = null;
            size--;
            modCount++;
            return true;
        }

        int hash = hash(key);
        int index = (table.length - 1) & hash;
        Entry<K, V> current = table[index];
        Entry<K, V> prev = null;

        while (current != null) {
            if (Objects.equals(current.key, key)) {
                if (prev == null) {
                    table[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                modCount++;
                return true;
            }
            prev = current;
            current = current.next;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        table = new Entry[capacity];
        size = 0;
        modCount++;
        hasNullKey = false;
        nullKeyValue = null;
    }

    protected void resize() {
        int newCapacity = capacity * 2;
        Entry<K, V>[] newTable = new Entry[newCapacity];

        for (int i = 0; i < capacity; i++) {
            Entry<K, V> node = table[i];
            while (node != null) {
                Entry<K, V> next = node.next;
                int newIndex = Math.abs(Objects.hashCode(node.key)) % newCapacity;
                node.next = newTable[newIndex];
                newTable[newIndex] = node;
                node = next;
            }
        }
        table = newTable;
        capacity = newCapacity;
        threshold = (int) (capacity * loadFactor);
        modCount++;
    }

    public MyHashSet<K> keySet() {
        MyHashSet<K> resKeys = new MyHashSet<>();

        if (hasNullKey) {
            resKeys.add(null);
        }

        for (int i = 0; i < capacity; i++) {
            Entry<K, V> current = table[i];
            while (current != null) {
                resKeys.add(current.key);
                current = current.next;
            }
        }

        return resKeys;
    }

    public Collection<V> values() {
        Collection<V> resValues = new ArrayList<>();
        if (hasNullKey) {
            resValues.add(nullKeyValue);
        }

        for (int i = 0; i < capacity; i++) {
            Entry<K, V> current = table[i];
            while (current != null) {
                resValues.add(current.value);
                current = current.next;
            }
        }
        return resValues;
    }

    public MyHashSet<EntryView<K, V>> entrySet() {
        MyHashSet<EntryView<K, V>> resEntries = new MyHashSet<>();

        if (hasNullKey) {
            resEntries.add(new EntryView<K, V>(null, nullKeyValue));
        }

        for (int i = 0; i < capacity; i++) {
            Entry<K, V> current = table[i];
            while (current != null) {
                EntryView<K, V> view = new EntryView<K, V>(current.key, current.value);
                resEntries.add(view);
                current = current.next;
            }
        }

        return resEntries;
    }

    class MyEntryIterator implements Iterator<EntryView<K, V>> {
        private final int expectedModCount;
        private int sizeCounter;
        private boolean nullKeySent = true;
        private int bucketIndex = 0;
        private Entry<K, V> current;

        MyEntryIterator() {
            sizeCounter = size;
            nullKeySent = !hasNullKey;
            expectedModCount = modCount;
        }

        @Override
        public boolean hasNext() {
            return sizeCounter > 0;
        }

        @Override
        public EntryView<K, V> next() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException("Map modified during iteration");
            }
            if (!nullKeySent) {
                nullKeySent = true;
                sizeCounter--;
                return new EntryView<K, V>(null, nullKeyValue);
            }
            while (current == null && bucketIndex < capacity) {
                current = table[bucketIndex++];
            }
            EntryView<K, V> result = new EntryView<>(current.key, current.value);
            current = current.next;
            sizeCounter--;
            return result;
        }

    }

    @Override
    public Iterator<EntryView<K, V>> iterator() {
        return new MyEntryIterator();
    }

    public void printBuckets() {
        if (hasNullKey) {
            System.out.println("[null=" + nullKeyValue + "]");
        }
        for (int i = 0; i < capacity; i++) {
            System.out.print("Bucket " + i + ": ");
            Entry<K, V> node = table[i];
            while (node != null) {
                System.out.print("[" + node.key + "=" + node.value + "] -> ");
                node = node.next;
            }
            System.out.println("null");
        }
    }
}
