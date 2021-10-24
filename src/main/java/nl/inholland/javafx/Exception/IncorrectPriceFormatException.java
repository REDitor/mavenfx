package nl.inholland.javafx.Exception;

public class IncorrectPriceFormatException extends RuntimeException {
    public IncorrectPriceFormatException() {
        super("Please enter the price of the movie in the correct format (0.00)");
    }
}
