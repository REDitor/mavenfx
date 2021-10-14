package nl.inholland.javafx.Model.Theatre;

import java.time.Duration;
import java.util.ArrayList;

public class Room {
    private static final Duration roomBreak = Duration.ofMinutes(15);
    private static final int numberOfSeats = 200;
    private ArrayList<MovieShowing> showings;
    private int roomNumber;

    public Room(int roomNumber) {
        showings = new ArrayList<>();

        this.roomNumber = roomNumber;
    }

    public ArrayList<MovieShowing> getShowings() { return showings; }
    public void addShowing(MovieShowing showing) { showings.add(showing); }
}