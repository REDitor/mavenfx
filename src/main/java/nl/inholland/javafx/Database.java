package nl.inholland.javafx;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private List<Person> people = new ArrayList<>();

    public List<Person> getPeople() {return people; }

    public Database() {
        people.add(new Person("George", "Washington", LocalDate.of(1732, 2, 22)));
        people.add(new Person("John", "Adams", LocalDate.of(1735, 10, 30)));
        people.add(new Person("Andre", "Harks", LocalDate.of(1963, 3, 26)));
        people.add(new Person("Yvonne", "Bekema", LocalDate.of(1967, 4, 19)));
        people.add(new Person("Daphne", "Harks", LocalDate.of(1994, 11, 4)));
    }
}