package nl.inholland.javafx.UI.View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import nl.inholland.javafx.Exception.NoNameException;
import nl.inholland.javafx.Exception.NoShowingSelectedException;
import nl.inholland.javafx.Exception.SoldOutException;
import nl.inholland.javafx.Exception.ZeroTicketsException;
import nl.inholland.javafx.Model.Theatre.MovieShowing;
import nl.inholland.javafx.Model.Theatre.Room;
import nl.inholland.javafx.Model.Theatre.Ticket;

import java.time.LocalDateTime;
import java.util.List;

// import static javax.swing.JOptionPane.YES_NO_OPTION;
// import static javax.swing.JOptionPane.showConfirmDialog;
//
// public class TicketView extends View {
//     List<Ticket> soldTickets;
//
//     //region TicketView Elements
//     Label lblRoom;
//     Label lblRoomResult;
//     Label lblMovieTitle;
//     Label lblMovieTitleResult;
//     Label lblStartTime;
//     Label lblStartTimeResult;
//     Label lblNrOfSeats;
//     ChoiceBox<Integer> choiceBoxNrOfSeatsResult;
//     Button btnPurchase;
//     Label lblEndTime;
//     Label lblEndTimeResult;
//     Label lblName;
//     TextField txtNameResult;
// }
//     //endregion
//
//
//     public TicketView() {
//
//         setInitialNodes();
//
//         soldTickets = new ArrayList<>();
//
//         assignSections();
//         styleView();
//         setEventHandlers();
//     }
//
//     @Override
//     public void setInitialNodes() {
//         lblTitle = new Label("Purchase Tickets");
//     }
//
//     @Override
//     public VBox getView() {
//         return mainContainer;
//     }
//
//     @Override
//     public void setEventHandlers() {
//         tableViewRoom1.setOnMouseClicked(e -> {
//             setSelectedRoom(tableViewRoom1);
//             if (tableViewRoom1.getSelectionModel().getSelectedItem() != null) {
//                 removeSelection(tableViewRoom2);
//                 selectedShowing = tableViewRoom1.getSelectionModel().getSelectedItem();
//                 loadSelectionInfo(selectedShowing, room1);
//             }
//         });
//
//         tableViewRoom1.setColumnResizePolicy(new Callback<TableView.ResizeFeatures, Boolean>() {
//             @Override
//             public Boolean call(TableView.ResizeFeatures resizeFeatures) {
//                 return true;
//             }
//         });
//
//         tableViewRoom2.setOnMouseClicked(e -> {
//             setSelectedRoom(tableViewRoom2);
//             if (tableViewRoom2.getSelectionModel().getSelectedItem() != null) {
//                 removeSelection(tableViewRoom1);
//                 selectedShowing = tableViewRoom2.getSelectionModel().getSelectedItem();
//                 loadSelectionInfo(selectedShowing, room2);
//             }
//         });
//
//         btnPurchase.setOnAction(new EventHandler<ActionEvent>() {
//             @Override
//             public void handle(ActionEvent actionEvent) {
//                 try {
//                     int numberOfTickets = choiceBoxNrOfSeatsResult.getSelectionModel().getSelectedItem();
//                     if (selectedShowing == null) {
//                         throw new NoShowingSelectedException();
//                     }
//                     if (showingsRoom1.contains(selectedShowing) || showingsRoom2.contains(selectedShowing)) {
//                         confirmPurchase(selectedShowing, numberOfTickets, txtNameResult.getText());
//                         window.sizeToScene();
//                     }
//                 } catch (NoShowingSelectedException | SoldOutException | ZeroTicketsException | NoNameException rte) {
//                     lblInfoMessage.setStyle("-fx-text-fill: red");
//                     lblInfoMessage.setText(rte.getMessage());
//                 }
//             }
//         });
//
//         btnClear.setOnAction(new EventHandler<ActionEvent>() {
//             @Override
//             public void handle(ActionEvent actionEvent) {
//                 clearFields();
//                 removeSelection(tableViewRoom1);
//                 removeSelection(tableViewRoom2);
//                 gridPane.setVisible(false);
//                 window.sizeToScene();
//             }
//         });
//     }
//
//     public void assignSections() {
//         setTableViews();
//         setGridPane();
//         setInfoBox();
//
//         inputContainer = new VBox();
//         inputContainer.getChildren().addAll(gridPane, infoMessageContainer);
//
//         mainContainer = new VBox();
//         mainContainer.getChildren().addAll(lblTitle, tableViewContainer, inputContainer); //add all containers to the parent container
//     }
//
//     private void setSelectedRoom(TableView tableView) {
//         if (tableView == tableViewRoom1) {
//             selectedRoom = room1;
//         } else {
//             selectedRoom = room2;
//         }
//     }
//
//     private void removeSelection(TableView tableView) {
//         tableView.getSelectionModel().clearSelection();
//     }
//
//     private void clearFields() {
//         lblRoomResult.setText(null);
//         lblMovieTitleResult.setText(null);
//         lblStartTimeResult.setText(null);
//         choiceBoxNrOfSeatsResult.setValue(0);
//         lblEndTimeResult.setText(null);
//         txtNameResult.clear();
//         lblInfoMessage.setText(null);
//     }

    // private void createTableContainers() {
    //     tableViewContainer = new HBox();
    //
    //     vBoxRoom1 = new VBox();
    //     lblRoom1 = new Label("Room 1");
    //     tableViewRoom1 = new TableView<>();
    //
    //     vBoxRoom2 = new VBox();
    //     lblRoom2 = new Label("Room 2");
    //     tableViewRoom2 = new TableView<>();
    // }

    // private void setTableViews() {
    //     createTableContainers();
    //     createTableColumns(tableViewRoom1);
    //     createTableColumns(tableViewRoom2);
    //
    //     tableViewRoom1.setItems(showingsRoom1);
    //     vBoxRoom1.getChildren().addAll(lblRoom1, tableViewRoom1); //add tableview for room 1 to a container
    //
    //     tableViewRoom2.setItems(showingsRoom2);
    //     vBoxRoom2.getChildren().addAll(lblRoom2, tableViewRoom2); //add tableview for room 2 to a container
    //
    //     tableViewContainer.getChildren().addAll(vBoxRoom1, vBoxRoom2); //add both tableview containers to a container
    // }
