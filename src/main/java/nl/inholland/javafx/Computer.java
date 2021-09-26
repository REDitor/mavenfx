package nl.inholland.javafx;

public class Computer extends Player{
    @Override
    public String[][] setTurn(int row, int column) {
        game.board[row][column] = "O";

        return game.board;
    }
}
