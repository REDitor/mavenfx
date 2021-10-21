package nl.inholland.javafx.UI.View;

import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import nl.inholland.javafx.Data.Database;
import nl.inholland.javafx.Model.Theatre.MovieShowing;
import nl.inholland.javafx.Model.Theatre.Room;
import nl.inholland.javafx.Model.Theatre.Ticket;

import java.time.format.DateTimeFormatter;
import java.util.List;

public abstract class View {
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    Database db;
    Stage window;

    ObservableList<MovieShowing> showingsRoom1;
    ObservableList<MovieShowing> showingsRoom2;
    List<Ticket> soldTickets;
    Room selectedRoom;
    MovieShowing selectedShowing;

    //region Elements
    VBox mainContainer;
    //region mainContainer
    Label lblTitle;
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

    HBox infoMessageContainer;
    //region infoMessageContainer
    Label lblInfoMessage;
    //endregion
    //endregion
    //endregion


}