//
//     @Override
//     void createTableColumns(TableView<MovieShowing> tableView) {
//         TableColumn<MovieShowing, LocalDateTime> colStartTime;
//         TableColumn<MovieShowing, LocalDateTime> colEndTime;
//         TableColumn<MovieShowing, String> colTitle;
//         TableColumn<MovieShowing, Integer> colSeats;
//         TableColumn<MovieShowing, Double> colPrice;
//
//         colStartTime = new TableColumn<>("Start");
//         colStartTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));
//
//         colEndTime = new TableColumn<>("End");
//         colEndTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));
//
//         colTitle = new TableColumn<>("Title");
//         colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
//
//         colSeats = new TableColumn<>("Seats");
//         colSeats.setCellValueFactory(new PropertyValueFactory<>("numberOfSeats"));
//
//         colPrice = new TableColumn<>("Price");
//         colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
//
//         tableView.getColumns().addAll(colStartTime, colEndTime, colTitle, colSeats, colPrice);
//     }
//
//     private void loadSelectionInfo(MovieShowing showing, Room room) {
//         gridPane.setVisible(true);
//
//         lblRoomResult.setText(String.format("Room %d", room.getRoomNumber()));
//         lblMovieTitleResult.setText(showing.getTitle());
//         lblStartTimeResult.setText(showing.getStartTime().toString());
//         lblEndTimeResult.setText(showing.getEndTime().toString());
//     }
//
//     private void setGridPane() {
//         gridPane = new GridPane();
//
//         gridPane.setPadding(new Insets(10));
//         gridPane.setVgap(20);
//         gridPane.setHgap(40);
//
//         lblRoom = new Label("Room:");
//         lblRoomResult = new Label();
//         lblMovieTitle = new Label("Movie Title:");
//         lblMovieTitleResult = new Label();
//         lblStartTime = new Label("Start:");
//         lblStartTimeResult = new Label();
//         lblNrOfSeats = new Label("No. of seats:");
//
//         choiceBoxNrOfSeatsResult = new ChoiceBox<>();
//         choiceBoxNrOfSeatsResult.getItems().addAll(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
//         choiceBoxNrOfSeatsResult.setValue(0);
//
//         btnPurchase = new Button("Purchase");
//         lblEndTime = new Label("End:");
//         lblEndTimeResult = new Label();
//         lblName = new Label("Name:");
//         txtNameResult = new TextField();
//         btnClear = new Button("Clear");
//
//         assignGrid();
//     }
//
//     private void assignGrid() {
//         GridPane.setConstraints(lblRoom, 0, 0);
//         GridPane.setConstraints(lblRoomResult, 1, 0);
//         GridPane.setConstraints(lblMovieTitle, 2, 0);
//         GridPane.setConstraints(lblMovieTitleResult, 3, 0, 3, 1);
//         GridPane.setConstraints(lblStartTime, 0, 1);
//         GridPane.setConstraints(lblStartTimeResult, 1, 1);
//         GridPane.setConstraints(lblNrOfSeats, 2, 1);
//         GridPane.setConstraints(choiceBoxNrOfSeatsResult, 3, 1);
//         GridPane.setConstraints(btnPurchase, 5, 1);
//         GridPane.setConstraints(lblEndTime, 0, 2);
//         GridPane.setConstraints(lblEndTimeResult, 1, 2);
//         GridPane.setConstraints(lblName, 2, 2);
//         GridPane.setConstraints(txtNameResult, 3, 2, 2, 1);
//         GridPane.setConstraints(btnClear, 5, 2);
//
//         gridPane.getChildren().addAll(
//                 lblRoom, lblRoomResult, lblMovieTitle, lblMovieTitleResult, lblStartTime, lblStartTimeResult,
//                 lblNrOfSeats, choiceBoxNrOfSeatsResult, btnPurchase, lblEndTime, lblEndTimeResult, lblName, txtNameResult,
//                 btnClear
//         );
//     }
//
//     private void setInfoBox() {
//         infoMessageContainer = new HBox();
//         infoMessageContainer.setPadding(new Insets(10));
//         infoMessageContainer.setSpacing(20);
//         lblInfoMessage = new Label();
//
//         infoMessageContainer.getChildren().add(lblInfoMessage);
//     }
//
//     private void confirmPurchase(MovieShowing showing, int numberOfTickets, String name) {
//         if (!ticketsLeft(numberOfTickets, showing))
//             throw new SoldOutException();
//
//         else {
//             if (numberOfTickets < 1)
//                 throw new ZeroTicketsException();
//
//             if (name.length() < 1)
//                 throw new NoNameException();
//
//             int result = showConfirmDialog(
//                     null,
//                     String.format("Are you sure you want to sell %d tickets%nfor Movie '%s'%nto %s", numberOfTickets, selectedShowing.getTitle(), name),
//                     "Confirm Transaction",
//                     YES_NO_OPTION
//             );
//
//             if (result == YES_NO_OPTION)
//                 sellTickets(showing, numberOfTickets, name);
//
//             else
//                 lblInfoMessage.setText("[Purchase Canceled]");
//         }
//     }
//
//     private void sellTickets(MovieShowing showing, int numberOfTickets, String name) {
//         showing.deductAvailableTickets(numberOfTickets); //decrement available tickets for showing
//         Ticket ticket = null;
//         for (int i = 0; i < numberOfTickets; i++) {
//             ticket = new Ticket(selectedRoom.getRoomNumber(), selectedShowing.getStartTime(),
//                     selectedShowing.getEndTime(), selectedShowing.getTitle(), name);
//             soldTickets.add(ticket);
//         }
//         lblInfoMessage.setStyle("-fx-text-fill: #fff");
//         lblInfoMessage.setText(String.format(
//                 "Successfully sold tickets to %s:%nAmount: %d%nMovie: %s%nRoom: Room %d",
//                 txtNameResult.getText(), numberOfTickets, ticket.getTitle(), ticket.getRoomNumber()));
//     }
//
//     private boolean ticketsLeft(int numberOfTickets, MovieShowing showing) {
//         return numberOfTickets <= showing.getAvailableTickets(); //check if tickets are available
//     }
//     //endregion
// }
