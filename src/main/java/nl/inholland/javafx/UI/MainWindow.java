package nl.inholland.javafx.UI;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import nl.inholland.javafx.DataAccess.Database;
import nl.inholland.javafx.Model.User.Permission;
import nl.inholland.javafx.Model.User.User;
import nl.inholland.javafx.UI.View.TicketView;

public class MainWindow {
    Database db;
    User loggedUser;

    //region Views
    TicketView ticketView;
    //endregion

    //region Elements
    Scene scene;

    //Menu
    MenuBar menuBar = new MenuBar();
    Menu adminMenu;
    MenuItem adminDashItem;
    MenuItem manageShowingsItem;
    MenuItem manageMoviesItem;
    Menu sellTicketsMenu;
    Menu helpMenu;
    MenuItem dashItem;
    MenuItem aboutItem;
    Menu logoutMenu;
    MenuItem logoutDashItem;
    MenuItem logoutItem;
    //endregion

    public MainWindow(Database db, User loggedUser) {
        this.db = db;
        this.loggedUser = loggedUser;

        ticketView = new TicketView(db, loggedUser);

        Stage window = new Stage();
        loadWindow(window);
    }


    //region Initiate Window
    private void loadWindow(Stage window) {
        window.setHeight(200);
        window.setWidth(260);
        window.setTitle("Fabulous Cinema -- -- Purchase Tickets -- username: " + loggedUser.getUsername());

        //initialize scene
        HBox hBox = ticketView.getView();

        scene = new Scene(hBox);

        //Create elements based on user permission
        instantiateMenu(loggedUser);

        //Style + grid assignment
        styleWindow();
        assignGrid();

        //Display elements, scene and window
        window.setScene(scene);
        window.show();
    }

    private void instantiateMenu(User loggedUser) {
        menuBar = new MenuBar();

        if (loggedUser.getPermission() == Permission.Admin) {
            adminMenu = new Menu("Admin");
            adminDashItem = new MenuItem("-");
            manageShowingsItem = new MenuItem("Manage Showings");
            manageMoviesItem = new MenuItem("Manage Movies");
        }

        sellTicketsMenu = new Menu("Sell Tickets");

        helpMenu = new Menu("Help");
        dashItem = new MenuItem("-");
        aboutItem = new MenuItem("About");

        logoutMenu = new Menu("Logout");
        logoutDashItem = new MenuItem("-");
        logoutItem = new MenuItem("Logout...");
    }

    private void styleWindow() {

    }

    private void assignGrid() {

    }
    //endregion

}
