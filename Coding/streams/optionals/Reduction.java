package streams.optionals;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Reduction {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(-1, -10);
        // Integer res = list.stream().reduce(0, Integer::max); // wrong

        Optional<Integer> opt = list.stream().reduce(Integer::max);

        if (opt.isPresent())
            System.out.println(opt.get());
        else
            System.out.println("Error");
    }
}
