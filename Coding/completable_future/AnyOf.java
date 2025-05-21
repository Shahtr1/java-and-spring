package completable_future;

import java.util.concurrent.CompletableFuture;

// Use when you're okay with the first completed task.

public class AnyOf {
    public static void main(String[] args) {
        CompletableFuture<String> service1 = CompletableFuture.supplyAsync(() -> {
            sleep(1500);
            return "Service 1";
        });

        CompletableFuture<String> service2 = CompletableFuture.supplyAsync(() -> {
            sleep(500);
            return "Service 2";
        });

        CompletableFuture<String> service3 = CompletableFuture.supplyAsync(() -> {
            sleep(1000);
            return "Service 3";
        });

        CompletableFuture.anyOf(service1, service2, service3)
                .thenAccept(result -> System.out.println("First completed: " + result));

        sleepMainThread(2000);
    }

    static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    static void sleepMainThread(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
