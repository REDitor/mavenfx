package nl.inholland.javafx;

public class User extends Player{
    @Override
    public String[][] setTurn(int row, int column) {
        game.board[row][column] = "X";

        return game.board;
    }
}
