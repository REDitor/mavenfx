package nl.inholland.javafx.UI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;
import nl.inholland.javafx.Data.Database;
import nl.inholland.javafx.Model.Theatre.MovieShowing;
import nl.inholland.javafx.Model.User.Permission;
import nl.inholland.javafx.Model.User.User;
import nl.inholland.javafx.UI.View.TicketView;
import nl.inholland.javafx.UI.View.View;

public class MainWindow {
    Database db;
    User loggedUser;

    //region Elements
    //Container
    VBox vBoxMainContainer;

    //region Views
    View view;
    VBox vBoxView;

    Label lblViewHeader;
    HBox hBoxTableViews;

    VBox vboxTableViewRoom1;
    Label lblRoom1Header;

    VBox vboxTableViewRoom2;
    Label lblRoom2Header;

    TableView<MovieShowing> roomTableView;
    TableColumn<MovieShowing, String> colStartTime;
    TableColumn<MovieShowing, String> colEndTime;
    TableColumn<MovieShowing, String> colTitle;
    TableColumn<MovieShowing, String> colSeats;
    TableColumn<MovieShowing, String> colPrice;

    GridPane gridPaneInfo;

    HBox hBoxErrorMessage;
    Label lblErrorMessage;
    //endregion

    //Scene
    Scene scene;

    //Menu
    MenuBar menuBar;
    Menu adminMenu, sellTicketsMenu, helpMenu, logoutMenu;
    MenuItem manageShowingsItem, manageMoviesItem, aboutItem, logoutItem;
    //endregion

    public MainWindow(Database db, User loggedUser) {
        this.db = db;
        this.loggedUser = loggedUser;

        Stage window = new Stage();
        loadWindow(window);
    }

    //region Initiate Window
    private void loadWindow(Stage window) {
        window.setHeight(650);
        window.setWidth(1280);
        window.setTitle("Fabulous Cinema -- -- Purchase Tickets -- username: " + loggedUser.getUsername());

        //Create elements based on user permission
        instantiateMenu(loggedUser);

        //Add MenuBar
        vBoxMainContainer = new VBox();
        vBoxMainContainer.getChildren().add(menuBar);

        //initialize main page/ticket view
        view = new TicketView(db);


        scene = new Scene(vBoxMainContainer);
        styleWindow();

        setEventHandlers(window);

        //Display elements, scene and window
        window.setScene(scene);
        window.show();
    }

    private void instantiateMenu(User loggedUser) {
        menuBar = new MenuBar();

        if (loggedUser.getPermission() == Permission.Admin) {
            adminMenu = new Menu("Admin");
            manageShowingsItem = new MenuItem("Manage Showings");
            manageMoviesItem = new MenuItem("Manage Movies");

            adminMenu.getItems().addAll(manageShowingsItem, manageMoviesItem);
            menuBar.getMenus().add(adminMenu);
        }

        sellTicketsMenu = new Menu("Sell Tickets");

        helpMenu = new Menu("Help");
        aboutItem = new MenuItem("About");

        logoutMenu = new Menu("Logout");
        logoutItem = new MenuItem("Logout...");

        helpMenu.getItems().add(aboutItem);
        logoutMenu.getItems().add(logoutItem);
        menuBar.getMenus().addAll(sellTicketsMenu, helpMenu, logoutMenu);
    }

    private void fillTicketView() {
        emptyView();

        Label lblRoom;
        Label lblRoomResult;
        Label lblMovieTitle;
        Label lblMovieTitleResult;
        Label lblStartTime;
        Label lblStartTimeResult;
        Label lblNrOfSeats;
        Label lblNrOfSeatsResult;
        ChoiceBox<Integer> choiceBoxNrOfSeats;
        Button btnPurchase;
        Label lblEndTime;
        Label lblEndTimeResult;
        Label lblCustomerName;
        TextField txtCustomerName;
        Button btnClear;
    }

    private void fillManageShowingsView() {
        emptyView();
    }

    private void fillManageMoviesView() {
        emptyView();
    }

    private void emptyView() {
        vBoxView.getChildren().removeAll();
    }

    private void addToContainer(Node node) {
        if (!vBoxMainContainer.getChildren().contains(node)) {
            vBoxMainContainer.getChildren().add(node);
        }
    }

    private void resetView() {
        vBoxMainContainer.getChildren().removeAll();
        vBoxMainContainer.getChildren().add(menuBar);
    }

    private void setEventHandlers(Stage window) {
        if (manageShowingsItem != null) {
            manageShowingsItem.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    resetView();
                    vbxManageShowingsView = manageShowingsView.getView();
                    addToContainer(vbxManageShowingsView);
                    vbxManageMoviesView.setVisible(true);
                }
            });
        }

        if (manageShowingsItem != null) {
            manageMoviesItem.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    resetView();
                    vbxManageMoviesView = manageMoviesView.getView();
                    addToContainer(vbxManageMoviesView);
                }
            });
        }

        sellTicketsMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                resetView();
                vbxTicketView = ticketView.getView();
                addToContainer(vbxTicketView);
            }
        });
    }

    private void styleWindow() {
        JMetro jMetro = new JMetro(Style.DARK);
        jMetro.setScene(scene);
        scene.getStylesheets().add("css/style.css");
        menuBar.setId("menu");
    }
    //endregion

}
