package threads.execution_models.pipelining_pattern;

import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Input data
        int[] numbers = { 1, 2, 3, 4, 5 };

        // Executor for concurrent processing
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // CompletableFuture pipeline processing
        for (int number : numbers) {
            // First stage: Double the number
            CompletableFuture<Integer> stage1 = CompletableFuture.supplyAsync(() -> {
                System.out.println("Stage 1: Doubling " + number);
                return number * 2;
            }, executor);

            // Second stage: Add 10 to the doubled number
            CompletableFuture<Integer> stage2 = stage1.thenApplyAsync(result -> {
                System.out.println("Stage 2: Adding 10 to " + result);
                return result + 10;
            }, executor);

            // Third stage: Multiply the result by 3
            CompletableFuture<Integer> stage3 = stage2.thenApplyAsync(result -> {
                System.out.println("Stage 3: Multiplying " + result + " by 3");
                return result * 3;
            }, executor);

            // Final result: Get the final processed value
            stage3.thenAccept(finalResult -> {
                System.out.println("Final Result: " + finalResult);
            });
        }

        // Shutdown the executor after tasks are complete
        executor.shutdown();
    }
}
