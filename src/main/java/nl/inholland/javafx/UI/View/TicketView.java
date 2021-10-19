package nl.inholland.javafx.UI.View;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import nl.inholland.javafx.Data.Database;
import nl.inholland.javafx.Model.Theatre.MovieShowing;
import nl.inholland.javafx.Model.Theatre.Room;
import nl.inholland.javafx.Model.Theatre.Ticket;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TicketView implements View {
    //TODO: Put duplicates into mainwindow class

    //FIXME: Format tableview items correctly

    private Database db;
    ObservableList<MovieShowing> showingsRoom1;
    ObservableList<MovieShowing> showingsRoom2;
    List<Ticket> soldTickets;
    Room room1;
    Room room2;
    MovieShowing selectedShowing;

    //region Elements
    VBox vBoxMainContainer;
    Label lblTitle = new Label("Purchase Tickets");

    //region MovieShowings/Rooms
    HBox hBoxTableViews;

    VBox vBoxRoom1;
    Label lblRoom1;
    TableView<MovieShowing> tableViewRoom1;

    VBox vBoxRoom2;
    Label lblRoom2;
    TableView<MovieShowing> tableViewRoom2;

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
    ChoiceBox<Integer> choiceBoxNrOfSeatsResult;
    Button btnPurchase;
    Label lblEndTime;
    Label lblEndTimeResult;
    Label lblName;
    TextField txtNameResult;
    Button btnClear;
    //endregion

    //region ErrorMessageBox
    HBox hBoxErrorMessage;
    Label lblErrorMessage;
    //endregion
    //endregion

    public TicketView(Database db) {
        this.db = db;
        room1 = db.getRoom1();
        room2 = db.getRoom2();
        showingsRoom1 = FXCollections.observableArrayList(room1.getShowings());
        showingsRoom2 = FXCollections.observableArrayList(room2.getShowings());
        soldTickets = new ArrayList<>();

        assignSections();
        styleView();
        setEventHandlers();
    }

    //region Interface
    @Override
    public VBox getView() {
        return vBoxMainContainer;
    }

    @Override
    public void assignSections() {
        setTableViews();
        setGridPane();
        setErrorBox();

        vBoxMainContainer = new VBox();
        vBoxMainContainer.getChildren().addAll(lblTitle, hBoxTableViews, gridPane, hBoxErrorMessage); //add all containers to the parent container
    }

    @Override
    public void styleView() {
        lblTitle.setStyle("-fx-text-fill: #19295e; -fx-font-size: 16");
        vBoxMainContainer.getStylesheets().add("css/style.css");
        vBoxMainContainer.setId("view");
        hBoxTableViews.setId("tableViewContainer");

        vBoxRoom1.setId("tableView");
        vBoxRoom1.setPadding(new Insets(0, 5, 10, 0));
        vBoxRoom1.setMinWidth(625);

        vBoxRoom2.setId("tableView");
        vBoxRoom2.setMinWidth(625);
        vBoxRoom2.setPadding(new Insets(0, 0, 10, 5));

        gridPane.setId("insertOptions");
        hBoxErrorMessage.setId("errorBox");
        hBoxErrorMessage.minHeight(30);
    }

    @Override
    public void refreshView() {

    }

    @Override
    public void setEventHandlers() {
        //check if selected showing is in room 1
        tableViewRoom1.setOnMouseClicked(e -> {
            if (tableViewRoom1.getSelectionModel().getSelectedItem() != null) {
                tableViewRoom2.getSelectionModel().clearSelection();
                selectedShowing = tableViewRoom1.getSelectionModel().getSelectedItem();
                loadSelectionInfo(selectedShowing, room1);
            }
        });

        tableViewRoom1.setColumnResizePolicy(new Callback<TableView.ResizeFeatures, Boolean>() {
            @Override
            public Boolean call(TableView.ResizeFeatures resizeFeatures) {
                return true;
            }
        });

        //check if selected showing is in room 2
        tableViewRoom2.setOnMouseClicked(e -> {
            if (tableViewRoom2.getSelectionModel().getSelectedItem() != null) {
                tableViewRoom1.getSelectionModel().clearSelection();
                selectedShowing = tableViewRoom2.getSelectionModel().getSelectedItem();
                loadSelectionInfo(selectedShowing, room2);
            }
        });

        btnPurchase.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int numberOfTickets = choiceBoxNrOfSeatsResult.getSelectionModel().getSelectedItem();
                if (selectedShowing != null && (showingsRoom1.contains(selectedShowing) || showingsRoom2.contains(selectedShowing))) {
                    sellTickets(selectedShowing, numberOfTickets);
                }
            }
        });
    }
    //endregion

    private void setTableViews() {
        hBoxTableViews = new HBox();

        vBoxRoom1 = new VBox();
        lblRoom1 = new Label("Room 1");
        tableViewRoom1 = new TableView<>();

        vBoxRoom2 = new VBox();
        lblRoom2 = new Label("Room 2");
        tableViewRoom2 = new TableView<>();

        createTableColumns(tableViewRoom1);
        createTableColumns(tableViewRoom2);

        tableViewRoom1.setItems(showingsRoom1);
        vBoxRoom1.getChildren().addAll(lblRoom1, tableViewRoom1); //add tableview for room 1 to a container

        tableViewRoom2.setItems(showingsRoom2);
        vBoxRoom2.getChildren().addAll(lblRoom2, tableViewRoom2); //add tableview for room 2 to a container

        hBoxTableViews.getChildren().addAll(vBoxRoom1, vBoxRoom2); //add both tableview containers to a container
    }

    private void createTableColumns(TableView<MovieShowing> tableView) {
        TableColumn<MovieShowing, LocalDateTime> colStartTime;
        TableColumn<MovieShowing, LocalDateTime> colEndTime;
        TableColumn<MovieShowing, String> colTitle;
        TableColumn<MovieShowing, Integer> colSeats;
        TableColumn<MovieShowing, Double> colPrice;

        colStartTime = new TableColumn<>("Start");
        colStartTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));

        colEndTime = new TableColumn<>("End");
        colEndTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));

        colTitle = new TableColumn<>("Title");
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));

        colSeats = new TableColumn<>("Seats");
        colSeats.setCellValueFactory(new PropertyValueFactory<>("numberOfSeats"));

        colPrice = new TableColumn<>("Price");
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        tableView.getColumns().addAll(colStartTime, colEndTime, colTitle, colSeats, colPrice);
    }

    private void loadSelectionInfo(MovieShowing showing, Room room) {
        lblRoomResult.setText(String.format("Room %d", room.getRoomNumber()));
        lblMovieTitleResult.setText(showing.getTitle());
        lblStartTimeResult.setText(showing.getStartTime().toString());
        lblEndTimeResult.setText(showing.getEndTime().toString());
    }

    private void setGridPane() {
        gridPane = new GridPane();

        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(20);
        gridPane.setHgap(40);

        lblRoom = new Label("Room:");
        lblRoomResult = new Label();
        lblMovieTitle = new Label("Movie Title:");
        lblMovieTitleResult = new Label();
        lblStartTime = new Label("Start:");
        lblStartTimeResult = new Label();
        lblNrOfSeats = new Label("No. of seats:");

        choiceBoxNrOfSeatsResult = new ChoiceBox<>();
        choiceBoxNrOfSeatsResult.getItems().addAll(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        choiceBoxNrOfSeatsResult.setValue(0);

        btnPurchase = new Button("Purchase");
        lblEndTime = new Label("End:");
        lblEndTimeResult = new Label();
        lblName = new Label("Name:");
        txtNameResult = new TextField();
        btnClear = new Button("Clear");

        assignGrid();
    }

    private void assignGrid() {
        GridPane.setConstraints(lblRoom, 0, 0);
        GridPane.setConstraints(lblRoomResult, 1, 0);
        GridPane.setConstraints(lblMovieTitle, 2, 0);
        GridPane.setConstraints(lblMovieTitleResult, 3, 0);
        GridPane.setConstraints(lblStartTime, 0, 1);
        GridPane.setConstraints(lblStartTimeResult, 1, 1);
        GridPane.setConstraints(lblNrOfSeats, 2, 1);
        GridPane.setConstraints(choiceBoxNrOfSeatsResult, 3, 1);
        GridPane.setConstraints(btnPurchase, 5, 1);
        GridPane.setConstraints(lblEndTime, 0, 2);
        GridPane.setConstraints(lblEndTimeResult, 1, 2);
        GridPane.setConstraints(lblName, 2, 2);
        GridPane.setConstraints(txtNameResult, 3, 2, 2, 1);
        GridPane.setConstraints(btnClear, 5, 2);

        gridPane.getChildren().addAll(
                lblRoom, lblRoomResult, lblMovieTitle, lblMovieTitleResult, lblStartTime, lblStartTimeResult,
                lblNrOfSeats, choiceBoxNrOfSeatsResult, btnPurchase, lblEndTime, lblEndTimeResult, lblName, txtNameResult,
                btnClear
        );
    }

    private void setErrorBox() {
        //FIXME: errorbox not showing up
        hBoxErrorMessage = new HBox();
        lblErrorMessage = new Label();

        hBoxErrorMessage.getChildren().add(lblErrorMessage);
    }

    private void sellTickets(MovieShowing showing, int numberOfTickets) {
        if (ticketsLeft(numberOfTickets, showing)) {
            showing.deductAvailableTickets(numberOfTickets); //decrement available tickets for showing
        }
    }

    private boolean ticketsLeft(int numberOfTickets, MovieShowing showing) {
        return numberOfTickets <= showing.getAvailableTickets(); //check if tickets are available
    }
}
