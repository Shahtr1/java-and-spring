## Types of References in Java (in `java.lang.ref` package)

### 1. Strong Reference (Default)

- Regular object reference (e.g., `Object obj = new Object();`)
- As long as a strong reference exists, the object will not be garbage collected.

```java
String name = "Amazon";  // strong reference
```

### 2. Soft Reference

- Eligible for GC only when JVM is low on memory.
- Useful for caches where you want to keep data around, but not at the cost of an `OutOfMemoryError`.

```java
SoftReference<MyObject> softRef = new SoftReference<>(new MyObject());
```

### 3. Weak Reference

- Eligible for GC as soon as there are no strong references.
- Useful for maps like WeakHashMap, where keys should not prevent GC.

```java
WeakReference<MyObject> weakRef = new WeakReference<>(new MyObject());
```

### 4. Phantom Reference

- Used to track object finalization and clean up resources post-GC.
- `get()` always returns `null`.
- Requires a `ReferenceQueue` to detect when the object is collected.
- A queue that the JVM will use to notify you when an object is truly about to be collected.
- This is the only way to interact with `PhantomReference` — you **cannot get the object directly**.
- You tell the JVM: “_Let me know when obj is about to die by putting this reference into queue._”

```java
public class PhantomReferenceExample {
    public static void main(String[] args) throws InterruptedException {
        ReferenceQueue<Object> queue = new ReferenceQueue<>();
        Object obj = new Object();

        PhantomReference<Object> phantom = new PhantomReference<>(obj, queue);

        obj = null; // Remove strong reference

        System.gc();
        Thread.sleep(100); // Give GC some time

        if (queue.poll() == phantom) {
            System.out.println("PhantomReference enqueued, object is finalized and GC-ready.");
        }

        /*

        This checks whether the JVM has enqueued the phantom reference into the queue.

        If yes, that means:
        -   The object was finalized.
        -   It is ready to be removed from memory.
        -   Now is your chance to do cleanup, like freeing native memory.

        */
    }
}
```

```text
[Object] <-- phantom reference --> [ReferenceQueue]
   |
   ↓
 set to null
   ↓
 GC sees it has no strong refs
   ↓
 GC finalizes it
   ↓
 puts PhantomReference into queue
   ↓
 You poll queue, see it's there → do cleanup
```

### `PhantomReference` vs `finalize()`

| Feature                   | `finalize()`               | `PhantomReference`                             |
| ------------------------- | -------------------------- | ---------------------------------------------- |
| When it runs              | Before object is GC'd      | After finalization, before actual GC           |
| Who controls the logic    | JVM calls `finalize()`     | You poll a `ReferenceQueue`                    |
| Can access the object?    | ✅ Yes (within `finalize`) | ❌ No (`get()` always returns `null`)          |
| Can resurrect the object? | ✅ Yes (bad practice)      | ❌ No (safe, no resurrection possible)         |
| Reliability               | ❌ Unreliable, discouraged | ✅ Predictable when used with `ReferenceQueue` |
| Use case                  | Legacy cleanup logic       | Resource deallocation (native memory etc.)     |
