package nl.inholland.javafx.UI.View;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import nl.inholland.javafx.Data.Database;
import nl.inholland.javafx.Model.Theatre.Movie;
import nl.inholland.javafx.Model.Theatre.MovieShowing;
import nl.inholland.javafx.Model.Theatre.Room;

import java.time.LocalDateTime;

public class ManageShowingsView extends View {
    Movie selectedMovie;
    Room selectedRoom;
    String selectedDateTime;

    //region ManageShowingsView Elements
    //region gridPane
    Label lblMovieTitle;
    ChoiceBox<Movie> choiceBoxMovieResult;
    Label lblStartDateTime;
    DatePicker datePickerStartDateResult;
    TextField txtStartTimeResult;
    Label lblRoom;
    ChoiceBox<Room> choiceBoxRoomResult;
    Label lblEndTime;
    Label lblEndTimeResult;
    Button btnAddShowing;
    Label lblNrOfSeats;
    Label lblNrOfSeatsResult;
    Label lblPrice;
    Label lblPriceResult;

    //endregion
    //endregion
    public ManageShowingsView(Database db, Stage window) {
        super(db, window);
    }

    @Override
    void setInitialNodes() {
        lblViewHeader.setText("Manage Showings");
    }

    @Override
    void createConcreteTableViews() {
        super.setTableViews();
    }

    @Override
    void setGridPane() {
        gridPane = new GridPane();

        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(20);
        gridPane.setHgap(40);

        instantiateElements();
        assignGrid();
    }

    @Override
    void assignGrid() {
        GridPane.setConstraints(lblMovieTitle, 0, 0);
        GridPane.setConstraints(choiceBoxMovieResult, 1, 0);
        GridPane.setConstraints(lblStartDateTime, 2, 0);
        GridPane.setConstraints(datePickerStartDateResult, 3, 0);
        GridPane.setConstraints(txtStartTimeResult, 4, 0);
        GridPane.setConstraints(lblRoom, 0, 1);
        GridPane.setConstraints(choiceBoxRoomResult, 1, 1);
        GridPane.setConstraints(lblEndTime, 2, 1);
        GridPane.setConstraints(lblEndTimeResult, 3, 1, 2, 1);
        GridPane.setConstraints(btnAddShowing, 5, 1);
        GridPane.setConstraints(lblNrOfSeats, 0, 2);
        GridPane.setConstraints(lblNrOfSeatsResult, 1, 2);
        GridPane.setConstraints(lblPrice, 2, 2);
        GridPane.setConstraints(lblPriceResult, 3, 2, 2, 1);
        GridPane.setConstraints(btnClear, 5, 2);

        gridPane.getChildren().addAll(
                lblMovieTitle, choiceBoxMovieResult, lblStartDateTime, datePickerStartDateResult,
                txtStartTimeResult, lblRoom, choiceBoxRoomResult, lblEndTime, lblEndTimeResult, btnAddShowing,
                lblNrOfSeats, lblNrOfSeatsResult, lblPrice, lblPriceResult, btnClear
        );
    }

    @Override
    void setEventHandlers() {
        choiceBoxMovieResult.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                selectedMovie = choiceBoxMovieResult.getSelectionModel().getSelectedItem();
                lblPriceResult.setText(String.format("%.2f", selectedMovie.getPrice()));
                lblEndTimeResult.setText(LocalDateTime.parse());
            }
        });

        StringProperty dateProperty = datePickerStartDateResult.getText;

        choiceBoxRoomResult.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                selectedRoom = choiceBoxRoomResult.getSelectionModel().getSelectedItem();
                lblNrOfSeatsResult.setText(String.format("%d", selectedRoom.getNumberOfSeats()));
            }
        });

        btnAddShowing.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                selectedDateTime = datePickerStartDateResult.getValue() + " " + txtStartTimeResult.getText();
                selectedRoom.addShowing(
                        new MovieShowing(
                                LocalDateTime.parse(selectedDateTime, dateTimeFormatter),
                                selectedMovie,
                                room1.getNumberOfSeats(),
                                selectedRoom
                        )
                );
            }
        });

        this.btnClear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                choiceBoxMovieResult.setValue(null);
                datePickerStartDateResult.setValue(null);
                txtStartTimeResult.setText(null);
                choiceBoxRoomResult.setValue(null);

                gridPane.setVisible(false);
            }
        });
    }

    @Override
    void loadSectionInfo() {

    }

    @Override
    void clearFields() {

    }

    private void instantiateElements() {
        lblMovieTitle = new Label("Movie Title:");
        for (Movie movie : db.getMovies()) {
            choiceBoxMovieResult.getItems().add(movie);
        }

        instantiateElements();
        lblStartDateTime = new Label("Start:");
        datePickerStartDateResult = new DatePicker();
        txtStartTimeResult = new TextField();
        lblRoom = new Label("Room:");
        choiceBoxRoomResult.getItems().addAll(room1, room2);
        lblEndTime = new Label("End:");
        lblEndTimeResult = new Label();
        btnAddShowing = new Button("Add Showing");
        lblNrOfSeats = new Label("No. of Seats:");
        lblNrOfSeatsResult = new Label();
        lblPrice = new Label("Price:");
        lblPriceResult = new Label();
        btnClear = new Button("Clear");
    }

}
