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
import nl.inholland.javafx.Exception.*;
import nl.inholland.javafx.Model.Theatre.Movie;
import nl.inholland.javafx.Model.Theatre.MovieShowing;
import nl.inholland.javafx.Model.Theatre.Room;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.chrono.ChronoPeriod;
import java.time.temporal.ValueRange;

public class ManageShowingsView extends View {
    Movie selectedMovie;
    Room selectedRoom;
    LocalDateTime selectedDateTime;

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
            }
        });

        StringProperty dateProperty = datePickerStartDateResult.getEditor().textProperty();
        dateProperty.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                selectedDateTime = LocalDateTime.parse(datePickerStartDateResult.getEditor().getText() +
                        " " + txtStartTimeResult.getText(), dateTimeFormatter);
                LocalDateTime endTimeResult = selectedDateTime.plusMinutes(selectedMovie.getDuration().toMinutes());
                lblEndTimeResult.setText(endTimeResult.format(dateTimeFormatter));
            }
        });

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
                try {
                    MovieShowing showing = new MovieShowing(selectedDateTime, selectedMovie,
                            selectedRoom.getNumberOfSeats(), selectedRoom);
                    checkShowingInput(showing, selectedRoom, selectedMovie);
                    selectedRoom.addShowing(
                            new MovieShowing(
                                    selectedDateTime,
                                    selectedMovie,
                                    selectedRoom.getNumberOfSeats(),
                                    selectedRoom
                            )
                    );
                } catch (NoMovieSelectedException | IncorrectTimeFormatException
                        | NoRoomSelectedException | OverlappingShowingException rte) {
                    lblInfoMessage.setStyle("-fx-text-fill: red");
                    lblInfoMessage.setText(rte.getMessage());
                }
            }
        });

        this.btnClear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                clearFields();
                gridPane.setVisible(false);
            }
        });
    }

    @Override
    void clearFields() {
        choiceBoxMovieResult.setValue(null);
        datePickerStartDateResult.setValue(null);
        txtStartTimeResult.setText(null);
        choiceBoxRoomResult.setValue(null);
    }

    private void instantiateElements() {
        lblMovieTitle = new Label("Movie Title:");
        for (Movie movie : db.getMovies()) {
            choiceBoxMovieResult.getItems().add(movie);
        }

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

    private void checkShowingInput(MovieShowing showing, Room room, Movie movie) {
        if (movie == null)
            throw new NoMovieSelectedException();

        if (datePickerStartDateResult.getEditor() == null)
            throw new NoDateTimeSelectedException();

        if (room == null)
            throw new NoRoomSelectedException();

        LocalDateTime newStart = showing.getStartTime().minusMinutes(room.getRoomBreak().toMinutes());
        LocalDateTime newEnd = showing.getEndTime().plusMinutes(room.getRoomBreak().toMinutes());

        for (MovieShowing existingShowing : room.getShowings()) {
            LocalDateTime existingStart = existingShowing.getStartTime().minusMinutes(room.getRoomBreak().toMinutes());
            LocalDateTime existingEnd = existingShowing.getEndTime().plusMinutes(room.getRoomBreak().toMinutes());

            if (isOverlapping(newStart, newEnd, existingStart, existingEnd)) {
                throw new OverlappingShowingException();
            }
        }
    }

    private boolean isOverlapping(LocalDateTime newStart, LocalDateTime newEnd, LocalDateTime existStart, LocalDateTime existEnd) {
        return newStart.isBefore(existEnd) && existStart.isBefore(newEnd);
    }
}
