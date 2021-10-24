package nl.inholland.javafx.Exception;

public class NoOrWrongPriceInputException extends RuntimeException {
    public NoOrWrongPriceInputException() {
        super("Please enter a (correct) price first (0.00)");
    }
}
