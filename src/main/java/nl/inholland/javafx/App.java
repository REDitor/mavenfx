package nl.inholland.javafx;

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

import static javax.swing.JOptionPane.showMessageDialog;

public class App extends Application {
    //region elements
    Label lblUsername = new Label("Username");
    Label lblPassword = new Label("Password");
    TextField txtUsername = new TextField();
    PasswordField pfPassword = new PasswordField();
    Button btnLogin = new Button("Log in");
    Label lblVisiblePassword = new Label();
    GridPane gridPane = new GridPane();
    //endregion

    //region initiate window
    private void loadWindow(Stage window) {
        window.setHeight(200);
        window.setWidth(300);
        window.setTitle("Login Screen (Improved)");

        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(10); // Vertical spacing between grid items
        gridPane.setHgap(8); // Horizontal spacing between grid items

        styleNodes();
        addToGrid();

        //Display UI elements (the gridpane) in scene + display window
        Scene scene = new Scene(gridPane);
        window.setScene(scene);
        window.show();
    }

    private void styleNodes() {
        txtUsername.setPromptText("Enter username");
        pfPassword.setPromptText("Enter password");

        btnLogin.setVisible(false);
    }

    private void addToGrid() {
        //Grid Assignment
        GridPane.setConstraints(lblUsername, 0, 0);
        GridPane.setConstraints(lblPassword, 0, 1);
        GridPane.setConstraints(txtUsername, 1, 0);
        GridPane.setConstraints(pfPassword, 1, 1);
        GridPane.setConstraints(btnLogin, 1, 2);
        GridPane.setConstraints(lblVisiblePassword, 0, 4, 3, 1);

        gridPane.getChildren().addAll(lblUsername, lblPassword, txtUsername,
                pfPassword, btnLogin, lblVisiblePassword);
    }
    //endregion

    @Override
    public void start(Stage window) throws Exception {
        loadWindow(window);

        StringProperty pfPasswordProperty = pfPassword.textProperty();
        pfPasswordProperty.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                btnLogin.setVisible(lblVisiblePassword.getText().matches("(?=.*[0-9])(?=.*[!@#$%^&*]).{8,}$"));
            }
        });
        lblVisiblePassword.textProperty().bind(pfPasswordProperty);

        btnLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showMessageDialog(null, "You are now successfully logged in");
            }
        });
    }
}
