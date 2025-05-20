package map;

import java.util.*;

public class MyLinkedHashMap<K, V> extends MyHashMap<K, V> {

    private LinkedEntry<K, V> head;
    private LinkedEntry<K, V> tail;

    private static class LinkedEntry<K, V> extends Entry<K, V> {

        LinkedEntry<K, V> before, after;

        LinkedEntry(K key, V value) {
            super(key, value);
        }
    }

    public MyLinkedHashMap() {
        super();
    }

    public MyLinkedHashMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    @Override
    public void put(K key, V value) {
        if (key == null) {
            super.put(null, value);
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

        LinkedEntry<K, V> newNode = new LinkedEntry<>(key, value);
        newNode.next = table[index];
        table[index] = newNode;
        linkEntryLast(newNode);
        size++;
        modCount++;

        if (size >= threshold) {
            resize();
        }
    }

    private void linkEntryLast(LinkedEntry<K, V> e) {
        if (tail == null) {
            head = tail = e;
        } else {
            tail.after = e;
            e.before = tail;
            tail = e;
        }
    }

    @Override
    public boolean remove(K key) {
        boolean removed = super.remove(key);
        if (removed && key != null) {
            LinkedEntry<K, V> current = head;
            while (current != null) {
                if (Objects.equals(current.key, key)) {
                    unlink(current);
                    break;
                }
                current = current.after;
            }
        }
        return removed;
    }

    private void unlink(LinkedEntry<K, V> e) {
        if (e.before != null) {
            e.before.after = e.after;
        } else {
            head = e.after;
        }
        if (e.after != null) {
            e.after.before = e.before;
        } else {
            tail = e.before;
        }
    }

    @Override
    public Iterator<EntryView<K, V>> iterator() {
        return new Iterator<>() {
            LinkedEntry<K, V> current = head;
            final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public EntryView<K, V> next() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                if (current == null)
                    throw new NoSuchElementException();
                EntryView<K, V> view = new EntryView<>(current.key, current.value);
                current = current.after;
                return view;
            }
        };
    }

    public void printLinkedOrder() {
        LinkedEntry<K, V> node = head;
        while (node != null) {
            System.out.print("[" + node.key + "=" + node.value + "] -> ");
            node = node.after;
        }
        System.out.println("null");
    }
}
