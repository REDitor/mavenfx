package nl.inholland.javafx.UI;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import nl.inholland.javafx.DataAccess.Database;
import nl.inholland.javafx.Models.Users.User;

public class MainWindow {
    Database db;
    User loggedUser;

    //region Elements
    Scene scene;

    //Menu
    MenuBar menuBar = new MenuBar();

    Menu helpMenu = new Menu("Help");
    MenuItem dashItem = new MenuItem("-");
    MenuItem aboutItem = new MenuItem("About");

    Menu adminMenu = new Menu("Admin");
    MenuItem adminDashItem = new MenuItem("-");
    MenuItem manageShowingsItem = new MenuItem("Manage Showings");
    MenuItem manageMoviesItem = new MenuItem("Manage Movies");

    Menu logoutMenu = new Menu("Logout");
    MenuItem logoutDashItem = new MenuItem("-");
    MenuItem logoutItem = new MenuItem("Logout...");
    //endregion

    public MainWindow(Database db, User loggedUser) {
        this.db = db;
        this.loggedUser = loggedUser;

        Stage window = new Stage();
        loadWindow(window);
    }

    private void loadWindow(Stage window) {
        window.setHeight(200);
        window.setWidth(260);
        window.setTitle("Fabulous Cinema -- -- Purchase Tickets -- username: " + loggedUser.getUsername());

        //initialize scene
        scene = new Scene();

        //Style + grid assignment
        styleWindow();
        assignGrid();

        //Display elements, scene and window
        window.setScene(scene);
        window.show();
    }

    private void styleWindow() {

    }

    private void assignGrid() {

    }
}
