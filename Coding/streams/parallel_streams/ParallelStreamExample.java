package streams.parallel_streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ParallelStreamExample {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("alice", "bob", "charlie", "diana", "edward", "frank");

        System.out.println("Sequential Stream:");
        long start1 = System.currentTimeMillis();

        List<String> result1 = names.stream()
                .map(ParallelStreamExample::processName)
                .collect(Collectors.toList());

        long end1 = System.currentTimeMillis();
        System.out.println("Result: " + result1);
        System.out.println("Time Taken (Sequential): " + (end1 - start1) + " ms");

        System.out.println("\nParallel Stream:");
        long start2 = System.currentTimeMillis();

        List<String> result2 = names.parallelStream()
                .map(ParallelStreamExample::processName)
                .collect(Collectors.toList());

        long end2 = System.currentTimeMillis();
        System.out.println("Result: " + result2);
        System.out.println("Time Taken (Parallel): " + (end2 - start2) + " ms");
    }

    // Simulate slow processing
    private static String processName(String name) {
        try {
            Thread.sleep(500); // simulate delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return name.toUpperCase();
    }
}
