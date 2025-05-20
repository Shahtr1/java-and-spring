package threads.print_1_to_100;

public class NumberPrinter {

    private int number = 1;
    private final int MAX = 100;

    // Method to print even numbers
    public synchronized void printEven() {
        while (number <= MAX) {
            if (number % 2 == 0) {
                System.out.println("Even Thread: " + number);
                number++;
                notify(); // Notify the other thread
            } else {
                try {
                    wait(); // Wait until notified
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    // Method to print odd numbers
    public synchronized void printOdd() {
        while (number <= MAX) {
            if (number % 2 != 0) {
                System.out.println("Odd Thread: " + number);
                number++;
                notify(); // Notify the other thread
            } else {
                try {
                    wait(); // Wait until notified
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
