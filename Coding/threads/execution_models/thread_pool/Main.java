package threads.execution_models.thread_pool;

import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        // Create a thread pool with 5 threads
        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        // Submit tasks to the thread pool
        for (int i = 0; i < 10; i++) {
            threadPool.submit(new Task(i));
        }

        // Shut down the thread pool after tasks are completed
        threadPool.shutdown();
    }

    // Task class implements Runnable interface
    static class Task implements Runnable {
        private final int taskId;

        public Task(int taskId) {
            this.taskId = taskId;
        }

        @Override
        public void run() {
            try {
                // Simulating some work
                System.out.println("Task " + taskId + " is being processed by " + Thread.currentThread().getName());
                Thread.sleep(1000); // Simulate time-consuming task
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
