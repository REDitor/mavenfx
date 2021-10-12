package nl.inholland.javafx.DataAccess;

import nl.inholland.javafx.Models.Theatre.Movie;
import nl.inholland.javafx.Models.Theatre.MovieShowing;
import nl.inholland.javafx.Models.Theatre.Room;
import nl.inholland.javafx.Models.Users.User;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private List<MovieShowing> showings = new ArrayList<>();
    private List<Movie> movies = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private Room room1 = new Room();
    private Room room2 = new Room();

    public List<MovieShowing> getShowings() { return showings; }
    public void addShowing(MovieShowing showing) { showings.add(showing); }

    public List<Movie> getMovies() { return movies; }
    public void addMovie(Movie movie) { movies.add(movie); }

    public List<User> getUsers() { return users; }

    public Room getRoom1() { return room1; }
    public Room getRoom2() { return room2; }

    public Database() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        //TODO: find way to use duration OR find alternative to Duration type
        showings.add(
                new MovieShowing(formatter.format(LocalDateTime.of(2021, 10, 9, 20, 0)), new Movie("No time to lie", 12.00, Duration.ofMinutes())));



        //TODO: add showings, movies, users, rooms
    }
}