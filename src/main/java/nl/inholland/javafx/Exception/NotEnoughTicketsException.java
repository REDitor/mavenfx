package nl.inholland.javafx.Exception;

public class NotEnoughTicketsException extends Exception {
    @Override
    public String getMessage() {
        return "Not enough tickets left for this showing";
    }
}
