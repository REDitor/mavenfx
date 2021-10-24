package nl.inholland.javafx.Exception;

public class NoTimeSelectedException extends RuntimeException {
    public NoTimeSelectedException() {
        super("Please select a start time first");
    }
}
