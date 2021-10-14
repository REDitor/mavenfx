package nl.inholland.javafx.DataAccess;

import nl.inholland.javafx.Model.Theatre.Movie;
import nl.inholland.javafx.Model.Theatre.MovieShowing;
import nl.inholland.javafx.Model.Theatre.Room;
import nl.inholland.javafx.Model.User.Admin;
import nl.inholland.javafx.Model.User.Permission;
import nl.inholland.javafx.Model.User.User;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static javafx.scene.input.KeyCode.L;

public class Database {
    private static final String PATH = "src/main/resources/%s";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");

    private List<User> users;
    private List<Movie> movies;
    private List<MovieShowing> showings;
    private Room room1;
    private Room room2;

    public List<User> getUsers() {
        return users;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public List<MovieShowing> getShowings() {
        return showings;
    }

    public void addShowing(MovieShowing showing) {
        showings.add(showing);
    }

    public Room getRoom1() {
        return room1;
    }

    public Room getRoom2() {
        return room2;
    }

    //TODO: Add .csv reading and writing

    public Database() {
        //TODO: find way to use duration OR find alternative to Duration type

        users = new ArrayList<>();
        movies = new ArrayList<>();
        showings = new ArrayList<>();
        room1 = new Room(1);
        room2 = new Room(2);
//        movies.add(new Movie("No time to lie", 12.00, Duration.ofMinutes(125)));
//        movies.add(new Movie("The Addams Family 19", 9.00, Duration.ofMinutes(92)));
//
//        //showings.addAll(movies)?
//        showings.add(new MovieShowing(formatter.format(LocalDateTime.of(2021, 10, 9, 20, 0)), new Movie("No time to lie", 12.00, Duration.ofMinutes())));
//
//
//        room1.getShowings().add();
//        room2.getShowings();
    }

    private List<User> readUsers() {
        try {
            List<String> strings = Files.readAllLines(Paths.get(String.format(PATH, "users.csv")));
            for (String line : strings) {
                if (line.split(",")[2] == "admin") {
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
            //TODO: implement throwing
        }

        return users;
    }

    public List<Movie> readMovies() {
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
            //....
        } catch (IOException ioe) {
            //....
        }

        return movies;
    }

    public List<MovieShowing> readShowings() {
        try {
            List<String> strings = Files.readAllLines(Paths.get(String.format(PATH, "showings.csv")));
            for (String line : strings) {
                Movie showingMovie = null;
                for (Movie movie : movies) {
                    if (movie.getTitle().equals(line.split(",")[2])) {    //check showingTitle
                        showingMovie = movie;
                    }
                }

                showings.add(
                        new MovieShowing(
                                dateFormat.parse(line.split(",")[0]),
                                dateFormat.parse(line.split(",")[1]),
                                showingMovie,
                                Integer.parseInt(line.split(",")[3]),
                                Integer.parseInt(line.split(",")[4])
                        )
                );
            }
        } catch (FileNotFoundException fnfe) {
            //......
        } catch (IOException | ParseException ioe) {

        }
        return showings;
    }

    public String exceptionMessage(String message) {
        return message;
    }
}