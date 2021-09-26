package nl.inholland.javafx;

public abstract class Player {
    Game game = Game.getInstance();

    abstract String[][] setTurn(int row, int column);
}
