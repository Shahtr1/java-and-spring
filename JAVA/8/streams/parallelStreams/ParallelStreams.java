package src.streams.parallelStreams;

import java.util.Arrays;
import java.util.List;

public class ParallelStreams {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        int sum = numbers.parallelStream()
                .mapToInt(Integer::intValue)
                .sum();

        System.out.println("Sum: " + sum);
    }

}
