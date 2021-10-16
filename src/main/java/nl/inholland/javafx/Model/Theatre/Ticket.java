package nl.inholland.javafx.Model.Theatre;

import java.time.LocalDateTime;

public class Ticket {
//    private MovieShowing showing;
//    private Room room;
//    private int amountOfTickets;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String title;
    private int seats;
    private double price;

    public Ticket(MovieShowing showing, Room room) {
        this.startTime = showing.getStartTime();
        this.endTime = showing.getEndTime();
        this.title = showing.getMovie().getTitle();
        this.seats = room.getNumberOfSeats();
        this.price = showing.getMovie().getPrice();
    }
}
