package queue.periodic_reading;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.*;

public class DaemonService {

    // Simulate a blocking queue to store events
    private static final BlockingQueue<String> eventQueue = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        // Populate the queue with sample events
        populateQueue();

        // Create a ScheduledExecutorService to run the daemon periodically
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor(runnable -> {
            Thread daemonThread = new Thread(runnable);
            daemonThread.setDaemon(true); // Mark the thread as daemon
            return daemonThread;
        });

        // Schedule the daemon to run every 5 seconds
        executorService.scheduleAtFixedRate(DaemonService::processEvents, 0, 5, TimeUnit.SECONDS);

        // Ensure the main thread keeps running so the daemon can do its work
        try {
            System.out.println("Main thread sleeping for 30 seconds...");
            Thread.sleep(30000); // Simulate application running for 30 seconds
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Shut down the executor service gracefully
        executorService.shutdown();
        System.out.println("Main thread exiting, daemon service will stop.");
    }

    // Method to populate the queue with sample events
    private static void populateQueue() {
        for (int i = 1; i <= 5; i++) {
            eventQueue.offer("Event " + i); // Add events to the queue
        }
    }

    // Method to process events from the queue
    private static void processEvents() {
        System.out.println("Processing events...");

        while (!eventQueue.isEmpty()) {
            String event = eventQueue.poll(); // Retrieve and remove the event from the queue
            if (event != null) {
                writeToFile(event); // Process the event and write to file
            }
        }
    }

    // Method to write an event to a file in the output directory
    private static void writeToFile(String event) {
        try {
            Files.createDirectories(Paths.get("queue/periodic_reading/output")); // Ensure the output directory exists

            String filename = "queue/periodic_reading/output/" + event.replace(" ", "_") + ".txt";
            try (FileWriter writer = new FileWriter(filename)) {
                writer.write("Processed: " + event);
                System.out.println("Wrote event to file: " + filename);
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
