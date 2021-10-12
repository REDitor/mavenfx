package nl.inholland.javafx.UI;

import nl.inholland.javafx.DataAccess.Database;
import nl.inholland.javafx.Models.Users.User;

public class MainWindow {
    Database db;
    User loggedUser;

    public MainWindow(Database db, User loggedUser) {
        this.db = db;
        this.loggedUser = loggedUser;
    }


}
