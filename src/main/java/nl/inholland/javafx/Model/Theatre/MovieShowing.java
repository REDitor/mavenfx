package nl.inholland.javafx.Model.Theatre;

import java.io.Serializable;
import java.time.LocalDateTime;

public class MovieShowing implements Serializable {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String roomName;
    Movie movie;
    private String title;
    private double price;
    private int availableTickets;
    private int numberOfSeats;

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public int getAvailableTickets() {
        return availableTickets;
    }

    public void setAvailableTickets(int availableTickets) {
        this.availableTickets = availableTickets;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public MovieShowing(LocalDateTime startTime, Movie movie, int availableTickets, Room room) {
        this.startTime = startTime;
        this.endTime = startTime.plusMinutes(movie.getDuration().toMinutes());
        this.movie = movie;
        this.title = movie.getTitle();
        this.availableTickets = room.getNumberOfSeats();
        this.price = movie.getPrice();
        this.numberOfSeats = room.getNumberOfSeats();
        this.roomName = room.toString();
    }

    public void deductAvailableTickets(int numberOfTickets) {
        availableTickets -= numberOfTickets;
    }
}
