package src.streams.optionals;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsEx {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("shahrukh", "shahrukh", "is", "good", "good");
        Stream<String> stream = words.stream();

        String sentence = stream.collect(Collectors.joining(" "));
        System.out.println(sentence);

        Map<String, Long> mapCounting = words.stream()
                .collect(Collectors.groupingBy(word -> word, Collectors.counting()));
        System.out.println(mapCounting);

        Map<String, String> mapConcatenation = words.stream()
                .collect(Collectors.groupingBy(word -> word,
                        Collectors.joining(" ")));

        System.out.println(mapConcatenation);

    }
}
