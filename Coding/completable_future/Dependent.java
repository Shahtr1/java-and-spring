package completable_future;

import java.util.concurrent.CompletableFuture;

public class Dependent {
    public static void main(String[] args) {
        getUserId()
                .thenCompose(Dependent::getUserOrders) // flat-maps the result
                .thenAccept(System.out::println)
                .exceptionally(ex -> {
                    System.err.println("Error: " + ex.getMessage());
                    return null;
                });

        sleepMainThread(3000);
    }

    static CompletableFuture<Integer> getUserId() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("Getting user ID...");
            sleep(1000);
            return 42;
        });
    }

    static CompletableFuture<String> getUserOrders(int userId) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("Fetching orders for user ID: " + userId);
            sleep(1000);
            return "Orders: [Pizza, Coffee]";
        });
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
