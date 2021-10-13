package nl.inholland.javafx.UI;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import nl.inholland.javafx.DataAccess.Database;
import nl.inholland.javafx.Model.User.Permission;
import nl.inholland.javafx.Model.User.User;

public class MainWindow {
    Database db;
    User loggedUser;

    //region Elements
    Scene scene;

    //Menu
    MenuBar menuBar = new MenuBar();
    Menu helpMenu;
    MenuItem dashItem;
    MenuItem aboutItem;
    Menu adminMenu;
    MenuItem adminDashItem;
    MenuItem manageShowingsItem;
    MenuItem manageMoviesItem;
    Menu logoutMenu;
    MenuItem logoutDashItem;
    MenuItem logoutItem;
    //endregion

    public MainWindow(Database db, User loggedUser) {
        this.db = db;
        this.loggedUser = loggedUser;

        Stage window = new Stage();
        loadWindow(window);
    }

    //region Initiate Window
    private void loadWindow(Stage window) {
        window.setHeight(200);
        window.setWidth(260);
        window.setTitle("Fabulous Cinema -- -- Purchase Tickets -- username: " + loggedUser.getUsername());

        //initialize scene
        scene = new Scene();

        //Create elements based on user permission
        instantiateElements(loggedUser);

        //Style + grid assignment
        styleWindow();
        assignGrid();

        //Display elements, scene and window
        window.setScene(scene);
        window.show();
    }

    private void instantiateElements(User loggedUser) {
        menuBar = new MenuBar();

        helpMenu = new Menu("Help");
        dashItem = new MenuItem("-");
        aboutItem = new MenuItem("About");

        if (loggedUser.getPermission() == Permission.Admin) {
            adminMenu = new Menu("Admin");
            adminDashItem = new MenuItem("-");
            manageShowingsItem = new MenuItem("Manage Showings");
            manageMoviesItem = new MenuItem("Manage Movies");
        }

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
