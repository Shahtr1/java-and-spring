package threads.countdown_latch;

import java.util.concurrent.CountDownLatch;

public class Main {

    // Two CountDownLatches to control the order of execution
    private final CountDownLatch firstLatch = new CountDownLatch(1);
    private final CountDownLatch secondLatch = new CountDownLatch(1);

    // First method: Releases the first latch after execution
    public void first() throws InterruptedException {
        System.out.println("first");
        firstLatch.countDown(); // Allow second() to proceed
    }

    // Second method: Waits for first() to complete, then releases the second latch
    public void second() throws InterruptedException {
        firstLatch.await(); // Wait until first() is done
        System.out.println("second");
        secondLatch.countDown(); // Allow third() to proceed
    }

    // Third method: Waits for second() to complete
    public void third() throws InterruptedException {
        secondLatch.await(); // Wait until second() is done
        System.out.println("third");
    }

    public static void main(String[] args) {
        Main main = new Main();

        // Create threads for each method
        Thread threadA = new Thread(() -> {
            try {
                main.first();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread threadB = new Thread(() -> {
            try {
                main.second();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread threadC = new Thread(() -> {
            try {
                main.third();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Start the threads
        threadC.start(); // Start third() thread
        threadB.start(); // Start second() thread
        threadA.start(); // Start first() thread
    }
}
