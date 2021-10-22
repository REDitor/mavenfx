package nl.inholland.javafx.UI.View;

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

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

//FIXME: Room displaying opposite seat numbers
//FIXME: Labels not showing for rooms
//FIXME: Endtime not displaying when adding date and/or time

//TODO: Find way to format movies while also being able to store back as movie
//TODO: ManageMovies (Entire thing)
//TODO: Logout button (Entire thing)

public class ManageShowingsView extends View {
    Movie selectedMovie;
    Room selectedRoom;
    LocalDateTime selectedDateTime;
    LocalDate selectedDate;
    LocalTime selectedTime;

    //region ManageShowingsView Elements
    //region gridPane
    Label lblMovieTitle;
    ChoiceBox<String> choiceBoxMovieResult;
    Label lblStartDateTime;
    DatePicker datePickerStartDateResult;
    ChoiceBox<String> choiceBoxStartTimeResult;
    Label lblRoom;
    ChoiceBox<String> choiceBoxRoomResult;
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
        lblViewHeader = new Label();
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
        GridPane.setConstraints(choiceBoxStartTimeResult, 4, 0);
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
                choiceBoxStartTimeResult, lblRoom, choiceBoxRoomResult, lblEndTime, lblEndTimeResult, btnAddShowing,
                lblNrOfSeats, lblNrOfSeatsResult, lblPrice, lblPriceResult, btnClear
        );
    }

    @Override
    void setEventHandlers() {
        choiceBoxMovieResult.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                for (Movie movie : db.getMovies()) {
                    if (movie.getTitle().equals(choiceBoxMovieResult.getSelectionModel().getSelectedItem())) {
                        selectedMovie = movie;
                        break;
                    }
                }
                lblPriceResult.setText(String.format("%.2f", selectedMovie.getPrice()));
            }
        });

        StringProperty dateProperty = datePickerStartDateResult.getEditor().textProperty();
        dateProperty.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                String dateString = datePickerStartDateResult.getEditor().getText();
                if (!dateString.equals(""))
                    selectedDate = LocalDate.parse(dateString, dateFormatter);
            }
        });

        choiceBoxStartTimeResult.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                String timeString = choiceBoxStartTimeResult.getSelectionModel().getSelectedItem();
                if (!timeString.equals(""))
                    selectedTime = LocalTime.parse(timeString, timeFormatter);

                if (!datePickerStartDateResult.getEditor().getText().equals(""))
                    fillLabelEndTime(selectedDate, selectedTime);
            }
        });

        choiceBoxRoomResult.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                if (choiceBoxRoomResult.getSelectionModel().getSelectedItem() == room1.toString())
                    selectedRoom = room1;
                else if (choiceBoxRoomResult.getSelectionModel().getSelectedItem() == room2.toString())
                    selectedRoom = room2;
                else
                    selectedRoom = null;

                if (selectedRoom != null)
                    lblNrOfSeatsResult.setText(String.format("%d", selectedRoom.getNumberOfSeats()));
            }
        });

        btnAddShowing.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    MovieShowing showing = new MovieShowing(selectedDateTime, selectedMovie,
                            selectedRoom.getNumberOfSeats(), selectedRoom);
                    if (checkShowingInput(showing, selectedRoom, selectedMovie)) {
                        selectedRoom.addShowing(
                                new MovieShowing(
                                        selectedDateTime,
                                        selectedMovie,
                                        selectedRoom.getNumberOfSeats(),
                                        selectedRoom
                                )
                        );
                    }
                } catch (NoMovieSelectedException | NoTimeSelectedException
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
            }
        });
    }

    @Override
    void clearFields() {
        choiceBoxMovieResult.setValue(null);
        datePickerStartDateResult.setValue(null);
        choiceBoxStartTimeResult.setValue(null);
        choiceBoxRoomResult.setValue(null);
    }

    private void instantiateElements() {
        lblMovieTitle = new Label("Movie Title:");
        choiceBoxMovieResult = new ChoiceBox<>();
        for (Movie movie : db.getMovies()) {
            choiceBoxMovieResult.getItems().add(movie.getTitle());
        }
        lblStartDateTime = new Label("Start:");
        datePickerStartDateResult = new DatePicker();
        choiceBoxStartTimeResult = new ChoiceBox<>();
        choiceBoxStartTimeResult.getItems().addAll(getStartTimes());
        lblRoom = new Label("Room:");
        choiceBoxRoomResult = new ChoiceBox<>();
        choiceBoxRoomResult.getItems().addAll(room1.toString(), room2.toString());
        lblEndTime = new Label("End:");
        lblEndTimeResult = new Label();
        btnAddShowing = new Button("Add Showing");
        lblNrOfSeats = new Label("No. of Seats:");
        lblNrOfSeatsResult = new Label();
        lblPrice = new Label("Price:");
        lblPriceResult = new Label();
        btnClear = new Button("Clear");
    }

    private MovieShowing setShowing(MovieShowing showing) {
        showing.setTitle(selectedMovie.getTitle());
        showing.setNumberOfSeats(selectedRoom.getNumberOfSeats());
        showing.setAvailableTickets(selectedRoom.getNumberOfSeats());
        showing.setPrice(selectedMovie.getPrice());
        showing.setStartTime(selectedDateTime);
        showing.
    }

    private boolean checkShowingInput(MovieShowing showing, Room room, Movie movie) {
        if (movie == null)
            throw new NoMovieSelectedException();

        if (datePickerStartDateResult.getEditor() == null)
            throw new NoDateSelectedException();

        if (room == null)
            throw new NoRoomSelectedException();

        if (choiceBoxStartTimeResult.getSelectionModel().getSelectedItem() == null)
            throw new NoTimeSelectedException();

        LocalDateTime newStart = showing.getStartTime().minusMinutes(room.getRoomBreak().toMinutes());
        LocalDateTime newEnd = showing.getEndTime().plusMinutes(room.getRoomBreak().toMinutes());

        for (MovieShowing existingShowing : room.getShowings()) {
            LocalDateTime existingStart = existingShowing.getStartTime().minusMinutes(room.getRoomBreak().toMinutes());
            LocalDateTime existingEnd = existingShowing.getEndTime().plusMinutes(room.getRoomBreak().toMinutes());

            if (isOverlapping(newStart, newEnd, existingStart, existingEnd)) {
                throw new OverlappingShowingException();
            }
        }
        return true;
    }

    private void fillLabelEndTime(LocalDate date, LocalTime time) {
        selectedDateTime = LocalDateTime.of(date, time);
        LocalDateTime endTimeResult = selectedDateTime.plusMinutes(selectedMovie.getDuration().toMinutes());
        lblEndTimeResult.setText(endTimeResult.format(dateTimeFormatter));
    }

    private String[] getStartTimes() {
        return new String[]{
                "16:00", "16:15", "16:30", "16:45",
                "17:00", "17:15", "17:30", "17:45",
                "18:00", "18:15", "18:30", "18:45",
                "19:00", "19:15", "19:30", "19:45",
                "20:00", "20:15", "20:30", "20:45",
                "21:00", "21:15", "21:30", "21:45",
                "22:00", "22:15", "22:30", "22:45",
                "23:00", "23:15", "23:30", "23:45"
        };
    }

    private boolean isOverlapping(LocalDateTime newStart, LocalDateTime newEnd, LocalDateTime existStart, LocalDateTime existEnd) {
        return newStart.isBefore(existEnd) && existStart.isBefore(newEnd);
    }
}
