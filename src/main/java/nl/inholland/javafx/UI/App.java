package nl.inholland.javafx.UI;

import javafx.application.Application;
import javafx.stage.Stage;
import nl.inholland.javafx.DataAccess.Database;

public class App extends Application {
    Database db;

    @Override
    public void start(Stage window) {
        Database db = new Database();
        LoginWindow loginWindow = new LoginWindow(db);
        window.close();
    }
}
