package nl.inholland.javafx.Exception;

public class ZeroTicketsException extends RuntimeException {
    public ZeroTicketsException() {
        super("Please choose an amount");
    }
}
