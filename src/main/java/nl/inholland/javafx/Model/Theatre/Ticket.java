package nl.inholland.javafx.Model.Theatre;

import java.time.LocalDateTime;

public class Ticket {
    private int roomNumber;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String title;
    private String name;

    public int getRoomNumber() {
        return roomNumber;
    }
    public String getTitle() {
        return title;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Ticket(int roomNumber, LocalDateTime startTime, LocalDateTime endTime, String title, String name) {
        this.roomNumber = roomNumber;
        this.startTime = startTime;
        this.endTime = endTime;
        this.title = title;
        this.name = name;
    }
}
