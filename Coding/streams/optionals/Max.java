package streams.optionals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Max {
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        Stream<Integer> stream = integers.stream();

        Optional<Integer> opt = stream.max(Comparator.naturalOrder());

        if (opt.isPresent()) {
            System.out.println(opt.get());
        } else {

        }
    }
}
