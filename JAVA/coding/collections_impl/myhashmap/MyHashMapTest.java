package collections_impl.myhashmap;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

import org.junit.Assert;
import org.junit.Test;

import collections.myhashmap.MyHashMap.EntryView;
import collections.myhashset.MyHashSet;

public class MyHashMapTest {
    @Test
    public void testGet() {

    }

    @Test
    public void testKeySetIsEmpty() {
        MyHashMap<String, Integer> map = new MyHashMap<>();

        MyHashSet<String> keys = map.keySet();

        // for(String key:keys){

        // }

        Assert.assertTrue(keys.isEmpty());

    }

    @Test
    public void testKeySetHasData() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("Aa", 1);
        map.put("Bb", 2);
        map.put("New", 3);
        map.put("Old", 4);
        map.put("Cc", 5);
        map.put(null, 6);

        MyHashSet<String> keys = map.keySet();

        Assert.assertTrue(keys.contains("Aa"));
        Assert.assertTrue(keys.contains("Bb"));
        Assert.assertTrue(keys.contains("New"));
        Assert.assertTrue(keys.contains("Old"));
        Assert.assertTrue(keys.contains("Cc"));
        Assert.assertTrue(keys.contains(null));
        Assert.assertEquals(6, keys.size());

    }

    @Test
    public void testValuesIsEmpty() {
        MyHashMap<String, Integer> map = new MyHashMap<>();

        Collection<Integer> values = map.values();

        Assert.assertTrue(values.isEmpty());

    }

    @Test
    public void testValuesHasData() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("Aa", 1);
        map.put("Bb", 2);
        map.put("New", 3);
        map.put("Old", 4);
        map.put("Cc", 5);
        map.put(null, 6);

        Collection<Integer> values = map.values();

        Assert.assertTrue(values.contains(1));
        Assert.assertTrue(values.contains(2));
        Assert.assertTrue(values.contains(3));
        Assert.assertTrue(values.contains(4));
        Assert.assertTrue(values.contains(5));
        Assert.assertTrue(values.contains(6));
        Assert.assertEquals(6, values.size());

    }

    @Test
    public void testPut() {

    }

    @Test
    public void testRemove() {

    }

    @Test
    public void testMapIterator() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("Aa", 1);
        map.put("Bb", 2);
        map.put("New", 3);
        map.put("Old", 4);
        map.put("Cc", 5);
        map.put(null, 6);

        int count = 21;

        Iterator<EntryView<String, Integer>> iterator = map.iterator();

        while (iterator.hasNext()) {
            EntryView<String, Integer> view = iterator.next();
            count -= view.getValue();
        }

        Assert.assertEquals(0, count);
    }

    @Test(expected = ConcurrentModificationException.class)
    public void testFailFastIterator() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("A", 1);
        Iterator<EntryView<String, Integer>> it = map.iterator();
        map.put("B", 2); // triggers fail-fast
        it.next(); // 💥 exception
    }
}
