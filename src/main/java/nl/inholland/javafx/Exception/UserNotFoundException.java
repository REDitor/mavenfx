package nl.inholland.javafx.Exception;

public class UserNotFoundException extends Exception {
    @Override
    public String getMessage() {
        return "User not found";
    }
}
