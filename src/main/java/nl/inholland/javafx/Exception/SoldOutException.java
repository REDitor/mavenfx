package nl.inholland.javafx.Exception;

public class SoldOutException extends RuntimeException {
    public SoldOutException() {
        super("Tickets for this showing are sold out");
    }
}
