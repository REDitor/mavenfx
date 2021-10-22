package nl.inholland.javafx.Model.Theatre;

import java.time.LocalDateTime;

public class MovieShowing {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String title;
    private double price;
    private int availableTickets;
    private int numberOfSeats;

    public LocalDateTime getStartTime() {
        return startTime;
    }
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
    public LocalDateTime getEndTime() {
        return endTime;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getAvailableTickets() {
        return availableTickets;
    }
    public void setAvailableTickets(int numberOfTickets) {
        this.availableTickets = numberOfTickets;
    }
    public int getNumberOfSeats() {
        return numberOfSeats;
    }
    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public MovieShowing() {
    }

    public MovieShowing(LocalDateTime startTime, Movie movie, int availableTickets, Room room) {
        this.startTime = startTime;
        this.endTime = startTime.plusMinutes(movie.getDuration().toMinutes());
        this.title = movie.getTitle();
        this.availableTickets = availableTickets;
        this.price = movie.getPrice();
        this.numberOfSeats = room.getNumberOfSeats();
    }

    public void deductAvailableTickets(int numberOfTickets) {
        availableTickets -= numberOfTickets;
    }
}
