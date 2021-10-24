package nl.inholland.javafx.UI.View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import nl.inholland.javafx.Data.Database;
import nl.inholland.javafx.Exception.NoNameException;
import nl.inholland.javafx.Exception.NoShowingSelectedException;
import nl.inholland.javafx.Exception.SoldOutException;
import nl.inholland.javafx.Exception.ZeroTicketsException;
import nl.inholland.javafx.Model.Theatre.MovieShowing;
import nl.inholland.javafx.Model.Theatre.Room;
import nl.inholland.javafx.Model.Theatre.Ticket;
import nl.inholland.javafx.Model.User.User;

import java.util.ArrayList;
import java.util.List;

import static javax.swing.JOptionPane.YES_NO_OPTION;
import static javax.swing.JOptionPane.showConfirmDialog;

public class TicketView extends View {
    List<Ticket> soldTickets;

    //region TicketView Elements
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
    //endregion

    public TicketView(Database db, Stage window, User user) {
        super(db, window, user);
        window.setTitle(String.format("Fabulous Cinema -- Sell Tickets -- username: %s (%s)",
                user.getUsername(), user.getPermission()));
        soldTickets = new ArrayList<>();
    }

    @Override
    public void setInitialNodes() {
        lblViewHeader = new Label("Purchase Tickets");
        instantiateGridPaneElements();
    }

    @Override
    void createConcreteTableViews() {
        super.setTableViews();
    }

    @Override
    public void setEventHandlers() {
        tableViewRoom1.setOnMouseClicked(e -> {
            setSelectedRoomView(tableViewRoom1);
            if (tableViewRoom1.getSelectionModel().getSelectedItem() != null) {
                deselect(tableViewRoom2);
                selectedShowing = tableViewRoom1.getSelectionModel().getSelectedItem();
                loadSelectionInfo(selectedShowing, room1);
            }
        });

        tableViewRoom2.setOnMouseClicked(e -> {
            setSelectedRoomView(tableViewRoom2);
            if (tableViewRoom2.getSelectionModel().getSelectedItem() != null) {
                deselect(tableViewRoom1);
                selectedShowing = tableViewRoom2.getSelectionModel().getSelectedItem();
                loadSelectionInfo(selectedShowing, room2);
            }
        });

        btnPurchase.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    int numberOfTickets = choiceBoxNrOfSeatsResult.getSelectionModel().getSelectedItem();
                    if (selectedShowing == null) {
                        throw new NoShowingSelectedException();
                    }
                    if (showingsRoom1.contains(selectedShowing) || showingsRoom2.contains(selectedShowing)) {
                        confirmPurchase(selectedShowing, numberOfTickets, txtNameResult.getText());
                        window.sizeToScene();
                    }
                } catch (NoShowingSelectedException | SoldOutException | ZeroTicketsException | NoNameException rte) {
                    lblInfoMessage.setStyle("-fx-text-fill: red");
                    lblInfoMessage.setText(rte.getMessage());
                }
            }
        });

        btnClear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                clearFields();
                deselect(tableViewRoom1);
                deselect(tableViewRoom2);
                gridPane.setVisible(false);
                window.sizeToScene();
            }
        });
    }

    @Override
    void clearFields() {
        lblRoomResult.setText(null);
        lblMovieTitleResult.setText(null);
        lblStartTimeResult.setText(null);
        choiceBoxNrOfSeatsResult.setValue(0);
        lblEndTimeResult.setText(null);
        txtNameResult.clear();
        lblInfoMessage.setText(null);
    }

    @Override
    void instantiateGridPaneElements() {
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
    }

    private void loadSelectionInfo(MovieShowing showing, Room room) {
        gridPane.setVisible(true);

        lblRoomResult.setText(String.format("Room %d", room.getRoomNumber()));
        lblMovieTitleResult.setText(showing.getTitle());
        lblStartTimeResult.setText(showing.getStartTime().toString());
        lblEndTimeResult.setText(showing.getEndTime().toString());
    }

    @Override
    void assignGrid() {
        GridPane.setConstraints(lblRoom, 0, 0);
        GridPane.setConstraints(lblRoomResult, 1, 0);
        GridPane.setConstraints(lblMovieTitle, 2, 0);
        GridPane.setConstraints(lblMovieTitleResult, 3, 0, 3, 1);
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

    private void confirmPurchase(MovieShowing showing, int numberOfTickets, String name) {
        if (!ticketsLeft(numberOfTickets, showing))
            throw new SoldOutException();

        else {
            if (numberOfTickets < 1)
                throw new ZeroTicketsException();

            if (name.length() < 1)
                throw new NoNameException();

            int result = showConfirmDialog(
                    null,
                    String.format("Are you sure you want to sell %d tickets%nfor Movie '%s'%nto %s", numberOfTickets, selectedShowing.getTitle(), name),
                    "Confirm Transaction",
                    YES_NO_OPTION
            );

            if (result == YES_NO_OPTION)
                sellTickets(showing, numberOfTickets, name);

            else
                lblInfoMessage.setText("[Purchase Canceled]");
        }
    }

    private void sellTickets(MovieShowing showing, int numberOfTickets, String name) {
        showing.deductAvailableTickets(numberOfTickets); //decrement available tickets for showing
        Ticket ticket = null;
        for (int i = 0; i < numberOfTickets; i++) {
            ticket = new Ticket(selectedRoomView.getRoomNumber(), selectedShowing.getStartTime(),
                    selectedShowing.getEndTime(), selectedShowing.getTitle(), name);
            soldTickets.add(ticket);
        }
        lblInfoMessage.setStyle("-fx-text-fill: #fff");
        lblInfoMessage.setText(String.format(
                "Successfully sold tickets to %s:%nAmount: %d%nMovie: %s%nRoom: Room %d",
                txtNameResult.getText(), numberOfTickets, ticket.getTitle(), ticket.getRoomNumber()));
    }

    private boolean ticketsLeft(int numberOfTickets, MovieShowing showing) {
        return numberOfTickets <= showing.getAvailableTickets(); //check if tickets are available
    }
    //endregion
}
