package nl.inholland.javafx;

import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class App extends Application {

    //region elements
    Label lblUsername = new Label("Username");
    Label lblPassword = new Label("Password");
    Label lblVisiblePassword = new Label();

    TextField txtUsername = new TextField();
        txtUsername.setPromptText("Enter username");

    PasswordField pfPassword = new PasswordField();
        pfPassword.setPromptText("Enter password");
    Button btnLogin = new Button("Log in");
    //endregion

    //region initiate window
    private void loadWindow(Stage window) {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(10); // Vertical spacing between grid items
        gridPane.setHgap(8); // Horizontal spacing between grid items
        addGridPaneElements(window, gridPane);

        //Display UI elements (the gridpane) in scene + display window
        Scene scene = new Scene(gridPane);
        window.setScene(scene);
        window.show();
    }


    //endregion

    @Override
    public void start(Stage window) throws Exception {
        window.setHeight(200);
        window.setWidth(300);
        window.setTitle("Week 4 - Lesson 2 Exercise - Login Screen");
        loadWindow(window);
    }



    private void addGridPaneElements(Stage window, GridPane gridPane) {

        //Elements


        //Store info/data
        String username = txtUsername.getText();
        String password = pfPassword.getText();

        StringProperty pfPasswordProperty = pfPassword.textProperty();
        lblVisiblePassword.textProperty().bind(pfPasswordProperty);

        btnLogin.setVisible(false);

        //FIXME: Button not appearing on met criteria
        pfPasswordProperty.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if (password.matches("^(?=.*[0-9])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8}$")) {
                    btnLogin.setVisible(true);
                }
            }
        });

        //Grid Assignment
        GridPane.setConstraints(lblUsername, 0, 0);
        GridPane.setConstraints(lblPassword, 0, 1);
        GridPane.setConstraints(txtUsername, 1, 0);
        GridPane.setConstraints(pfPassword, 1, 1);
        GridPane.setConstraints(btnLogin, 1, 2);
        GridPane.setConstraints(lblVisiblePassword, 0, 4);
        gridPane.getChildren().addAll(lblUsername, lblPassword, txtUsername, pfPassword, btnLogin, lblVisiblePassword);
    }
}
