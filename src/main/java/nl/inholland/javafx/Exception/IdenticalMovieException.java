package nl.inholland.javafx.Exception;

public class IdenticalMovieException extends RuntimeException {
    public IdenticalMovieException() {
        super("This movie already exists");
    }
}
