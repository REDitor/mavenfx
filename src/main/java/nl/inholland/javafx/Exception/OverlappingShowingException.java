package nl.inholland.javafx.Exception;

import nl.inholland.javafx.Model.Theatre.Room;

public class OverlappingShowingException extends Exception {
    @Override
    public String getMessage() {
        return "Another movie is already scheduled to play in that room during the selected time, please select another moment";
    }
}
