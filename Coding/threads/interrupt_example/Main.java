package threads.interrupt_example;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();

        // Thread 1: Will wait for a notification or an interrupt
        Thread thread1 = new Thread(() -> {
            synchronized (lock) {
                try {
                    System.out.println("Thread 1 is waiting...");
                    lock.wait(); // Will wait until notified or interrupted
                    System.out.println("Thread 1 is notified!");
                } catch (InterruptedException e) {
                    System.out.println("Thread 1 was interrupted during wait.");
                    // Handle the interruption (e.g., exit or cleanup)
                }
            }
        });

        thread1.start();

        // Main thread sleeps for 2 seconds to ensure thread1 has time to acquire the
        // lock and enter wait()
        Thread.sleep(2000);

        // Thread 2: Interrupt thread 1 during its wait
        System.out.println("Main thread interrupts thread 1.");
        thread1.interrupt(); // Interrupting Thread 1 while it is waiting

        // delay notify to let interrupt win
        Thread.sleep(100);

        // Thread 2: Notify (this will have no effect, since thread 1 is interrupted)
        synchronized (lock) {
            lock.notify();
        }
    }
}
