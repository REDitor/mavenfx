package nl.inholland.javafx.Exception;

public class IdenticalMovieException extends Exception {
    @Override
    public String getMessage() {
        return "This movie already exists";
    }
}
