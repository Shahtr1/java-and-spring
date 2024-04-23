package src.streams.parallelStreams;

import java.util.stream.IntStream;

public class ReduceOperation {
    public static void main(String[] args) {
        // Product of numbers using parallel stream with reduce
        int product = IntStream.rangeClosed(1, 10)
                .parallel()
                .reduce(1, (a, b) -> a * b);

        System.out.println("Product: " + product);
    }
}
