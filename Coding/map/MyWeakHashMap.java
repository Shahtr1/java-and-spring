package map;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

import set.MyHashSet;

@SuppressWarnings("unchecked")
public class MyWeakHashMap<K, V> implements Iterable<MyWeakHashMap.EntryView<K, V>> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    protected Entry<K, V>[] table;
    protected int size;
    private int capacity;
    private final float loadFactor;
    protected int threshold;
    protected int modCount = 0;

    // Support for one null key (like Java's HashMap)
    private V nullKeyValue = null;
    private boolean hasNullKey = false;

    // Entry with a WeakReference to the key
    protected static class Entry<K, V> {
        final WeakReference<K> weakKey;
        V value;
        Entry<K, V> next;

        Entry(K key, V value) {
            this.weakKey = new WeakReference<>(key);
            this.value = value;
        }
    }

    public static class EntryView<K, V> implements Map.Entry<K, V> {
        private final K key;
        private final V value;

        public EntryView(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            throw new UnsupportedOperationException("Entry is immutable");
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof EntryView<?, ?> entryView))
                return false;
            return Objects.equals(key, entryView.key) && Objects.equals(value, entryView.value);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }
    }

    public MyWeakHashMap() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public MyWeakHashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    public MyWeakHashMap(int initialCapacity, float loadFactor) {
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
            K k = current.weakKey.get();
            if (k == null) {
                current = current.next;
                continue;
            }
            if (Objects.equals(k, key)) {
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
        if (key == null)
            return hasNullKey ? nullKeyValue : null;

        int hash = hash(key);
        int index = (table.length - 1) & hash;
        Entry<K, V> current = table[index];

        while (current != null) {
            K k = current.weakKey.get();
            if (k == null) {
                current = current.next;
                continue;
            }
            if (Objects.equals(k, key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    public boolean containsKey(K key) {
        return key == null ? hasNullKey : get(key) != null;
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
            K k = current.weakKey.get();
            if (k == null) {
                current = current.next;
                continue;
            }
            if (Objects.equals(k, key)) {
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
                K k = node.weakKey.get();
                if (k != null) {
                    int newIndex = (newCapacity - 1) & hash(k);
                    node.next = newTable[newIndex];
                    newTable[newIndex] = node;
                } else {
                    size--; // Cleanup GC'd entry
                }
                node = next;
            }
        }

        table = newTable;
        capacity = newCapacity;
        threshold = (int) (capacity * loadFactor);
        modCount++;
    }

    public MyHashSet<K> keySet() {
        MyHashSet<K> keys = new MyHashSet<>();
        if (hasNullKey)
            keys.add(null);

        for (int i = 0; i < capacity; i++) {
            Entry<K, V> current = table[i];
            while (current != null) {
                K k = current.weakKey.get();
                if (k != null)
                    keys.add(k);
                current = current.next;
            }
        }
        return keys;
    }

    public Collection<V> values() {
        Collection<V> values = new ArrayList<>();
        if (hasNullKey)
            values.add(nullKeyValue);

        for (int i = 0; i < capacity; i++) {
            Entry<K, V> current = table[i];
            while (current != null) {
                if (current.weakKey.get() != null) {
                    values.add(current.value);
                }
                current = current.next;
            }
        }
        return values;
    }

    public MyHashSet<EntryView<K, V>> entrySet() {
        MyHashSet<EntryView<K, V>> entries = new MyHashSet<>();
        if (hasNullKey)
            entries.add(new EntryView<>(null, nullKeyValue));

        for (int i = 0; i < capacity; i++) {
            Entry<K, V> current = table[i];
            while (current != null) {
                K k = current.weakKey.get();
                if (k != null)
                    entries.add(new EntryView<>(k, current.value));
                current = current.next;
            }
        }
        return entries;
    }

    class MyEntryIterator implements Iterator<EntryView<K, V>> {
        private final int expectedModCount = modCount;
        private int sizeCounter = size;
        private boolean nullKeySent = !hasNullKey;
        private int bucketIndex = 0;
        private Entry<K, V> current = null;

        @Override
        public boolean hasNext() {
            return sizeCounter > 0;
        }

        @Override
        public EntryView<K, V> next() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();

            if (!nullKeySent) {
                nullKeySent = true;
                sizeCounter--;
                return new EntryView<>(null, nullKeyValue);
            }

            while (current == null && bucketIndex < capacity) {
                current = table[bucketIndex++];
            }

            while (current != null) {
                K k = current.weakKey.get();
                V v = current.value;
                current = current.next;
                if (k != null) {
                    sizeCounter--;
                    return new EntryView<>(k, v);
                } else {
                    size--;
                    modCount++;
                }
            }

            throw new ConcurrentModificationException("Map changed during iteration");
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
                K k = node.weakKey.get();
                if (k != null) {
                    System.out.print("[" + k + "=" + node.value + "] -> ");
                }
                node = node.next;
            }
            System.out.println("null");
        }
    }
}
