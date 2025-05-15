package collections_impl.myhashmap;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.Objects;

@SuppressWarnings("unchecked")
public class MyConcurrentHashMap<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private volatile Node<K, V>[] table;

    private volatile int size;
    private final float loadFactor;
    private int threshold;

    private static final VarHandle TABLE_HANDLE;

    static {
        try {
            TABLE_HANDLE = MethodHandles.arrayElementVarHandle(Node[].class);
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    static class Node<K, V> {
        final K key;
        volatile V value;
        final int hash;
        volatile Node<K, V> next;

        Node(K key, V value, int hash, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.hash = hash;
            this.next = next;
        }
    }

    public MyConcurrentHashMap() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public MyConcurrentHashMap(int initialCapacity, float loadFactor) {
        this.loadFactor = loadFactor;
        this.table = new Node[initialCapacity];
        this.threshold = (int) (initialCapacity * loadFactor);
        this.size = 0;
    }

    private int hash(Object key) {
        int h = Objects.hashCode(key);
        return h ^ (h >>> 16); // Hash spreading like Java 8+
    }

    public void put(K key, V value) {
        int hash = hash(key);
        int index = (table.length - 1) & hash;
        Node<K, V> newNode = new Node<>(key, value, hash, null);

        // Try lock-free CAS if bucket is empty
        if (TABLE_HANDLE.compareAndSet(table, index, null, newNode)) {
            size++;
            if (size >= threshold) {
                resize(); // still uses full lock
            }
            return;
        }

        // Fallback: bucket not empty or CAS failed
        synchronized (getLockObject(index)) {
            Node<K, V> head = table[index];
            Node<K, V> current = head;

            while (current != null) {
                if (Objects.equals(current.key, key)) {
                    current.value = value;
                    return;
                }
                current = current.next;
            }

            newNode.next = head;
            table[index] = newNode;
            size++;

            if (size >= threshold) {
                resize();
            }
        }
    }

    public V get(K key) {
        int hash = hash(key);
        int index = (table.length - 1) & hash;

        Node<K, V> current = table[index];
        while (current != null) {
            if (Objects.equals(current.key, key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    // Because table[index] might be null, you can't synchronize on it directly — so
    // we make sure there's always something to lock on:

    private Object getLockObject(int index) {
        Node<K, V> node = table[index];
        if (node != null)
            return node;

        // If null, create dummy node to synchronize on
        synchronized (this) {
            if (table[index] == null) {
                table[index] = new Node<>(null, null, 0, null);
            }
        }
        return table[index];
    }

    private synchronized void resize() {
        Node<K, V>[] oldTable = table;
        int oldCapacity = oldTable.length;
        int newCapacity = oldCapacity * 2;

        Node<K, V>[] newTable = new Node[newCapacity];

        for (int i = 0; i < oldCapacity; i++) {
            Node<K, V> current = oldTable[i];

            while (current != null) {
                Node<K, V> next = current.next;
                int newIndex = (newCapacity - 1) & current.hash;

                // Insert at head of new bucket
                current.next = newTable[newIndex];
                newTable[newIndex] = current;

                current = next;
            }
        }

        table = newTable;
        threshold = (int) (newCapacity * loadFactor);
    }

    public boolean remove(K key) {
        int hash = hash(key);
        int index = (table.length - 1) & hash;

        synchronized (getLockObject(index)) {
            Node<K, V> current = table[index];
            Node<K, V> prev = null;

            while (current != null) {
                if (Objects.equals(current.key, key)) {
                    if (prev == null) {
                        table[index] = current.next; // Remove head
                    } else {
                        prev.next = current.next; // Remove middle or end
                    }
                    size--;
                    return true;
                }
                prev = current;
                current = current.next;
            }
            return false; // Not found
        }
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }

}
