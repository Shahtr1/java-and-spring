package threads.execution_models.dining_philosophores;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    // Number of philosophers
    private static final int NUM_PHILOSOPHERS = 5;

    // Locks for each fork (one for each philosopher's left and right fork)
    private static final Lock[] forks = new Lock[NUM_PHILOSOPHERS];

    public static void main(String[] args) {
        // Initialize the forks (locks)
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            forks[i] = new ReentrantLock();
        }

        // Create philosopher threads
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            new Thread(new Philosopher(i)).start();
        }
    }

    // Philosopher class
    static class Philosopher implements Runnable {
        private final int id;

        public Philosopher(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    think(); // Philosopher is thinking
                    dine(); // Philosopher is eating
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Philosopher thinking (just simulating with sleep)
        private void think() throws InterruptedException {
            System.out.println("Philosopher " + id + " is thinking.");
            Thread.sleep((int) (Math.random() * 1000));
        }

        // Philosopher eating (acquires both forks)
        private void dine() throws InterruptedException {
            // Pick up the left and right fork
            Lock leftFork = forks[id];
            Lock rightFork = forks[(id + 1) % NUM_PHILOSOPHERS];

            // Ensure the philosopher picks up the forks in a consistent order to prevent
            // deadlock
            Lock first = leftFork;
            Lock second = rightFork;

            if (id % 2 == 0) { // Even philosopher picks up left fork first, then right
                first = leftFork;
                second = rightFork;
            } else { // Odd philosopher picks up right fork first, then left
                first = rightFork;
                second = leftFork;
            }

            // Try to acquire both forks
            first.lock();
            System.out.println("Philosopher " + id + " picked up " + (first == leftFork ? "left" : "right") + " fork.");
            second.lock();
            System.out
                    .println("Philosopher " + id + " picked up " + (second == leftFork ? "left" : "right") + " fork.");

            // Eating
            System.out.println("Philosopher " + id + " is eating.");
            Thread.sleep((int) (Math.random() * 1000)); // Simulate eating time

            // Put down the forks
            first.unlock();
            System.out.println("Philosopher " + id + " put down " + (first == leftFork ? "left" : "right") + " fork.");
            second.unlock();
            System.out.println("Philosopher " + id + " put down " + (second == leftFork ? "left" : "right") + " fork.");
        }
    }
}
