package threads.print_1_to_100;

public class EvenOddThreads {
    public static void main(String[] args) {
        NumberPrinter printer = new NumberPrinter();

        // Create even and odd threads
        Thread evenThread = new Thread(() -> printer.printEven());
        Thread oddThread = new Thread(() -> printer.printOdd());

        // Start the threads
        evenThread.start();
        oddThread.start();

        // Wait for threads to finish
        try {
            evenThread.join();
            oddThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
