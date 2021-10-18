package nl.inholland.javafx.Model.Theatre;

import java.time.LocalDateTime;

public class MovieShowing {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String title;
    private double price;
    private int availableTickets;
    int roomNumber;


    public LocalDateTime getStartTime() {
        return startTime;
    }
    public LocalDateTime getEndTime() {
        return endTime;
    }
    public int getRoomNumber() {
        return roomNumber;
    }
    public String getTitle() {
        return title;
    }
    public double getPrice() {
        return price;
    }

    public MovieShowing(LocalDateTime startTime, Movie movie, int availableTickets) {
        this.startTime = startTime;
        this.endTime = startTime.plusMinutes(movie.getDuration().toMinutes());
        this.title = movie.getTitle();
        this.availableTickets = availableTickets;
        this.price = movie.getPrice();
    }
}
