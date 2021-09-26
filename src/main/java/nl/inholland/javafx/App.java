package nl.inholland.javafx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Random;

import static javax.swing.JOptionPane.showMessageDialog;

public class App extends Application {
    Game game;
    Player user;
    Player computer;

    //region Elements
    Button[][] buttons = new Button[3][3];
    Label lblCurrentTurn = new Label("Current turn:");
    Label lblCurrentTurnResult = new Label();
    GridPane gridPane = new GridPane();
    //endregion

    //region InitiateWindow
    private void loadWindow(Stage window) {
        window.setMinHeight(400);
        window.setMinWidth(340);
        window.setTitle("Tic-Tac-Toe");

        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        CreateButtons();
        styleNodes();
        assignGrid();

        Scene scene = new Scene(gridPane);
        window.setScene(scene);
        window.show();
    }

    private void CreateButtons() {
        for (int row = 0; row < buttons.length; row++) {
            for (int col = 0; col < buttons[0].length; col++) {
                buttons[row][col] = new Button("_");
            }
        }
    }

    private void assignGrid() {
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(33);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(33);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(33);

        gridPane.getColumnConstraints().addAll(col1, col2, col3);

        for (int row = 0; row < buttons.length; row++) {
            for (int col = 0; col < buttons[0].length; col++) {
                GridPane.setConstraints(buttons[row][col], col, row);
                gridPane.getChildren().add(buttons[row][col]);
            }
        }

        GridPane.setConstraints(lblCurrentTurn, 0, 3, 3, 1);
        GridPane.setConstraints(lblCurrentTurnResult, 1, 3);

        gridPane.getChildren().addAll(lblCurrentTurn, lblCurrentTurnResult);
    }

    private void styleNodes() {
        for (Button[] row : buttons) {
            for (Button button : row) {
                button.setPrefSize(100, 100);
            }
        }
    }
    //endregion

    @Override
    public void start(Stage window) throws Exception {
        loadWindow(window);
        startGame();

        while (!checkForWinner()) {
            for (Button[] row : buttons) {
                for (Button button : row) {
                    button.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            int rowIndex = GridPane.getRowIndex(button);
                            int columnIndex = GridPane.getColumnIndex(button);

                            game.board = user.setTurn(rowIndex, columnIndex);
                            button.setText(game.board[rowIndex][columnIndex]);
                            button.setDisable(true);

                            Random rng = new Random();

                            while (game.board[rowIndex][columnIndex] != null) {
                                rowIndex = rng.nextInt(game.board.length);
                                columnIndex = rng.nextInt(game.board[0].length);
                            }
                            game.board = computer.setTurn(rowIndex, columnIndex);
                            button.setText(game.board[rowIndex][columnIndex]);
                            button.setDisable(true);
                        }
                    });

                }
            }
        }
        showMessageDialog(null, String.format("The %s won the game", game.winner.getClass()));
    }

    private void startGame() {
        user = new User();
        computer = new Computer();
        game = Game.getInstance();
    }

    private boolean checkForWinner() {
        int userRow = 0;
        int computerRow = 0;

        for (int row = 0; row < game.board.length; row++) {
            for (int col = 0; col < game.board[0].length; col++) {
                if (userRow == 3) {
                    game.winner = user;
                    return true;
                }else if (computerRow == 3) {
                    game.winner = computer;
                    return true;
                }

                if (game.board[row][col] == "X") {
                    userRow++;
                } else if (game.board[row][col] == "O") {
                    computerRow++;
                }
            }
        }
        return false;
    }
}
