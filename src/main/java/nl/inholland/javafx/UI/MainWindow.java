package nl.inholland.javafx.UI;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nl.inholland.javafx.DataAccess.Database;
import nl.inholland.javafx.Models.Users.User;

public class MainWindow {
    Database db;
    User loggedUser;

    Scene scene;

    public MainWindow(Database db, User loggedUser) {
        this.db = db;
        this.loggedUser = loggedUser;

        loadWindow();
    }

    private void loadWindow(Stage window) {
        window.setHeight(200);
        window.setWidth(260);
        window.setTitle("Fabulous Cinema -- Home");

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
