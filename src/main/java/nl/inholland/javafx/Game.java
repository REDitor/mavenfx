package nl.inholland.javafx;

public class Game {
    private static Game uniqueGame;
    public String[][] board = new String[3][3];
    Player winner;

    private Game(){}

    public static Game getInstance() {
        if (uniqueGame == null) {
            uniqueGame = new Game();
        }
        return uniqueGame;
    }
}
