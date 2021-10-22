package nl.inholland.javafx.Exception;

public class NoDateSelectedException extends RuntimeException {
    public NoDateSelectedException() {
        super("Please select a date first");
    }
}
