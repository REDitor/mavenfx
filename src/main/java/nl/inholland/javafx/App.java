package nl.inholland.javafx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Random;

public class App extends Application {
    Button[][] board = new Button[3][3];
    int[] rows;
    int[] columns;
    int[] diagonal1;
    int[] diagonal2;

    //region Elements
    Label lblCurrentTurn = new Label("Current turn:");
    Label lblCurrentTurnResult = new Label();

    GridPane gridPane = new GridPane();
    //endregion

    //region InitiateApp
    private void createButtons() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                board[row][col] = new Button("_");
                board[row][col].setPrefSize(100, 100);
                board[row][col].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        lblCurrentTurnResult.setText("It's your turn!");

                        //User turn
                        Button clickedButton = (Button)actionEvent.getSource();
                        clickedButton.setText("X");
                        clickedButton.setDisable(true);
                        if (checkForWinner()) {
                            lblCurrentTurnResult.setText("Congratulations, you won the game!");
                            return;
                        }

                        //Computer turn
                        Random rng = new Random();
                        int randomRow = rng.nextInt(2);
                        int randomColumn = rng.nextInt(2);

                        while (!checkTurn(randomRow, randomColumn)) {
                            randomRow = rng.nextInt(2); //2 or 3?
                            randomColumn = rng.nextInt(2);
                        }

                        board[randomRow][randomColumn].setText("O");
                        board[randomRow][randomColumn].setDisable(true);
                        if (checkForWinner()) {
                            lblCurrentTurnResult.setText("The computer won the game, better luck next time...");
                        }
                    }
                });
            }
        }
    }

    private void assignGrid() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                GridPane.setConstraints(board[row][col], col, row);
                gridPane.getChildren().add(board[row][col]);
            }
        }

        GridPane.setConstraints(lblCurrentTurn, 0, 3);
        GridPane.setConstraints(lblCurrentTurnResult, 1, 3, 2, 1);
        gridPane.getChildren().addAll(lblCurrentTurn, lblCurrentTurnResult);
    }
    //endregion

    @Override
    public void start(Stage window) throws Exception {
        window.setMinHeight(400);
        window.setMinWidth(340);
        window.setTitle("Tic-Tac-Toe");

        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        createButtons();
        assignGrid();

        Scene scene = new Scene(gridPane);
        window.setScene(scene);
        window.show();
    }

    //region Logical Methods
    private boolean checkTurn(int row, int column) {
        return board[row][column].getText() == null;
    }

    private boolean checkForWinner() {

        //FIXME: crashes the application

//        if (board[0][0].getText().equals("X") && board[0][1].getText().equals("X") && board[0][2].getText().equals("X")
//            || board[1][0].getText().equals("X") && board[1][1].getText().equals("X") && board[1][2].getText().equals("X")
//            || board[2][0].getText().equals("X") && board[2][1].getText().equals("X") && board[2][2].getText().equals("X")) {
//            return true;
//        }
//        return false;
    }
    //endregion
}
