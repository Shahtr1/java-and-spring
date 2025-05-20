package src.streams;

import java.util.function.BinaryOperator;
import java.util.stream.Stream;

public class Reduce {
    public static void main(String[] args) {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        // Stream<Integer> stream = Stream.of(1);
        // Stream<Integer> stream = Stream.empty();
        BinaryOperator<Integer> sum = (i1, i2) -> i1 + i2;
        Integer id = 0;

        int red = stream.reduce(id, sum);

        System.out.println(red);
    }
}
