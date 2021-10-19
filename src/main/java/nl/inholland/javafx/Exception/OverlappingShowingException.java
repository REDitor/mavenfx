package nl.inholland.javafx.Exception;

public class OverlappingShowingException extends RuntimeException {
    public OverlappingShowingException() {
        super("Another movie is already scheduled to play in that room during the selected time, please select another moment");
    }
}
