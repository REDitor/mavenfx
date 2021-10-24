package nl.inholland.javafx.Exception;

public class NoDurationSelectedException extends RuntimeException {
    public NoDurationSelectedException() {
        super("Please select duration [Full Hours] + [Minutes]");
    }
}
