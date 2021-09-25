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

import static javax.swing.JOptionPane.showMessageDialog;

public class App extends Application {
    Game game;
    Player currentPlayer;
    Player user;
    Player computer;

    //region Elements
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
    //endregion

    //region InitiateWindow
    private void loadWindow(Stage window) {
        window.setHeight(400);
        window.setWidth(350);
        window.setTitle("Currency Converter");

        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        styleNodes();
        assignGrid();

        Scene scene = new Scene(gridPane);
        window.setScene(scene);
        window.show();
    }

    private void assignGrid() {
        GridPane.setConstraints(btnUpperLeft, 0, 0);
        GridPane.setConstraints(btnUpperMid, 1, 0);
        GridPane.setConstraints(btnUpperRight, 2, 0);
        GridPane.setConstraints(btnMiddleLeft, 0, 1);
        GridPane.setConstraints(btnMiddleMid, 1, 1);
        GridPane.setConstraints(btnMiddleRight, 2, 1);
        GridPane.setConstraints(btnLowerLeft, 0, 2);
        GridPane.setConstraints(btnLowerMid, 1, 2);
        GridPane.setConstraints(btnLowerRight, 2, 2);
        GridPane.setConstraints(lblCurrentTurn, 0, 3, 3, 1);
        GridPane.setConstraints(lblCurrentTurnResult, 1, 3);

        gridPane.getChildren().addAll(btnUpperLeft, btnUpperMid, btnUpperRight, btnMiddleLeft, btnMiddleMid,
                btnMiddleRight, btnLowerLeft, btnLowerMid, btnLowerRight, lblCurrentTurn,
                lblCurrentTurnResult);
    }

    private void styleNodes() {
       //TODO: resize buttons
    }
    //endregion

    @Override
    public void start(Stage window) throws Exception {
        loadWindow(window);
        startGame();

        btnUpperLeft.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                btnUpperLeft.setText(currentPlayer.writeTurn());
                if (currentPlayer instanceof User) {
                    currentPlayer = computer;
                }else {
                    currentPlayer = user;
                }
                btnUpperLeft.setDisable(true);
                checkForWin([GridPane.getRowIndex(btnUpperLeft)][GridPane.getColumnIndex(btnUpperLeft)]);
            }
        });
    }

    public void startGame() {
        user = new User();
        computer = new Computer();
        currentPlayer = user;
        game = Game.getInstance();
    }

    public void checkForWin(String[][] playingField) {
        int userRow = 0;
        int computerRow = 0;

        for (int row = 0; row < game.board.length; row++) {
            for (int col = 0; col < game.board[0].length; col++) {
                if (userRow == 3) {
                    showMessageDialog(null, "You won the game, Congratulations");
                    break;
                }else if (computerRow == 3) {
                    showMessageDialog(null, "The computer won the game, better luck next time");
                    break;
                }

                if (game.board[row][col] == "X") {
                  userRow++;
                }else if (game.board[row][col] == "O") {
                   computerRow++;
                }
            }
        }
    }
}
