package nl.inholland.javafx.Exception;

public class NoRoomSelectedException extends RuntimeException {
    public NoRoomSelectedException() {
        super("Please select a room first");
    }
}
