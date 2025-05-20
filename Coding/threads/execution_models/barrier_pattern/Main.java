package threads.execution_models.barrier_pattern;

import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        // Number of threads in the barrier
        int numberOfThreads = 3;

        // Create a CyclicBarrier with a barrier action to run when all threads reach
        // the barrier
        CyclicBarrier barrier = new CyclicBarrier(numberOfThreads, new Runnable() {
            @Override
            public void run() {
                System.out.println("All threads have reached the barrier. Proceeding to the next phase...");
            }
        });

        // Create and start threads
        for (int i = 0; i < numberOfThreads; i++) {
            new Thread(new Worker(i, barrier)).start();
        }
    }

    static class Worker implements Runnable {
        private final int id;
        private final CyclicBarrier barrier;

        public Worker(int id, CyclicBarrier barrier) {
            this.id = id;
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                // Simulate work by sleeping for a random amount of time
                System.out.println("Worker " + id + " is working...");
                Thread.sleep((int) (Math.random() * 1000));

                // Each worker reaches the barrier
                System.out.println("Worker " + id + " reached the barrier.");

                // Wait at the barrier until all threads have reached it
                barrier.await();

                // Simulate additional work after the barrier
                System.out.println("Worker " + id + " is working after the barrier.");

            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
