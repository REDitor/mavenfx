package nl.inholland.javafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage window) throws Exception {
        window.setHeight(200);
        window.setWidth(300);
        window.setTitle("Week 4 - Lesson 2 Exercise - Login Screen");

        //Basic Structure
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(10); // Vertical spacing between grid items
        gridPane.setHgap(8); // Horizontal spacing between grid items

        //Elements
        Label lblUsername = new Label("Username");
        Label lblPassword = new Label("Password");

        TextField txtUsername = new TextField();
        txtUsername.setPromptText("Enter username");

        PasswordField pfPassword = new PasswordField();
        pfPassword.setPromptText("Enter password");

        Button btnLogin = new Button("Log in");

        //Grid Assignment
        GridPane.setConstraints(lblUsername, 0, 0);
        GridPane.setConstraints(lblPassword, 0, 1);
        GridPane.setConstraints(txtUsername, 1, 0);
        GridPane.setConstraints(pfPassword, 1, 1);
        GridPane.setConstraints(btnLogin, 1, 2);
        gridPane.getChildren().addAll(lblUsername, lblPassword, txtUsername, pfPassword, btnLogin);

        //Display UI elements (the gridpane) in scene + display window
        Scene scene = new Scene(gridPane);
        window.setScene(scene);
        window.show();

        //Store info/data (not used right now)
        String username = txtUsername.getText();
        String password = pfPassword.getText();
    }
}
