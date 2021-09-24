package nl.inholland.javafx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class App extends Application {

    //I don't know if this is the right way to
    //be able to access the elements but this way
    //I can use them in every method.

    Label lblEuroAmount = new Label("Amount €:");
    TextField txtEuroAmount = new TextField();
    Button btnConvertToDollar = new Button("Convert Euro to Dollar");
    Label lblDollarAmount = new Label("Amount $:");
    Label lblDollarAmountResult = new Label("...");
    GridPane gridPane = new GridPane();

    @Override
    public void start(Stage window) throws Exception {
        loadWindow(window);
        btnConvertToDollar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                double euro = Double.parseDouble(txtEuroAmount.getText());
                double dollarResult = convertEuroToDollar(euro);
                lblDollarAmountResult.setText(String.format("%.2f", convertEuroToDollar(euro)));
            }
        });
    }

    //Methods
    private void loadWindow(Stage window) {
        window.setHeight(200);
        window.setWidth(300);
        window.setTitle("Currency Converter");
        window.setX(100);

        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(10);
        gridPane.setHgap(8);
        assignGrid();
        Scene scene = new Scene(gridPane);
        window.setScene(scene);
        window.show();
    }

    private void assignGrid() {
        GridPane.setConstraints(lblEuroAmount, 0, 0);
        GridPane.setConstraints(txtEuroAmount, 1, 0);
        GridPane.setConstraints(btnConvertToDollar, 1, 1);
        GridPane.setConstraints(lblDollarAmount, 0, 2);
        GridPane.setConstraints(lblDollarAmountResult, 1, 2);
        gridPane.getChildren().addAll(lblEuroAmount, txtEuroAmount, btnConvertToDollar, lblDollarAmount, lblDollarAmountResult);
    }

    public double convertEuroToDollar(double euro) {
        return euro * 1.18;
    }
}
