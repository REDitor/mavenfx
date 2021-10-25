package nl.inholland.javafx.UI;

import javafx.application.Application;
import javafx.stage.Stage;
import nl.inholland.javafx.Data.Database;
import nl.inholland.javafx.Model.User.Admin;
import nl.inholland.javafx.Model.User.User;

public class App extends Application {
    @Override
    public void start(Stage window) {
        Database db = new Database();
//        LoginWindow loginWindow = new LoginWindow(db);

        User user = new Admin("sdf", "sdgafaf");
        MainWindow mainWindow = new MainWindow(db, user);
        window.close();
    }
}
