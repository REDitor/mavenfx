package nl.inholland.javafx.Model.Theatre;

import java.io.Serializable;
import java.time.Duration;

public class Movie implements Serializable {
    private String title;
    private double price;
    private Duration duration;

    public Duration getDuration() {
        return duration;
    }

    public double getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public Movie(String title, double price, Duration duration) {
        this.title = title;
        this.price = price;
        this.duration = duration;
    }
}
