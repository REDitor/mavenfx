package nl.inholland.javafx.UI.View;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;
import nl.inholland.javafx.Data.Database;
import nl.inholland.javafx.Model.Theatre.MovieShowing;
import nl.inholland.javafx.Model.Theatre.Room;
import nl.inholland.javafx.Model.User.User;

import javax.swing.text.DateFormatter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class View {
    protected final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    protected final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    protected final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    Database db;
    Stage window;

    Room room1;
    Room room2;
    ObservableList<MovieShowing> showingsRoom1;
    ObservableList<MovieShowing> showingsRoom2;
    Room selectedRoomView;
    MovieShowing selectedShowing;

    //region Elements
    VBox mainContainer;
    //region mainContainer
    Label lblViewHeader;
    HBox tableViewContainer;
    //region tableViewContainer
    VBox vBoxRoom1;
    Label lblRoom1;
    TableView<MovieShowing> tableViewRoom1;

    VBox vBoxRoom2;
    Label lblRoom2;
    TableView<MovieShowing> tableViewRoom2;

    //endregion

    VBox inputContainer;
    //region inputContainer
    GridPane gridPane;
    //region gridPane
    Button btnClear;
    //endregion

    HBox infoMessageContainer;
    //region infoMessageContainer
    Label lblInfoMessage;
    //endregion
    //endregion
    //endregion
    //endregion

    public View(Database db, Stage window) {
        this.db = db;
        this.window = window;
        room1 = db.getRoom1();
        room2 = db.getRoom2();
        showingsRoom1 = FXCollections.observableArrayList(room1.getShowings());
        showingsRoom2 = FXCollections.observableArrayList(room2.getShowings());

        loadWindow(window);
    }

    public VBox getView() {
        return mainContainer;
    }

    private void loadWindow(Stage window) {
        window.setTitle(String.format("Fabulous Cinema -- -- %s -- username: $s", this.getClass().getSimpleName()));
        setInitialNodes();
        assignSections();
        styleView();
        setEventHandlers();
    }

    abstract void setInitialNodes();

    private void assignSections() {
        createConcreteTableViews();
        setGridPane();
        setInfoBox();

        inputContainer = new VBox();
        inputContainer.getChildren().addAll(gridPane, infoMessageContainer);

        mainContainer = new VBox();
        mainContainer.getChildren().addAll(lblViewHeader, tableViewContainer, inputContainer); //add all containers to the parent container
    }

    abstract void createConcreteTableViews();

    protected void setTableViews() {
        createTableContainers();
        createTableColumns(tableViewRoom1);
        createTableColumns(tableViewRoom2);

        tableViewRoom1.setItems(showingsRoom1);
        vBoxRoom1.getChildren().addAll(lblRoom1, tableViewRoom1); //add tableview for room 1 to a container

        tableViewRoom2.setItems(showingsRoom2);
        vBoxRoom2.getChildren().addAll(lblRoom2, tableViewRoom2); //add tableview for room 2 to a container

        tableViewContainer.getChildren().addAll(vBoxRoom1, vBoxRoom2); //add both tableview containers to a container
    }

    protected void createTableContainers() {
        tableViewContainer = new HBox();

        vBoxRoom1 = new VBox();
        lblRoom1 = new Label("Room 1");
        tableViewRoom1 = new TableView<>();

        vBoxRoom2 = new VBox();
        lblRoom2 = new Label("Room 2");
        tableViewRoom2 = new TableView<>();
    }

    protected void createTableColumns(TableView tableView) {
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

    abstract void setGridPane();

    abstract void assignGrid();

    private void setInfoBox() {
        infoMessageContainer = new HBox();
        infoMessageContainer.setPadding(new Insets(10));
        infoMessageContainer.setSpacing(20);
        lblInfoMessage = new Label();

        infoMessageContainer.getChildren().add(lblInfoMessage);
    }

    private void styleView() {
        lblViewHeader.setStyle("-fx-text-fill: #19295e; -fx-font-size: 16");
        mainContainer.getStylesheets().add("css/style.css");
        mainContainer.setPadding(new Insets(10));
        mainContainer.setId("view");
        tableViewContainer.setId("tableViewContainer");

        if (vBoxRoom1 != null && vBoxRoom2 != null)
            styleRoomTableViews();

        inputContainer.setSpacing(10);
        gridPane.setId("insertOptions");
        infoMessageContainer.setId("infoBox");

        if (this instanceof TicketView)
            gridPane.setVisible(false);
    }

    private void styleRoomTableViews() {
        vBoxRoom1.setId("tableView");
        vBoxRoom1.setPadding(new Insets(0, 5, 10, 0));
        vBoxRoom1.setMinWidth(625);

        vBoxRoom2.setId("tableView");
        vBoxRoom2.setMinWidth(625);
        vBoxRoom2.setPadding(new Insets(0, 0, 10, 5));
    }

    abstract void setEventHandlers();

    protected void loadSectionInfo() {

    }

    abstract void clearFields();

    protected void setSelectedRoomView(TableView tableView) {
        if (tableView == tableViewRoom1)
            selectedRoomView = room1;

        else
            selectedRoomView = room2;
    }

    protected void removeSelection(TableView tableView) {
        tableView.getSelectionModel().clearSelection();
    }


}
