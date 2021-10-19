package nl.inholland.javafx.Exception;

public class NoShowingSelectedException extends RuntimeException {
    public NoShowingSelectedException() {
        super("Please select a showing first");
    }
}
