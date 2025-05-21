package threads.countdown_latch;

import java.util.concurrent.CountDownLatch;

public class Main {

    public static void main(String[] args) {
        int numberOfWorkers = 3;
        CountDownLatch latch = new CountDownLatch(numberOfWorkers);

        // Start worker threads
        for (int i = 1; i <= numberOfWorkers; i++) {
            new Thread(new Worker(i, latch)).start();
        }

        System.out.println("Main thread is waiting for workers to finish...");

        try {
            latch.await(); // Main thread waits here until latch count is zero
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Main thread interrupted");
        }

        System.out.println("All workers finished. Main thread continues...");
    }
}

class Worker implements Runnable {
    private final int workerId;
    private final CountDownLatch latch;

    public Worker(int workerId, CountDownLatch latch) {
        this.workerId = workerId;
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println("Worker " + workerId + " started work.");

        try {
            Thread.sleep((long) (Math.random() * 2000)); // Simulate work
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Worker " + workerId + " finished work.");
        latch.countDown(); // Signal that this worker is done
    }
}
