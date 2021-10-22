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
        MovieShowing room1Showing1 = new MovieShowing();
        room1Showing1.setStartTime(LocalDateTime.parse("09-10-2021 20:00", dateTimeFormatter));
        room1Showing1.setMovie(movies.get(0));
        room1Showing1.setAvailableTickets(room1.getNumberOfSeats());
        room1Showing1.setRoom(room1);
        room1Showing1.setTitle(movies.get(0).getTitle());
        room1Showing1.setPrice(movies.get(0).getPrice());
        room1.addShowing(room1Showing1);

        MovieShowing room1Showing2 = new MovieShowing();
        room1Showing1.setStartTime(LocalDateTime.parse("10-10-2021 22:30", dateTimeFormatter));
        room1Showing1.setMovie(movies.get(1));
        room1Showing1.setAvailableTickets(room1.getNumberOfSeats());
        room1Showing1.setRoom(room1);
        room1Showing1.setTitle(movies.get(1).getTitle());
        room1Showing1.setPrice(movies.get(1).getPrice());
        room1.addShowing(room1Showing2);

        //Room2
        MovieShowing room2Showing1 = new MovieShowing();
        room2Showing1.setStartTime(LocalDateTime.parse("09-10-2021 20:00", dateTimeFormatter));
        room2Showing1.setMovie(movies.get(1));
        room2Showing1.setAvailableTickets(room2.getNumberOfSeats());
        room2Showing1.setRoom(room2);
        room2Showing1.setTitle(movies.get(1).getTitle());
        room2Showing1.setPrice(movies.get(1).getPrice());
        room2.addShowing(room2Showing1);

        MovieShowing room2Showing2 = new MovieShowing();
        room2Showing2.setStartTime(LocalDateTime.parse("10-10-2021 22:30", dateTimeFormatter));
        room2Showing2.setMovie(movies.get(0));
        room2Showing2.setAvailableTickets(room2.getNumberOfSeats());
        room2Showing2.setRoom(room2);
        room2Showing2.setTitle(movies.get(0).getTitle());
        room2Showing2.setPrice(movies.get(0).getPrice());
        room2.addShowing(room2Showing2);

    }
    //endregion

    //region Users
    public List<User> getUsers() {
        List<User> dbUsers = new ArrayList<>();
        try {
            List<String> strings = Files.readAllLines(Paths.get(String.format(PATH, "users.csv")));
            for (String line : strings) {
                if (line.split(",")[2].equals("admin")) {
                    dbUsers.add(
                            new Admin(
                                    line.split(",")[0],
                                    line.split(",")[1]
                            )
                    );
                } else {
                    dbUsers.add(
                            new User(
                                    line.split(",")[0],
                                    line.split(",")[1]
                            )
                    );
                }
            }

        } catch (FileNotFoundException fnfe) {
            //TODO: messagedialog?
        } catch (IOException ioe) {
            //...
        }

        return dbUsers;
    }
    //endregion

    //region Movies
    public List<Movie> getMovies() {
        List<Movie> dbMovies = new ArrayList<>();
        try {
            List<String> strings = Files.readAllLines(Paths.get(String.format(PATH, "movies.csv")));
            for (String line : strings) {
                dbMovies.add(
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

        return dbMovies;
    }
    //endregion
}