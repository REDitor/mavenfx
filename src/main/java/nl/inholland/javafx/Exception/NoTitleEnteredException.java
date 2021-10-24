package nl.inholland.javafx.Exception;

public class NoTitleEnteredException extends RuntimeException {
    public NoTitleEnteredException() {
        super("Please enter a title first");
    }
}
