package nl.inholland.javafx.DataAccess;

import nl.inholland.javafx.Model.Theatre.Movie;
import nl.inholland.javafx.Model.Theatre.MovieShowing;
import nl.inholland.javafx.Model.Theatre.Room;
import nl.inholland.javafx.Model.User.Admin;
import nl.inholland.javafx.Model.User.User;

import java.time.Duration;
import java.time.LocalDateTime;
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

    //TODO: Add .csv reading and writing

    public Database() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        //TODO: find way to use duration OR find alternative to Duration type
        //showings.addAll(movies)?
        showings.add(new MovieShowing(formatter.format(LocalDateTime.of(2021, 10, 9, 20, 0)), new Movie("No time to lie", 12.00, Duration.ofMinutes())));

        movies.add(new Movie("No time to lie", 12.00, Duration.ofMinutes(125)));
        movies.add(new Movie("The Addams Family 19", 9.00, Duration.ofMinutes(92)));

        users.add(new Admin("Sander", "myPassword12*"));
        users.add(new Admin("Wimmelstein", "myPassword13*"));
        users.add(new User("Billy", "myPassword14*"));
        users.add(new User("Daniel", "myPassword15*"));

        room1.getShowings().add();
        room2.getShowings();
    }
}