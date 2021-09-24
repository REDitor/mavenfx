package nl.inholland.javafx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class App extends Application {
    private static final double pricePerDay = 45;
    private static final double pricePerExtraKm = 0.25;
    private static final int fullTankSize = 40;
    private static final double pricePerLiter = 2;

    //region WindowElements
    Label lblNumberOfDaysRented = new Label("Number of days rented:");
    TextField txtNumberOfDaysRented = new TextField();
    Label lblNumberOfKmDriven = new Label("Number of kilometers driven:");
    TextField txtNumberOfKmDriven = new TextField();
    CheckBox cbxNoFullTank = new CheckBox("Fuel tank not full when returned");
    Label lblNumberOfLiters = new Label("Number of liters:");
    TextField txtNumberOfLiters = new TextField();
    Label lblErrorMessage = new Label(String.format("Please enter a number lower than %d Liters", fullTankSize));
    Button btnCalcPayment = new Button("Calculate payment");
    Label lblAmountDue = new Label("Amount due:");
    Label lblAmountDueResult = new Label();
    GridPane gridPane = new GridPane();
    //endregion

    //region Initiate Window
    private void loadWindow(Stage window) {
        window.setHeight(250);
        window.setWidth(400);
        window.setTitle("Car Rental");
        window.setX(100);

        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(10);
        gridPane.setHgap(8);
        assignGrid();

        Scene scene = new Scene(gridPane);

        window.setScene(scene);
        window.show();

        lblNumberOfLiters.setVisible(false);
        txtNumberOfLiters.setVisible(false);
        lblErrorMessage.setVisible(false);
    }

    private void assignGrid() {
        GridPane.setConstraints(lblNumberOfDaysRented, 0, 0);
        GridPane.setConstraints(txtNumberOfDaysRented, 1, 0);
        GridPane.setConstraints(lblNumberOfKmDriven, 0, 1);
        GridPane.setConstraints(txtNumberOfKmDriven, 1, 1);
        GridPane.setConstraints(cbxNoFullTank, 0, 2);
        GridPane.setConstraints(lblNumberOfLiters, 0, 3);
        GridPane.setConstraints(txtNumberOfLiters, 1, 3);
        GridPane.setConstraints(lblErrorMessage, 0, 4);
        GridPane.setConstraints(btnCalcPayment, 1, 4);
        GridPane.setConstraints(lblAmountDue, 0, 5);
        GridPane.setConstraints(lblAmountDueResult, 1, 5);
        gridPane.getChildren().addAll(lblNumberOfDaysRented, txtNumberOfDaysRented, lblNumberOfKmDriven,
                txtNumberOfKmDriven, cbxNoFullTank, lblNumberOfLiters, txtNumberOfLiters,
                btnCalcPayment, lblAmountDue, lblAmountDueResult);
    }
    //endregion

    @Override
    public void start(Stage window) throws Exception {
        loadWindow(window);

        cbxNoFullTank.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (cbxNoFullTank.isSelected()) {
                    lblNumberOfLiters.setVisible(true);
                    txtNumberOfLiters.setVisible(true);
                }else {
                    txtNumberOfLiters.clear();
                    lblNumberOfLiters.setVisible(false);
                    txtNumberOfLiters.setVisible(false);
                }
            }
        });

        btnCalcPayment.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                lblAmountDueResult.setText(String.format("%.2f", calculatePayment()));
            }
        });
    }

    private double calculatePayment() {
        int rentedDays = parseInt(txtNumberOfDaysRented.getText());
        int kmDriven = parseInt(txtNumberOfKmDriven.getText());

        double amountDue = (double)rentedDays * pricePerDay;

        if (kmDriven / rentedDays > 100) {
            amountDue += (double)(kmDriven - 100) * pricePerExtraKm;
        }

        if (parseInt(txtNumberOfLiters.getText()) >= 40) {
            lblErrorMessage.setStyle("-fx-text-fill: red;");
            lblErrorMessage.setVisible(true);
        }else if (cbxNoFullTank.isSelected() && txtNumberOfLiters.getText().length() > 0) {
            //full tank size isn't specified, so I'm using a constant of 40 liters,
            //and I consider the number of liters to be the amount of liters still in the tank.
            lblErrorMessage.setVisible(false);
            amountDue += ((double)fullTankSize - parseDouble(txtNumberOfLiters.getText())) * pricePerLiter;
        }

        return amountDue;
    }
}
