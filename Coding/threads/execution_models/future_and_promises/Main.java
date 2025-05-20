package threads.execution_models.future_and_promises;

import java.util.Arrays;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // Define the number of threads and the barrier
        int numberOfThreads = 3;
        CyclicBarrier barrier = new CyclicBarrier(numberOfThreads, new Runnable() {
            @Override
            public void run() {
                System.out.println("All threads have reached the barrier. Proceeding to the next phase...");
            }
        });

        // ExecutorService to manage worker threads
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);

        // Create a list to hold Future objects
        Future<Integer>[] futures = new Future[numberOfThreads];

        // Create and submit tasks for each worker
        for (int i = 0; i < numberOfThreads; i++) {
            final int workerId = i;
            futures[i] = executor.submit(() -> {
                // Simulating some task by worker
                System.out.println("Worker " + workerId + " is working...");
                Thread.sleep((long) (Math.random() * 2000)); // Simulate work
                System.out.println("Worker " + workerId + " reached the barrier.");

                // Wait at the barrier
                barrier.await();

                // After barrier, continue with next task
                System.out.println("Worker " + workerId + " is doing work after the barrier.");
                return workerId * 10; // Simulating result (e.g., calculation)
            });
        }

        // Convert Future[] to CompletableFuture[]
        CompletableFuture<Void> allOf = CompletableFuture.allOf(
                Arrays.stream(futures)
                        .map(future -> CompletableFuture.supplyAsync(() -> {
                            try {
                                return future.get(); // Block and retrieve result from each Future
                            } catch (InterruptedException | ExecutionException e) {
                                e.printStackTrace();
                                return null;
                            }
                        }))
                        .toArray(CompletableFuture[]::new));

        // Now let's use CompletableFuture to chain a task after all Future tasks are
        // completed
        allOf.thenRun(() -> {
            System.out.println("All worker tasks have finished. Final processing...");
        });

        // Wait for the completion of the chained tasks
        allOf.get();

        // Retrieve and print the results from the Future objects
        System.out.println("Results from workers:");
        for (int i = 0; i < numberOfThreads; i++) {
            System.out.println("Result from Worker " + i + ": " + futures[i].get());
        }

        // Shut down the executor service
        executor.shutdown();
    }
}
