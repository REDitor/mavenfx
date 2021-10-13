package nl.inholland.javafx.Model.Theatre;

public class Ticket {
    private MovieShowing showing;
    private Room room;
    private int amountOfTickets;

    public Ticket(MovieShowing showing, Room room, int amountOfTickets) {
        this.showing = showing;
        this.room = room;
        this.amountOfTickets = amountOfTickets;
    }
}
