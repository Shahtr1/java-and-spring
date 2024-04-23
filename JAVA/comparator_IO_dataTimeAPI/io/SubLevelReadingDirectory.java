package comparator_IO_dataTimeAPI.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class SubLevelReadingDirectory {
    public static void main(String[] args) {
        Path path = Paths.get("c:", "windows");

        // try (Stream<Path> stream = Files.walk(path)) { // All
        try (Stream<Path> stream = Files.walk(path, 2)) { // 2 levels
            stream.filter(p -> p.toFile().isDirectory())
                    .forEach(System.out::println);

        } catch (IOException e) {
            // TODO: handle exception
        }
    }

}
