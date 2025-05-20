package threads.execution_models.readers_writers_problem;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main {

    // Shared resource
    private static String sharedResource = "Initial Value";

    // Create a ReadWriteLock
    private static ReadWriteLock lock = new ReentrantReadWriteLock();
    private static Lock readLock = lock.readLock();
    private static Lock writeLock = lock.writeLock();

    public static void main(String[] args) {
        // Create and start reader threads
        for (int i = 0; i < 3; i++) {
            new Thread(new Reader(), "Reader-" + i).start();
        }

        // Create and start writer threads
        for (int i = 0; i < 2; i++) {
            new Thread(new Writer(), "Writer-" + i).start();
        }
    }

    // Reader class
    static class Reader implements Runnable {
        @Override
        public void run() {
            try {
                // Acquire the read lock
                readLock.lock();
                System.out.println(Thread.currentThread().getName() + " is reading: " + sharedResource);
                Thread.sleep(1000); // Simulate some reading time
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // Release the read lock
                readLock.unlock();
            }
        }
    }

    // Writer class
    static class Writer implements Runnable {
        @Override
        public void run() {
            try {
                // Acquire the write lock
                writeLock.lock();
                System.out.println(Thread.currentThread().getName() + " is writing to the shared resource.");
                sharedResource = "Updated by " + Thread.currentThread().getName();
                Thread.sleep(1000); // Simulate some writing time
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // Release the write lock
                writeLock.unlock();
            }
        }
    }
}
