package nl.inholland.javafx.Model.Theatre;

import java.time.LocalDateTime;

public class MovieShowing {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Movie movie;
    private int availableTickets;
    private int roomNumber;

    public LocalDateTime getStartTime() { return startTime; }
    public LocalDateTime getEndTime() { return endTime; }
    public Movie getMovie() { return movie; }

    public MovieShowing(LocalDateTime startTime, Movie movie, int availableTickets, int roomNumber) {
        this.startTime = startTime;
        this.endTime = startTime.plusMinutes(movie.getDuration().toMinutes());
        this.movie = movie;
        this.availableTickets = availableTickets;
        this.roomNumber = roomNumber;
    }
}
