package nl.inholland.javafx.DataAccess;

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
    private List<MovieShowing> showings;
    private Room room1;
    private Room room2;

    public Database() {
        users = new ArrayList<>();
        movies = new ArrayList<>();
        showings = new ArrayList<>();
        room1 = new Room(1, 200);
        room2 = new Room(2, 100);
    }

    //region Read
    public List<User> getUsers() {
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
            //...
        }

        return users;
    }

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

    public List<MovieShowing> getShowings() {
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
                                LocalDateTime.parse(line.split(",")[0], dateTimeFormatter),
                                showingMovie,
                                Integer.parseInt(line.split(",")[3]),
                                Integer.parseInt(line.split(",")[4])
                        )
                );
            }
        } catch (FileNotFoundException fnfe) {
            //......
        } catch (IOException ioe) {
            //
        }
        return showings;
    }
    //endregion

    //region Write
    public void addMovie(Movie movie) {
        try {

        } catch () {

        }
    }

    public void addShowing(MovieShowing showing) {
        try {

        } catch () {

        }
    }
    //endregion
}