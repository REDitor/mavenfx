package nl.inholland.javafx.UI.View;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import nl.inholland.javafx.Data.Database;
import nl.inholland.javafx.Model.Theatre.Movie;
import nl.inholland.javafx.Model.Theatre.MovieShowing;
import nl.inholland.javafx.Model.Theatre.Room;
import nl.inholland.javafx.Model.User.User;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

public abstract class View {
    protected final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    protected final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    protected final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    protected final DecimalFormat currencyFormat = new DecimalFormat("€ 0.00");

    Database db;
    Stage window;
    User user;

    Room room1;
    Room room2;
    ObservableList<MovieShowing> showingsRoom1;
    ObservableList<MovieShowing> showingsRoom2;
    ObservableList<Movie> listedMovies;
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

    public View(Database db, Stage window, User user) {
        this.db = db;
        this.window = window;
        this.user = user;

        room1 = db.getRoom1();
        room2 = db.getRoom2();

        loadWindow(window);
    }

    public VBox getView() {
        return mainContainer;
    }

    private void loadWindow(Stage window) {
        setInitialNodes();
        instantiateGridPaneElements();
        setGridPane();
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

        refreshTableViews();
        vBoxRoom1.getChildren().addAll(lblRoom1, tableViewRoom1); //add tableview for room 1 to a container
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

    private void createTableColumns(TableView<MovieShowing> tableView) {
        TableColumn<MovieShowing, String> colStartTime = new TableColumn<>("Start");
        colStartTime.setCellValueFactory(cellData ->
                new SimpleStringProperty(dateTimeFormatter.format(cellData.getValue().getStartTime())));

        TableColumn<MovieShowing, String> colEndTime = new TableColumn<>("End");
        colEndTime.setCellValueFactory(cellData ->
                new SimpleStringProperty(dateTimeFormatter.format(cellData.getValue().getEndTime())));

        TableColumn<MovieShowing, String> colTitle = new TableColumn<>("Title");
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<MovieShowing, String> colSeats = new TableColumn<>("Seats");
        colSeats.setCellValueFactory(new PropertyValueFactory<>("numberOfSeats"));

        TableColumn<MovieShowing, String> colPrice = new TableColumn<>("Price");
        colPrice.setCellValueFactory(cellData ->
                new SimpleStringProperty(currencyFormat.format(cellData.getValue().getPrice())));

        tableView.getColumns().addAll(colStartTime, colEndTime, colTitle, colSeats, colPrice);
    }

    protected void setGridPane() {
        gridPane = new GridPane();

        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(20);
        gridPane.setHgap(40);

        assignGrid();
    }

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
        lblRoom1.setStyle("-fx-text-fill: #19295e; -fx-font-size: 12");
        vBoxRoom1.setPadding(new Insets(10, 5, 10, 0));
        vBoxRoom1.setMinWidth(625);

        vBoxRoom2.setId("tableView");
        lblRoom2.setStyle("-fx-text-fill: #19295e; -fx-font-size: 12");
        vBoxRoom2.setPadding(new Insets(10, 0, 10, 5));
        vBoxRoom2.setMinWidth(625);
    }

    abstract void setEventHandlers();

    abstract void clearFields();

    abstract void instantiateGridPaneElements();

    protected void setSelectedRoomView(TableView tableView) {
        if (tableView == tableViewRoom1)
            selectedRoomView = room1;

        else
            selectedRoomView = room2;
    }

    protected void deselect(TableView tableView) {
        tableView.getSelectionModel().clearSelection();
    }

    public void refreshTableViews() {
        showingsRoom1 = FXCollections.observableArrayList(room1.getShowings());
        showingsRoom2 = FXCollections.observableArrayList(room2.getShowings());

        tableViewRoom1.setItems(showingsRoom1);
        tableViewRoom2.setItems(showingsRoom2);
    }

    public void refreshMovies() {
        listedMovies = FXCollections.observableArrayList(db.getMovies());
    }
}
