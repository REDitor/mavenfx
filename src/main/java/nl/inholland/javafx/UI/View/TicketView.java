package nl.inholland.javafx.UI.View;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import nl.inholland.javafx.DataAccess.Database;
import nl.inholland.javafx.Model.User.User;

import java.time.LocalDateTime;

public class TicketView {
    Database db;
    User loggedUser;

    public TicketView(Database db, User loggedUser) {
        this.db = db;
        this.loggedUser = loggedUser;
    }

    public HBox getView() {
        HBox hBox = new HBox();

        VBox vBoxRoom1 = new VBox();
        Label lblRoom1 = new Label("Room 1");
        TableView tbvRoom1 = new TableView();

        VBox vBoxRoom2 = new VBox();
        Label lblRoom2 = new Label("Room 2");
        TableView tbvRoom2 = new TableView();

        TableColumn<String, LocalDateTime> colStartTime = new TableColumn("Start");
        TableColumn<String, LocalDateTime> colEndTime = new TableColumn<>("End");
        TableColumn<String, String> colTitle = new TableColumn<>("Title");
        TableColumn<String, Integer> colSeats = new TableColumn<>("Seats");
        TableColumn<String, Double> colPrice = new TableColumn<>("Price");

        tbvRoom1.getColumns().addAll(colStartTime, colEndTime, colTitle, colSeats, colPrice);
        vBoxRoom1.getChildren().addAll(lblRoom1, tbvRoom1);

        tbvRoom2.getColumns().addAll(colStartTime, colEndTime, colTitle, colSeats, colPrice);
        vBoxRoom2.getChildren().addAll(lblRoom2, tbvRoom2);

        hBox.getChildren().addAll(vBoxRoom1, vBoxRoom2);

        return hBox;
    }
}
