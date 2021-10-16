package nl.inholland.javafx.UI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import nl.inholland.javafx.DataAccess.Database;
import nl.inholland.javafx.Model.User.Permission;
import nl.inholland.javafx.Model.User.User;
import nl.inholland.javafx.UI.View.ManageMoviesView;
import nl.inholland.javafx.UI.View.ManageShowingsView;
import nl.inholland.javafx.UI.View.TicketView;

public class MainWindow {
    Database db;
    User loggedUser;

    //region Views
    TicketView ticketView;
    ManageMoviesView manageMoviesView;
    ManageShowingsView manageShowingsView;
    //endregion

    //region Elements
    Scene ticketScene, manageShowingsScene, manageMoviesScene;
    //Menu
    MenuBar menuBar;
    Menu adminMenu, sellTicketsMenu, helpMenu, logoutMenu;
    MenuItem manageShowingsItem, manageMoviesItem, aboutItem, logoutItem;
    //endregion

    public MainWindow(Database db, User loggedUser) {
        this.db = db;
        this.loggedUser = loggedUser;

        ticketView = new TicketView(db);
        manageMoviesView = new ManageMoviesView(db);
        manageShowingsView = new ManageShowingsView(db);

        Stage window = new Stage();
        loadWindow(window);
    }


    //region Initiate Window
    private void loadWindow(Stage window) {
        window.setHeight(200);
        window.setWidth(260);
        window.setTitle("Fabulous Cinema -- -- Purchase Tickets -- username: " + loggedUser.getUsername());

        //Create elements based on user permission
        instantiateMenu(loggedUser);

        //initialize ticketView as main page/scene
        HBox hBox = ticketView.getView();
        ticketScene = new Scene(hBox);

        setEventHandlers(window);
        //Style + grid assignment
//        styleWindow();
//        assignGrid();

        //Display elements, scene and window
        window.setScene(ticketScene);
        window.show();
    }

    private void instantiateMenu(User loggedUser) {
        menuBar = new MenuBar();

        if (loggedUser.getPermission() == Permission.Admin) {
            adminMenu = new Menu("Admin");
            manageShowingsItem = new MenuItem("Manage Showings");
            manageMoviesItem = new MenuItem("Manage Movies");
        }

        sellTicketsMenu = new Menu("Sell Tickets");

        helpMenu = new Menu("Help");
        aboutItem = new MenuItem("About");

        logoutMenu = new Menu("Logout");
        logoutItem = new MenuItem("Logout...");
    }

    private void setEventHandlers(Stage window) {
        manageShowingsItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                HBox hbox = manageShowingsView.getView();
                manageShowingsScene = new Scene(hbox); //TODO: creates it every time --> CHANGE THIS
            }
        });

        manageMoviesItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                HBox hbox = manageMoviesView.getView();
                manageMoviesScene = new Scene(hbox);
            }
        });

        sellTicketsMenu.setOnAction(e -> window.setScene(ticketScene));
    }

    private void styleWindow() {

    }

    private void assignGrid() {

    }
    //endregion

}
