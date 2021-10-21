package nl.inholland.javafx.Exception;

public class OverlappingShowingException extends RuntimeException {
    public OverlappingShowingException() {
        super("Conflict with showing times, please select another moment");
    }
}
