package nl.inholland.javafx.UI.View;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import nl.inholland.javafx.Data.Database;
import nl.inholland.javafx.Model.Theatre.MovieShowing;

import java.time.LocalDateTime;

public class TicketView {
    private Database db;
    ObservableList<MovieShowing> showingsRoom1 = FXCollections.observableArrayList(db.getRoom1().getShowings());
    ObservableList<MovieShowing> showingsRoom2 = FXCollections.observableArrayList(db.getRoom2().getShowings());

    //region Elements
    //region MovieShowings/Rooms
    HBox hBox;

    VBox vBoxRoom1;
    Label lblRoom1;
    TableView<MovieShowing> tbvRoom1;

    VBox vBoxRoom2;
    Label lblRoom2;
    TableView<MovieShowing> tbvRoom2;

    TableColumn<MovieShowing, LocalDateTime> colStartTime;
    TableColumn<MovieShowing, LocalDateTime> colEndTime;
    TableColumn<MovieShowing, String> colTitle;
    TableColumn<MovieShowing, Integer> colSeats;
    TableColumn<MovieShowing, Double> colPrice;
    //endregion

    //region SellTicketOptions
    GridPane gridPane;
    Label lblRoom;
    Label lblRoomResult;
    Label lblMovieTitle;
    Label lblMovieTitleResult;
    Label lblStartTime;
    Label lblStartTimeResult;
    Label lblNrOfSeats;
    ChoiceBox<Integer> chbNrOfSeatsResult;
    Button btnPurchase;
    Label lblEndTime;
    Label lblEndTimeResult;
    Label lblName;
    TextField txtNameResult;
    Button btnClear;
    //endregion
    //endregion

    public HBox getView() {
        return hBox;
    }

    public TicketView(Database db) {
        this.db = db;

        setTableViews();
        setEventHandlers();
    }

    private void setEventHandlers() {

    }

    //region TableViews
    private void setTableViews() {
        hBox = new HBox();

        vBoxRoom1 = new VBox();
        lblRoom1 = new Label("Room 1");
        tbvRoom1 = new TableView<>();

        vBoxRoom2 = new VBox();
        lblRoom2 = new Label("Room 2");
        tbvRoom2 = new TableView<>();

        gridPane = setGridPane();

        colStartTime = new TableColumn<>("Start");
        colStartTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));

        colEndTime = new TableColumn<>("End");
        colEndTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));

        colTitle = new TableColumn<>("Title");
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));

        colSeats = new TableColumn<>("Seats");
        colSeats.setCellValueFactory(new PropertyValueFactory<>("seats"));

        colPrice = new TableColumn<>("Price");
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        assignSections();
    }

    private void assignSections() {
        tbvRoom1.getColumns().addAll(colStartTime, colEndTime, colTitle, colSeats, colPrice);
        vBoxRoom1.getChildren().addAll(lblRoom1, tbvRoom1);

        tbvRoom2.getColumns().addAll(colStartTime, colEndTime, colTitle, colSeats, colPrice);
        vBoxRoom2.getChildren().addAll(lblRoom2, tbvRoom2);

        hBox.getChildren().addAll(vBoxRoom1, vBoxRoom2, gridPane);
    }
    //endregion

    //region GridPane
    private GridPane setGridPane() {
        lblRoom = new Label("Room:");
        lblRoomResult = new Label();
        lblMovieTitle = new Label("Movie Title:");
        lblMovieTitleResult = new Label();
        lblStartTime = new Label("Start:");
        lblStartTimeResult = new Label();
        lblNrOfSeats = new Label("No. of seats:");

        chbNrOfSeatsResult = new ChoiceBox<>();
        chbNrOfSeatsResult.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9);

        btnPurchase = new Button("Purchase");
        lblEndTime = new Label("End:");
        lblEndTimeResult = new Label();
        lblName = new Label("Name:");
        txtNameResult = new TextField();
        btnClear = new Button("Clear");

        assignGrid();

        return gridPane;
    }

    private void assignGrid() {
        GridPane.setConstraints(lblRoom, 0, 0);
        GridPane.setConstraints(lblRoomResult, 1, 0);
        GridPane.setConstraints(lblMovieTitle, 2, 0);
        GridPane.setConstraints(lblMovieTitleResult, 3, 0);
        GridPane.setConstraints(lblStartTime, 0, 1);
        GridPane.setConstraints(lblStartTimeResult, 1, 1);
        GridPane.setConstraints(lblNrOfSeats, 2, 1);
        GridPane.setConstraints(chbNrOfSeatsResult, 3, 1);
        GridPane.setConstraints(btnPurchase, 5, 1);
        GridPane.setConstraints(lblEndTime, 0, 2);
        GridPane.setConstraints(lblEndTimeResult, 1, 2);
        GridPane.setConstraints(lblName, 2, 2);
        GridPane.setConstraints(txtNameResult, 3, 2, 2, 1);
        GridPane.setConstraints(btnClear, 5, 2);

        gridPane.getChildren().addAll(
                lblRoom, lblRoomResult, lblMovieTitle, lblMovieTitleResult, lblStartTime, lblStartTimeResult,
                lblNrOfSeats, chbNrOfSeatsResult, btnPurchase, lblEndTime, lblEndTimeResult, lblName, txtNameResult,
                btnClear
        );
    }
    //endregion
}
