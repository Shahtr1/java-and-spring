package comparator_IO_dataTimeAPI.dateTimeAPI.localDateTime;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class DateAndTime {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();

        Path path = Paths.get("C:", "Users", "SHAHRU", "Desktop", "test", "src",
                "dateTimeAPI", "localDateTime", "people.txt");
        try (
                Stream<String> stream = Files.lines(path)) {

            Function<String, Person> func = str -> {
                String[] s = str.split(" ");
                String name = s[0].trim();
                int year = Integer.parseInt(s[1]);
                Month month = Month.of(Integer.parseInt(s[2]));
                int day = Integer.parseInt(s[3]);

                Person p = new Person(name, LocalDate.of(year, month, day));
                persons.add(p);
                return p;
            };

            stream.map(func).forEach(System.out::println);

        } catch (

        IOException e) {
            System.err.println(e.getMessage());
        }

        LocalDate now = LocalDate.now();

        // persons.forEach(
        // p -> {
        // Period period = Period.between(p.getDateOfBirth(), now);
        // System.out.println(
        // p.getName() + " was born " +
        // period.get(ChronoUnit.YEARS) + " years and " +
        // period.get(ChronoUnit.MONTHS) + " months " +
        // "[ " + p.getDateOfBirth().until(now, ChronoUnit.MONTHS) +
        // " months ] ");
        // });

        // or

        persons.forEach(p -> {
            Period period = Period.between(p.getDateOfBirth(), now);
            System.out.println(
                    p.getName() + " was born " +
                            period.getYears() + " years and " +
                            period.getMonths() + " months " +
                            "[ " + p.getDateOfBirth().until(now, ChronoUnit.MONTHS) +
                            " months ] ");
        });
    }
}
