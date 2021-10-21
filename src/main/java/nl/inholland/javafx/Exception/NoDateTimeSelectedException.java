package nl.inholland.javafx.Exception;

public class NoDateTimeSelectedException extends RuntimeException {
    public NoDateTimeSelectedException() {
        super("Please select a date first");
    }
}
