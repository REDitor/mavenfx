package nl.inholland.javafx.Model.Theatre;

import java.time.Duration;
import java.util.ArrayList;

public class Room {
    private static final Duration roomBreak = Duration.ofMinutes(15);

    private int roomNumber;
    private int numberOfSeats;
    private ArrayList<MovieShowing> showings;

    public int getRoomNumber() {
        return roomNumber;
    }
    public int getNumberOfSeats() {
        return numberOfSeats;
    }
    public ArrayList<MovieShowing> getShowings() {
        return showings;
    }
    public void addShowing(MovieShowing showing) {
        showings.add(showing);
    }

    public Room(int roomNumber, int numberOfSeats) {
        this.roomNumber = roomNumber;
        this.numberOfSeats = numberOfSeats;
        this.showings = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Room " + roomNumber;
    }
}