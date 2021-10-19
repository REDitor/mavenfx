package nl.inholland.javafx.UI;

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
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;
import nl.inholland.javafx.Data.Database;
import nl.inholland.javafx.Model.User.User;

import java.util.List;
import java.util.Objects;

public class LoginWindow {
    private Database db;

    //region Elements
    Scene scene;
    GridPane gridPane = new GridPane();

    Label lblUsername = new Label("Username");
    TextField txtUsername = new TextField();
    Label lblPassword = new Label("Password");
    PasswordField pwfPassword = new PasswordField();
    Button btnLogin = new Button("Log in");
    Label lblErrorMessage = new Label();
    //endregion

    //region Initiate Window
    private void loadWindow(Stage window) {
        window.setHeight(200);
        window.setWidth(260);
        window.setTitle("Fabulous Cinema -- Login");

        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        //initialize scene
        scene = new Scene(gridPane);

        setEventHandlers(window);

        //Style + grid assignment
        styleWindow();
        assignGrid();

        //Display elements, scene and window
        window.setScene(scene);
        window.show();
    }

    private void styleWindow() {
        //basic startup styling
        txtUsername.setPromptText("Enter username");
        pwfPassword.setPromptText("Enter password");
        lblErrorMessage.setVisible(false);
        btnLogin.setVisible(false);

        JMetro jMetro = new JMetro(Style.DARK);
        jMetro.setScene(scene);

        //css
        scene.getStylesheets().add("css/style.css");
        gridPane.setId("login");
        lblErrorMessage.setStyle("-fx-text-fill: red");
    }

    private void assignGrid() {
        GridPane.setConstraints(lblUsername, 0, 0);
        GridPane.setConstraints(txtUsername, 1, 0);
        GridPane.setConstraints(lblPassword, 0, 1);
        GridPane.setConstraints(pwfPassword, 1, 1);
        GridPane.setConstraints(btnLogin, 0, 2);
        GridPane.setConstraints(lblErrorMessage, 0, 3, 1, 3);

        gridPane.getChildren().addAll(lblUsername, txtUsername, lblPassword,
                pwfPassword, btnLogin, lblErrorMessage);
    }

    private void setEventHandlers(Stage loginWindow) {
        StringProperty txtUsernameProperty = txtUsername.textProperty();
        txtUsernameProperty.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                lblErrorMessage.setVisible(false);
            }
        });

        StringProperty pwfPasswordProperty = pwfPassword.textProperty();
        pwfPasswordProperty.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                btnLogin.setVisible(pwfPassword.getText().matches("(?=.*[0-9])(?=.*[!@#$%^&*]).{8,}$"));
            }
        });

        btnLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                List<User> dbUsers = db.getUsers();
//                try {
//
//                } catch(UserNotFoundException | IncorrectPasswordException e) {
//                    lblErrorMessage.setVisible(true);
//                    lblErrorMessage.setText(e.getMessage());
//                }
                for (User user : dbUsers) {
                    //check if user exists in database
                    if (txtUsername.getText().equals(user.getUsername())) {
                        //check for correct password
                        if (pwfPassword.getText().equals(user.getPassword())) {
                            MainWindow mainWindow = new MainWindow(db, user);
                            loginWindow.close();
                            return;
                        }else {
                            lblErrorMessage.setText("Incorrect Password...");
                            lblErrorMessage.setVisible(true);
                            pwfPassword.clear();
                        }
                    }
                }
                lblErrorMessage.setText("User not found");
                lblErrorMessage.setVisible(true);
                txtUsername.clear();
                pwfPassword.clear();
            }
        });
    }
    //endregion

    public LoginWindow(Database db) {
        this.db = Objects.requireNonNullElseGet(db, Database::new);

        Stage loginWindow = new Stage();
        loadWindow(loginWindow);
    }
}