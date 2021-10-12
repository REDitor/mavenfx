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
    //region Elements
    Scene scene;
    GridPane gridPane = new GridPane();

    Label lblUsername = new Label("Username");
    TextField txtUsername = new TextField();
    Label lblPassword = new Label("Password");
    PasswordField pwfPassword = new PasswordField();
    Button btnLogin = new Button("Log in");
    Label lblErrorMessage = new Label("Wrong credentials, please try again...");
    //endregion

    //region Initiate Window
    private void loadWindow(Stage window) {
        //Basic window shape
        window.setHeight(200);
        window.setWidth(260);
        window.setTitle("Fabulous Cinema -- Login");

        //gridpane spacing
        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        //Style + grid assignment
        styleWindow();
        assignGrid();

        //Display elements, scene and window
        scene = new Scene(gridPane);
        window.setScene(scene);
        window.show();
    }

    private void styleWindow() {
        //basic startup styling
        txtUsername.setPromptText("Enter username");
        pwfPassword.setPromptText("Enter password");
        lblErrorMessage.setVisible(false);
        btnLogin.setVisible(false);

        //css
        scene.getStylesheets().add("css/style.css");

    }

    private void assignGrid() {
        GridPane.setConstraints(lblUsername, 0, 0);
        GridPane.setConstraints(txtUsername, 1, 0);
        GridPane.setConstraints(lblPassword, 0, 1);
        GridPane.setConstraints(pwfPassword, 1, 1);
        GridPane.setConstraints(btnLogin, 1, 2);
        GridPane.setConstraints(lblErrorMessage, 0, 4, 3, 1);

        gridPane.getChildren().addAll(lblUsername, txtUsername, lblPassword,
                                        pwfPassword, btnLogin, lblErrorMessage);
    }
    //endregion

    @Override
    public void start(Stage window) throws Exception {
        loadWindow(window);

    }
}
