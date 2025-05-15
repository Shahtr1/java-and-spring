## 1. Configuration Constants

```java
private static final int DEFAULT_CAPACITY = 16;
private static final float DEFAULT_LOAD_FACTOR = 0.75f;
```

| 🔍 Decision                                         | 🤔 WHY                                                                                                                                            |
| --------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------- |
| Use of `DEFAULT_CAPACITY` and `DEFAULT_LOAD_FACTOR` | Mirrors Java's `HashMap`. `16` is a power of two (enables better distribution with bitwise ops in future). `0.75f` balances space vs performance. |
| `static final`                                      | Ensures these are shared, unchangeable constants — improves clarity and prevents accidental changes.                                              |
| Constants over magic numbers                        | Makes the code more readable and tunable. For instance, changing the default from 16 to 32 is a one-line fix.                                     |

## 2. Core State Variables

```java
private Entry<K, V>[] table;
private int size;
private int capacity;
private final float loadFactor;
private int threshold;
```

| 🔍 Field     | 🤔 WHY It Exists                                                                       |
| ------------ | -------------------------------------------------------------------------------------- |
| `table`      | The core hash table — array of chains (`Entry[]`). Holds buckets of linked list nodes. |
| `size`       | Tracks number of key-value pairs, including null key if used.                          |
| `capacity`   | Current size of the table array — changes on resize.                                   |
| `loadFactor` | Ratio at which resizing is triggered: size >= threshold → grow table.                  |
| `threshold`  | Precomputed as `capacity * loadFactor`. Avoids recalculating every time we `put()`.    |

## 3. Null Key Handling

```java
private V nullKeyValue = null;
private boolean hasNullKey = false;
```

| 🔍 Why This Is Crucial                                                                                       |
| ------------------------------------------------------------------------------------------------------------ |
| Java's `HashMap` allows **one null key** (stored separately).                                                |
| `hashCode(null)` will throw a `NullPointerException`, so we bypass it entirely for null.                     |
| Storing nulls separately keeps hashing logic clean and efficient — no edge-case branching in core functions. |

## 4. Entry Class

```java
private static class Entry<K, V> {
    final K key;
    V value;
    Entry<K, V> next;
}
```

| 🔍 Design             | 🤔 Why                                                                                                                   |
| --------------------- | ------------------------------------------------------------------------------------------------------------------------ |
| `static` nested class | Prevents memory leak via implicit reference to outer class (`MyHashMap`).                                                |
| `final K key`         | Once a key is hashed and inserted, it shouldn't change. This ensures hash stability.                                     |
| `Entry<K, V> next`    | Implements **separate chaining** — resolves collisions via linked lists. Simpler and more reliable than open addressing. |

## 5. Constructors

```java
public MyHashMap()
public MyHashMap(int initialCapacity)
public MyHashMap(int initialCapacity, float loadFactor)
```

| 🔍 Why Overloaded Constructors?                                                            |
| ------------------------------------------------------------------------------------------ |
| Gives callers flexibility — start with defaults or tune capacity + load factor.            |
| Makes it **test-friendly** and **production-friendly**.                                    |
| Defensive programming: constructor validates inputs (no zero or negative capacity/factor). |
| `threshold = capacity * loadFactor` avoids repeated computation.                           |

## 6. Hash Function

```java
private int hash(K key) {
    return Math.abs(Objects.hashCode(key)) % capacity;
}
```

| 🔍 Component         | 🤔 Why It Matters                                                                                               |
| -------------------- | --------------------------------------------------------------------------------------------------------------- |
| `Objects.hashCode()` | Null-safe wrapper around `key.hashCode()` — returns 0 if null.                                                  |
| `Math.abs(...)`      | `hashCode()` may return negative values, which would crash an array lookup.                                     |
| `% capacity`         | Maps hashcode to array index. Simple but effective. (Future enhancement: bitmasking if capacity is power of 2.) |

## 7. PUT Operation

```java
public void put(K key, V value) { ... }
```

### Logic Path:

1.  If `key == null`: handle special null-key logic (store separately).
2.  Else: calculate hash index → traverse the bucket.
3.  If key exists → update value.
4.  If key not found → insert at head of linked list (`O(1)` insert).
5.  Increment size.
6.  If `size >= threshold`, trigger resize.

| 🔍 Why This Structure?                                                                   |
| ---------------------------------------------------------------------------------------- |
| ✅ Supports idempotent inserts (update vs insert).                                       |
| ✅ Head insertion is faster than tail (`O(1)` vs `O(n)`), doesn't require tail tracking. |
| ✅ Resize check prevents performance drop-off due to overloaded buckets.                 |

## 8. GET Operation

```java
public V get(K key) { ... }
```

| 🔍 Behavior                                                                          |
| ------------------------------------------------------------------------------------ |
| If `key == null`: return `nullKeyValue`.                                             |
| Else: hash → index → walk chain → check `Objects.equals()` for match.                |
| Returns null if not found.                                                           |
| ✅ Uses safe equality and null-aware logic.                                          |
| ✅ Fast average-case lookup: O(1) with uniform hash; O(n) worst-case in same bucket. |

## 9. REMOVE Operation

```java
public boolean remove(K key) { ... }
```

| 🔍 Why This Works                                                |
| ---------------------------------------------------------------- |
| For null keys → reset internal flag and value.                   |
| For normal keys → hash to index, walk chain with `prev` pointer. |
| Handles both head and middle deletion safely.                    |
| Size is decremented only on successful deletion.                 |
| Guarantees consistent behavior like Java’s `HashMap.remove()`.   |

## 10. CLEAR Operation

```java
public void clear() {
    table = new Entry[capacity];
    size = 0;
    hasNullKey = false;
    nullKeyValue = null;
}
```

| 🔍 Why This Works                                                  |
| ------------------------------------------------------------------ |
| Reinitializes the backing array to free memory.                    |
| Clears all state, including null key handling.                     |
| Leaves capacity and load factor untouched (unlike reinitializing). |

## 11. RESIZE Operation

```java
private void resize() {
    int newCapacity = capacity * 2;
    Entry<K, V>[] newTable = new Entry[newCapacity];
    ...
}
```

| 🔍 Why This Matters                                              |
| ---------------------------------------------------------------- |
| Doubles capacity to maintain load factor.                        |
| All entries are rehashed and redistributed.                      |
| Buckets are preserved in a **new array** — not mutated in-place. |
| Improves space/time tradeoff for scalability.                    |
| Keeps average case insert/search/delete at O(1).                 |

## 12. DEBUGGING: printBuckets()

```java
public void printBuckets() { ... }
```

| 🔍 Why It’s Helpful                                                                        |
| ------------------------------------------------------------------------------------------ |
| Shows linked list length per bucket — reveals if your hash function is causing clustering. |
| Logs null key separately.                                                                  |
| Good for testing resizing, clustering, and collision handling.                             |

## TODO:

1.  `keySet()` with iterator? ✅
2.  `entrySet()` using `Map.Entry<K, V>` style? ✅
3.  Thread-safe version (`ConcurrentMyHashMap`)?
4.  JUnit tests for full validation? ✅
