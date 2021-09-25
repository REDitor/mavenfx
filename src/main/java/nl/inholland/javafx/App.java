package nl.inholland.javafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class App extends Application {

    Button btnUpperLeft = new Button("_");
    Button btnUpperMid = new Button("_");
    Button btnUpperRight = new Button("_");
    Button btnMiddleLeft = new Button("_");
    Button btnMiddleMid = new Button("_");
    Button btnMiddleRight = new Button("_");
    Button btnLowerLeft = new Button("_");
    Button btnLowerMid = new Button("_");
    Button btnLowerRight = new Button("_");
    Label lblCurrentTurn = new Label("Current turn:");
    Label lblCurrentTurnResult = new Label();
    GridPane gridPane = new GridPane();

    @Override
    public void start(Stage window) throws Exception {
        loadWindow(window);

    }

    //Methods
    private void loadWindow(Stage window) {
        window.setHeight(300);
        window.setWidth(400);
        window.setTitle("Currency Converter");
        window.setX(100);

        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        assignGrid();
        Scene scene = new Scene(gridPane);
        window.setScene(scene);
        window.show();
    }

    private void assignGrid() {
        GridPane.setConstraints(, 0, 0);
        GridPane.setConstraints(, 0, 0);
        GridPane.setConstraints(, 0, 0);
        GridPane.setConstraints(, 0, 0);
        GridPane.setConstraints(, 0, 0);
        GridPane.setConstraints(, 0, 0);
        GridPane.setConstraints(, 0, 0);
        GridPane.setConstraints(, 0, 0);
        GridPane.setConstraints(, 0, 0);
        GridPane.setConstraints(, 0, 0);
        GridPane.setConstraints(, 0, 0);

        gridPane.getChildren().addAll(lblEuroAmount, txtEuroAmount, btnConvertToDollar, lblDollarAmount, lblDollarAmountResult);
    }
}
