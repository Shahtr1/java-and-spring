package varhandle;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

@SuppressWarnings("unchecked")
public class VarHandleCASDemo {

    private static final int TABLE_SIZE = 16;
    private static final Node<String, String>[] table = new Node[TABLE_SIZE];

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
        final V value;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "[" + key + "=" + value + "]";
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int index = 3;
        int numThreads = 10;

        Runnable task = () -> {
            String threadName = Thread.currentThread().getName();
            Node<String, String> newNode = new Node<>(threadName, "value");

            boolean success = TABLE_HANDLE.compareAndSet(table, index, null, newNode);
            if (success) {
                System.out.println(threadName + " inserted " + newNode);
            } else {
                System.out.println(threadName + " lost the race. Current value: " + table[index]);
            }
        };

        Thread[] threads = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(task, "Thread-" + i);
            threads[i].start();
        }

        for (Thread t : threads) {
            t.join();
        }

        System.out.println("\nFinal value at index " + index + ": " + table[index]);
    }
}
