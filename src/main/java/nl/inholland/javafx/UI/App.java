package nl.inholland.javafx.UI;

import javafx.application.Application;
import javafx.stage.Stage;
import nl.inholland.javafx.Data.Database;

public class App extends Application {
    @Override
    public void start(Stage window) {
        Database db = new Database();
        LoginWindow loginWindow = new LoginWindow(db);

        window.close();
    }
}
