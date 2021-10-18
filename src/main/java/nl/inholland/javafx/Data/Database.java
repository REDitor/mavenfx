package nl.inholland.javafx.Data;

import nl.inholland.javafx.Model.Theatre.Movie;
import nl.inholland.javafx.Model.Theatre.MovieShowing;
import nl.inholland.javafx.Model.Theatre.Room;
import nl.inholland.javafx.Model.User.Admin;
import nl.inholland.javafx.Model.User.User;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static final String PATH = "src/main/resources/%s";
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    private List<User> users;
    private List<Movie> movies;
    private Room room1;
    private Room room2;

    public Room getRoom1() {
        return room1;
    }

    public Room getRoom2() {
        return room2;
    }

    public Database() {
        users = new ArrayList<>();
        users.addAll(getUsers());
        movies = new ArrayList<>();
        movies.addAll(getMovies());
        room1 = new Room(1, 200);
        room2 = new Room(2, 100);
        addInitialShowings();
    }

    //region Rooms
    private void addInitialShowings() {
        //Room1
        room1.addShowing(
                new MovieShowing(
                        LocalDateTime.parse("09-10-2021 20:00", dateTimeFormatter),
                        movies.get(0),
                        room1.getNumberOfSeats(),
                        1
                )
        );
        room1.addShowing(
                new MovieShowing(
                        LocalDateTime.parse("10-10-2021 22:30", dateTimeFormatter),
                        movies.get(1),
                        room1.getNumberOfSeats(),
                        1
                )
        );

        //Room2
        room2 = new Room(2, 100);
        room2.addShowing(
                new MovieShowing(
                        LocalDateTime.parse("09-10-2021 20:00", dateTimeFormatter),
                        movies.get(1),
                        room1.getNumberOfSeats(),
                        2
                )
        );
        room2.addShowing(
                new MovieShowing(
                        LocalDateTime.parse("09-10-2021 22:00", dateTimeFormatter),
                        movies.get(0),
                        room1.getNumberOfSeats(),
                        2
                )
        );
    }
    //endregion

    //region Users
    public List<User> getUsers() {
        try {
            List<String> strings = Files.readAllLines(Paths.get(String.format(PATH, "users.csv")));
            for (String line : strings) {
                if (line.split(",")[2].equals("admin")) {
                    users.add(
                            new Admin(
                                    line.split(",")[0],
                                    line.split(",")[1]
                            )
                    );
                } else {
                    users.add(
                            new User(
                                    line.split(",")[0],
                                    line.split(",")[1]
                            )
                    );
                }
            }

        } catch (FileNotFoundException fnfe) {
            //TODO: implement throwing
        } catch (IOException ioe) {
            //...
        }

        return users;
    }
    //endregion

    //region Movies
    public List<Movie> getMovies() {
        try {
            List<String> strings = Files.readAllLines(Paths.get(String.format(PATH, "movies.csv")));
            for (String line : strings) {
                movies.add(
                        new Movie(
                                line.split(",")[0],
                                Double.parseDouble(line.split(",")[1]),
                                Duration.ofMinutes(Long.parseLong(line.split(",")[2]))
                        )
                );
            }
        } catch (FileNotFoundException fnfe) {
            //
        } catch (IOException ioe) {
            //.....
        }

        return movies;
    }
    //endregion
}