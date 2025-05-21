package streams.parallel_streams;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CollectOperation {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("hello", "world",
                "parallel", "stream", "example");

        // Grouping word lengths using parallel stream
        Map<Integer, List<String>> wordGroups = words.parallelStream()
                .collect(Collectors.groupingByConcurrent(String::length));

        System.out.println("Words grouped by length: " + wordGroups);
    }
}
