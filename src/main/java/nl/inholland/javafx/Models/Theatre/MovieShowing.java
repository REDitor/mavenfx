package nl.inholland.javafx.Models.Theatre;

import java.time.LocalDateTime;

public class MovieShowing {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Movie movie;
    private int availableTickets;
    private Room room;

    public LocalDateTime getEndTime( return startTime + movie.getDuration());

    public MovieShowing(LocalDateTime startTime, LocalDateTime endTime, Movie movie, int availableTickets, Room room) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.movie = movie;
        this.availableTickets = availableTickets;
        this.room = room;
    }
}
