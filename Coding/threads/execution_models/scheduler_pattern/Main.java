package threads.execution_models.scheduler_pattern;

import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        // Create a scheduled executor service with a pool of 2 threads
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

        // Task 1: A task that runs once after a delay
        Runnable task1 = () -> System.out.println("Task 1 executed at: " + System.currentTimeMillis());

        // Schedule task1 to run after 3 seconds
        scheduler.schedule(task1, 3, TimeUnit.SECONDS);

        // Task 2: A recurring task that runs at a fixed rate
        Runnable task2 = () -> System.out.println("Task 2 executed at: " + System.currentTimeMillis());

        // Schedule task2 to run every 5 seconds, starting after an initial delay of 2
        // seconds
        scheduler.scheduleAtFixedRate(task2, 2, 5, TimeUnit.SECONDS);

        // Task 3: A recurring task that runs after a fixed delay
        Runnable task3 = () -> System.out.println("Task 3 executed at: " + System.currentTimeMillis());

        // Schedule task3 to run with a fixed delay of 3 seconds between the completion
        // of the last execution and the next
        scheduler.scheduleWithFixedDelay(task3, 1, 3, TimeUnit.SECONDS);

        // Let the tasks run for a while before shutting down the scheduler
        Thread.sleep(20000); // Let it run for 20 seconds

        // Shutdown the scheduler
        scheduler.shutdown();
    }
}
