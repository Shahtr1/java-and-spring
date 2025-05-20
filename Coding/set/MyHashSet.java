package set;

import java.util.Iterator;

import map.MyHashMap;

public class MyHashSet<T> implements Iterable<T> {
    private static final Object PRESENT = new Object();
    private final MyHashMap<T, Object> map;

    public MyHashSet() {
        map = new MyHashMap<>();
    }

    public boolean add(T value) {
        if (map.containsKey(value))
            return false;
        map.put(value, PRESENT);
        return true;
    }

    public boolean contains(T value) {
        return map.containsKey(value);
    }

    public boolean remove(T value) {
        return map.remove(value);
    }

    public int size() {
        return map.size();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public void clear() {
        map.clear();
    }

    public void printBuckets() {
        map.printBuckets();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Iterator<MyHashMap.EntryView<T, Object>> entryIterator = map.iterator();

            @Override
            public boolean hasNext() {
                return entryIterator.hasNext();
            }

            @Override
            public T next() {
                return entryIterator.next().getKey();
            }

        };

    }

}
