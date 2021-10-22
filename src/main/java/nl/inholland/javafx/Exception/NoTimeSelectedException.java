package nl.inholland.javafx.Exception;

import java.util.IllegalFormatException;

public class NoTimeSelectedException extends RuntimeException {
    public NoTimeSelectedException() {
        super("Please select a start time first");
    }
}
