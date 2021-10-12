package nl.inholland.javafx.Models.Theatre;

import java.time.Duration;

public class Movie {
    private String title;
    private double price;
    private Duration duration;

    public Movie(String title, double price, Duration duration) {
        this.title = title;
        this.price = price;
        this.duration = duration;
    }
}
