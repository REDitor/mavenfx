package nl.inholland.javafx;

public abstract class Player {
    protected Game game;

    public void startNewGame() {
        Game game = Game.getInstance();
    }

    abstract String writeTurn();

    public void setTurn(Player player, int row, int column) {
        game.board[row][column] = player.writeTurn();
    }
}
