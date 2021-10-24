package nl.inholland.javafx.UI.View;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import nl.inholland.javafx.Data.Database;
import nl.inholland.javafx.Model.Theatre.Movie;
import nl.inholland.javafx.Model.User.User;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.JOptionPane.*;

public class ManageMoviesView extends View {
    Duration selectedDuration;

    //region Elements
    //region tableViewContainer
    Label lblTableViewHeader;
    TableView<Movie> tableViewMovies;
    //endregion

    //region gridPane
    Label lblMovieTitle;
    TextField txtMovieTitleResult;
    Label lblDuration;
    ChoiceBox<Integer> choiceBoxDurationHours;
    ChoiceBox<Integer> choiceBoxDurationMinutes;
    Label lblPrice;
    TextField txtPriceResult;
    Button btnAddMovie;
    //endregion
    //endregion

    public ManageMoviesView(Database db, Stage window, User user) {
        super(db, window, user);
        window.setTitle(String.format("Fabulous Cinema -- Manage Movies -- username: %s (%s)",
                user.getUsername(), user.getPermission()));
    }

    @Override
    void setInitialNodes() {
        lblViewHeader = new Label("ManageMovies");
        lblTableViewHeader = new Label("Listed Movies");
        instantiateGridPaneElements();
    }

    @Override
    void createConcreteTableViews() {
        tableViewMovies = new TableView<>();
        createTableColumns(tableViewMovies);
        tableViewMovies.setItems(listedMovies);
        tableViewMovies.setMinWidth(1250);

        tableViewContainer = new HBox();
        tableViewContainer.getChildren().add(tableViewMovies);
        tableViewContainer.setPadding(new Insets(10, 0, 10, 0));
    }

    private void createTableColumns(TableView<Movie> tableView) {
        TableColumn<Movie, String> colMovieTitle = new TableColumn<>("Title");
        colMovieTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colMovieTitle.setMinWidth(500);

        TableColumn<Movie, String> colPrice = new TableColumn<>("Price");
        colPrice.setCellValueFactory(cellData ->
                new SimpleStringProperty(currencyFormat.format(cellData.getValue().getPrice())));
        colPrice.setMinWidth(250);

        TableColumn<Movie, String> colDuration = new TableColumn<>("Duration");
        colDuration.setCellValueFactory(cellData -> {
            long mm = cellData.getValue().getDuration().toMinutes();
            String durationString = String.format("%2d minute(s)", mm);
            StringProperty durationProperty = new SimpleStringProperty(durationString);
            return durationProperty;
                });
        colDuration.setMinWidth(250);

        tableView.getColumns().addAll(colMovieTitle, colPrice, colDuration);

    }

    @Override
    void assignGrid() {
        GridPane.setConstraints(lblMovieTitle, 0, 0);
        GridPane.setConstraints(txtMovieTitleResult, 1, 0, 3, 1);
        GridPane.setConstraints(lblDuration, 0, 1);
        GridPane.setConstraints(choiceBoxDurationHours, 1, 1);
        GridPane.setConstraints(choiceBoxDurationMinutes, 2, 1);
        GridPane.setConstraints(btnAddMovie, 3, 1);
        GridPane.setConstraints(lblPrice, 0, 2);
        GridPane.setConstraints(txtPriceResult, 1, 2, 2, 1);
        GridPane.setConstraints(btnClear, 3, 2);

        gridPane.getChildren().addAll(lblMovieTitle, txtMovieTitleResult, lblDuration, choiceBoxDurationHours,
                choiceBoxDurationMinutes, btnAddMovie, lblPrice, txtPriceResult, btnClear);
    }

    @Override
    void setEventHandlers() {
        choiceBoxDurationHours.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                if (choiceBoxDurationHours.getValue() != null) {

                }
                choiceBoxDurationMinutes.setDisable(false);
            }
        });

        choiceBoxDurationMinutes.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                if (choiceBoxDurationHours.getValue() != null && choiceBoxDurationMinutes.getValue() != null) {
                    int numberOfMinutes = (choiceBoxDurationHours.getValue() * 60) + choiceBoxDurationMinutes.getValue();
                    selectedDuration = Duration.ofMinutes(Long.parseLong(String.format("%d", numberOfMinutes)));
                }
            }
        });

        btnAddMovie.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Movie newMovie = new Movie(
                        txtMovieTitleResult.getText(),
                        Double.parseDouble(txtPriceResult.getText()),
                        selectedDuration
                );
                confirmAddMovie(newMovie);
                refreshTableViews();
                window.sizeToScene();
            }
        });

        btnClear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                clearFields();
            }
        });
    }

    @Override
    void clearFields() {
        txtMovieTitleResult.clear();
        choiceBoxDurationHours.getSelectionModel().clearSelection();
        choiceBoxDurationMinutes.getSelectionModel().clearSelection();
        txtPriceResult.clear();
        lblInfoMessage.setText(null);
    }

    @Override
    void instantiateGridPaneElements() {
        lblMovieTitle = new Label("Title:");
        txtMovieTitleResult = new TextField();
        txtMovieTitleResult.setPromptText("Enter a movie title...");
        lblDuration = new Label("Duration:");
        choiceBoxDurationHours = new ChoiceBox<>();
        choiceBoxDurationHours.getItems().addAll(getHourOptions());
        choiceBoxDurationMinutes = new ChoiceBox<>();
        choiceBoxDurationMinutes.getItems().addAll(getMinuteOptions());
        choiceBoxDurationMinutes.setDisable(true);
        lblPrice = new Label("Price");
        txtPriceResult = new TextField();
        txtPriceResult.setPromptText("Enter a price (0.00)");
        btnAddMovie = new Button("Add movie");
        btnClear = new Button("Clear");
    }

    private List<Integer> getHourOptions() {
        return new ArrayList<>(List.of(
                1, 2, 3, 4, 5, 6, 7, 8, 9
        ));
    }

    private List<Integer> getMinuteOptions() {
        List<Integer> minuteOptions = new ArrayList<>();
        int minute = 0;

        for (int i = 0; i < 60; i++) {
            minuteOptions.add(minute);
            minute++;
        }

        return minuteOptions;
    }

    private void confirmAddMovie(Movie movie) {
        int result = showConfirmDialog(
                null,
                String.format("Are you sure you want to add '%s' to the listed movies", movie.getTitle()),
                "Confirm Add Movie",
                YES_NO_OPTION
        );

        if (result == YES_OPTION) {
            listedMovies.add(movie);
            db.addMovie(movie);
            lblInfoMessage.setText(String.format(
                    "Successfully added movie to the listed movies:%nTitle: %s%nDuration: %s%nPrice: %.2f",
                    movie.getTitle(), movie.getDuration().toString(), movie.getPrice()
            ));
            refreshTableViews();
            clearFields();
        } else
            lblInfoMessage.setText("[Adding Canceled]");
    }

    @Override
    public void refreshTableViews() {
        listedMovies = FXCollections.observableArrayList(db.getMovies());
        tableViewMovies.setItems(listedMovies);
    }
}

