package nl.inholland.javafx.Exception;

public class NoShowingSelectedException extends Exception {
    @Override
    public String getMessage() {
        return "Please select a showing first";
    }
}
