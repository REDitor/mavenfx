package nl.inholland.javafx.Exception;

public class NoMovieSelectedException extends RuntimeException {
    public NoMovieSelectedException() {
        super("Please select a movie first");
    }
}
