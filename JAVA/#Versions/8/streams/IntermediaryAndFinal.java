package src.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class IntermediaryAndFinal {

    public static void main(String[] args) {
        Stream<String> stream = Stream.of("one", "two", "three", "four", "five");
        Predicate<String> p1 = p -> p.equals("one");
        Predicate<String> p2 = p -> p.equals("two");

        List<String> result = new ArrayList<>();

        stream
                .peek(System.out::println)
                .filter(p1.or(p2))
                // .peek(result::add); // Intermediary
                .forEach(result::add);

        System.out.println("Done");
        System.out.println("size " + result.size());
    }

}
