package completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class Independent {

    public static void main(String[] args) {

        // Simulate two async tasks: fetching user and account details
        CompletableFuture<String> userFuture = CompletableFuture.supplyAsync(() -> {
            simulateDelay("Fetching user...");
            return "User: Alice";
        });

        CompletableFuture<String> accountFuture = CompletableFuture.supplyAsync(() -> {
            simulateDelay("Fetching account...");
            return "Account: Premium";
        });

        // Combine the results of both async calls
        CompletableFuture<String> combined = userFuture.thenCombine(accountFuture, (user, account) -> {
            return user + ", " + account;
        });

        // Handle result or exception
        combined
                .thenApply(data -> data.toUpperCase())
                .thenAccept(System.out::println)
                .exceptionally(ex -> {
                    System.err.println("Error: " + ex.getMessage());
                    return null;
                });

        // Prevent main from exiting immediately
        sleepMainThread(3000);
    }

    private static void simulateDelay(String message) {
        System.out.println(message);
        try {
            TimeUnit.SECONDS.sleep(1); // Simulate I/O delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static void sleepMainThread(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
