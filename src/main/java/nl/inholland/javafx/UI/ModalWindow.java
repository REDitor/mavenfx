package nl.inholland.javafx.UI;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ModalWindow {
    private boolean canceled ;
    public ModalWindow(Stage mainWindow) {
        canceled = false;
        Label lblCloseWindow = new Label("Close the window?");
        Button buttonOk = new Button("OK");
        Button buttonCancel = new Button("Cancel");

        GridPane gridPane = new GridPane();
        GridPane.setConstraints(lblCloseWindow, 0,0,2,1);
        GridPane.setConstraints(buttonOk, 0, 1);
        GridPane.setConstraints(buttonCancel, 1, 1);

        gridPane.getChildren().addAll(lblCloseWindow, buttonOk, buttonCancel);
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Scene scene = new Scene(gridPane);
        Stage window = new Stage();
        window.setScene(scene);
        window.sizeToScene();

        buttonOk.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                window.close();
                mainWindow.close();
                System.exit(0);
            }
        });

        buttonCancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                canceled = true;
                window.close();
            }
        });

        window.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                window.close();
            }
        });

        window.showAndWait();
    }

    public boolean checkCancel() {
        if (canceled) {
            return true;
        }
        return false;
    }
}
