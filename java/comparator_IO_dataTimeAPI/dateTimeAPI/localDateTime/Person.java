package comparator_IO_dataTimeAPI.dateTimeAPI.localDateTime;

import java.time.LocalDate;

public class Person {
    private String name;

    private LocalDate dateOfBirth;

    public Person() {

    }

    public Person(String name, LocalDate dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", dateOfBirth=" + dateOfBirth + "]";
    }

}
