package nl.inholland.javafx.UI.View;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import nl.inholland.javafx.DataAccess.Database;
import nl.inholland.javafx.Model.Theatre.Ticket;
import nl.inholland.javafx.Model.User.User;

import java.time.LocalDateTime;

public class TicketView {
    Database db;

    public TicketView(Database db) {
        this.db = db;

        setEventHandlers();
    }

    private void setEventHandlers() {

    }

    public HBox getView() {
        HBox hBox = new HBox();

        VBox vBoxRoom1 = new VBox();
        Label lblRoom1 = new Label("Room 1");
        TableView<Ticket> tbvRoom1 = new TableView<>();

        VBox vBoxRoom2 = new VBox();
        Label lblRoom2 = new Label("Room 2");
        TableView<Ticket> tbvRoom2 = new TableView<>();

        TableColumn<Ticket, LocalDateTime> colStartTime = new TableColumn<>("Start");
        colStartTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));

        TableColumn<Ticket, LocalDateTime> colEndTime = new TableColumn<>("End");
        colStartTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));

        TableColumn<Ticket, String> colTitle = new TableColumn<>("Title");
        colStartTime.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Ticket, Integer> colSeats = new TableColumn<>("Seats");
        colStartTime.setCellValueFactory(new PropertyValueFactory<>("seats"));

        TableColumn<Ticket, Double> colPrice = new TableColumn<>("Price");
        colStartTime.setCellValueFactory(new PropertyValueFactory<>("price"));

        tbvRoom1.getColumns().addAll(colStartTime, colEndTime, colTitle, colSeats, colPrice);
        vBoxRoom1.getChildren().addAll(lblRoom1, tbvRoom1);

        tbvRoom2.getColumns().addAll(colStartTime, colEndTime, colTitle, colSeats, colPrice);
        vBoxRoom2.getChildren().addAll(lblRoom2, tbvRoom2);

        hBox.getChildren().addAll(vBoxRoom1, vBoxRoom2);

        return hBox;
    }
}
