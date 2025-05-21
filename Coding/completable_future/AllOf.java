package completable_future;

import java.util.concurrent.CompletableFuture;

// Use when you need to run multiple independent tasks in parallel, and wait for all of them to finish.

public class AllOf {
    public static void main(String[] args) {
        CompletableFuture<String> productInfo = CompletableFuture.supplyAsync(() -> {
            sleep(1000);
            return "Product Info";
        });

        CompletableFuture<String> reviews = CompletableFuture.supplyAsync(() -> {
            sleep(1500);
            return "Reviews";
        });

        CompletableFuture<String> stock = CompletableFuture.supplyAsync(() -> {
            sleep(500);
            return "Stock Info";
        });

        CompletableFuture<Void> all = CompletableFuture.allOf(productInfo, reviews, stock);

        all.thenRun(() -> {
            try {
                // All futures are done at this point
                System.out.println(productInfo.get());
                System.out.println(reviews.get());
                System.out.println(stock.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        sleepMainThread(3000);
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
