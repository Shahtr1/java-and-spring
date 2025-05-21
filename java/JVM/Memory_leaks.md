## What Is a Memory Leak in Java?

A memory leak in Java occurs when objects are no longer needed by the application but cannot be garbage collected because they are still referenced (strongly) from somewhere in memory.

## Common Causes of Memory Leaks

## 1. Long-lived Caches or Static Maps

```java
static Map<String, Object> cache = new HashMap<>();
```

## 2. ClassLoader Leaks (Your Point)

- Especially in web apps (Tomcat, Jetty), loading/reloading classes without cleaning up leads to Metaspace growth.
- Fix: Avoid custom class loaders, and always deregister classes and threads during shutdown.

## 3. ThreadLocal Leaks

- Failing to remove values from ThreadLocal in thread pools can leak memory.

```java
ThreadLocal<MyObject> tl = new ThreadLocal<>();
tl.set(data); // forgot to call tl.remove();
```

## 4. Unclosed Resources

```java
FileInputStream in = new FileInputStream("file.txt");
// forgot in.close();
```

| Strategy                        | Description                                                                |
| ------------------------------- | -------------------------------------------------------------------------- |
| Use **try-with-resources**      | Auto-close I/O/DB connections                                              |
| Use **Weak/SoftReferences**     | For caches (so GC can clean unused items)                                  |
| Always **unregister listeners** | Avoid holding references to disposed objects                               |
| Call `ThreadLocal.remove()`     | Especially in thread pools                                                 |
| Monitor with **tools**          | Use tools like **VisualVM**, **YourKit**, **JProfiler**, or **Heap dumps** |
| Use **Reference Queues**        | To clean up phantom references safely                                      |
