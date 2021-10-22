package nl.inholland.javafx.Model.Theatre;

import java.time.LocalDateTime;

public class MovieShowing {
    private LocalDateTime startTime;
    Movie movie;
    Room room;
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
        return startTime.plusMinutes(movie.getDuration().toMinutes());
    }
    public Movie getMovie() {
        return movie;
    }
    public void setMovie(Movie movie) {
        this.movie = movie;
    }
    public Room getRoom() {
        return room;
    }
    public void setRoom(Room room) {
        this.room = room;
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
    public void setAvailableTickets(int availableTickets) {
        this.availableTickets = availableTickets;
    }
    public void getNumberOfSeats() {
        this.numberOfSeats = room.getNumberOfSeats();
    }

    public MovieShowing() {
    }

    public void deductAvailableTickets(int numberOfTickets) {
        availableTickets -= numberOfTickets;
    }
}
