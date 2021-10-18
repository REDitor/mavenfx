package nl.inholland.javafx.Exception;

public class IncorrectPasswordException extends Exception {
    @Override
    public String getMessage() {
        return "Incorrect password";
    }
}
