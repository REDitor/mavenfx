package nl.inholland.javafx.DataAccess;

import nl.inholland.javafx.Models.Theatre.Movie;
import nl.inholland.javafx.Models.Theatre.MovieShowing;

import java.util.ArrayList;
import java.util.List;

public class Database {
    private List<MovieShowing> showings = new ArrayList<>();
    private List<Movie> movies = new ArrayList<>();

    public List<MovieShowing> getShowings() { return showings; }
    public void addShowing(MovieShowing showing) { showings.add(showing); }

    public List<Movie> getMovies() { return movies; }
    public void addMovie(Movie movie) { movies.add(movie); }
}