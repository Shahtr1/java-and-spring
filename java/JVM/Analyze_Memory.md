## How to Analyze Memory Usage in Java

### 1. Use JVM Monitoring Tools

- VisualVM (bundled with JDK)

  - GUI tool to inspect heap, threads, GC activity, memory usage
  - Can take heap dumps, inspect objects, and identify leaks

- JConsole
  - Lightweight monitor for CPU, memory, and threads
  - Great for live apps

### 2. Heap Dumps

- Snapshot of heap memory at a given time
- Use tools like:
  - `jmap -dump:live,format=b,file=heap.hprof <pid>`
  - Analyze with: **VisualVM**, **Eclipse MAT** (Memory Analyzer Tool)

### 3. GC Logs

```bash
-XX:+PrintGCDetails -Xloggc:gc.log
```

- Analyze with tools like GCViewer or GCEasy.io

### 4. In-Code Techniques

- Use `Runtime.getRuntime()` to inspect memory:

```java
Runtime runtime = Runtime.getRuntime();
System.out.println("Free: " + runtime.freeMemory());
```
