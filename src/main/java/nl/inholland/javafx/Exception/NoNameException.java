package nl.inholland.javafx.Exception;

public class NoNameException extends RuntimeException {
    public NoNameException() {
        super("Please enter a name");
    }
}
