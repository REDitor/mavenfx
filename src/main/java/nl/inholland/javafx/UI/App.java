package nl.inholland.javafx.UI;

import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import nl.inholland.javafx.DataAccess.Database;
import nl.inholland.javafx.Model.User.User;

import java.util.List;
import java.util.Objects;

public class App extends Application {
    Database db;

    @Override
    public void start(Stage window) {
        Database db = new Database();
        LoginWindow loginWindow = new LoginWindow(db);
        window.close();
    }
}
