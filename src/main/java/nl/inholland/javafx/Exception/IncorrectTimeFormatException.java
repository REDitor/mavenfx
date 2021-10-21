package nl.inholland.javafx.Exception;

import java.util.IllegalFormatException;

public class IncorrectTimeFormatException extends IllegalArgumentException {
    public IncorrectTimeFormatException() {
        super("Incorrect time format, please check if your input matches the following: 00:00");
    }
}
