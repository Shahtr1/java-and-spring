package comparator_IO_dataTimeAPI.io;

import java.io.IOException;
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

import comparator_IO_dataTimeAPI.dateTimeAPI.localDateTime.Person;

public class ReadingFromFile {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();

        // *************
        // USING InputStreamReader
        // *************

        // try (BufferedReader bufferedReader = new BufferedReader(
        // new InputStreamReader(
        // DateAndTime.class.getResourceAsStream("people.txt")));
        // Stream<String> stream = bufferedReader.lines();) {

        // *************
        // USING FileReader
        // *************

        // try (BufferedReader bufferedReader = new BufferedReader(
        // new FileReader(
        // new File(
        // "C:\\Users\\SHAHRU\\Desktop\\test\\src\\io\\people.txt")));
        // Stream<String> stream = bufferedReader.lines();) {

        // *************
        // USING PATHS
        // *************

        Path path = Paths.get("C:", "Users", "SHAHRU", "Desktop", "test", "src",
                "io", "people.txt");
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
