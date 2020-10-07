public interface IGame {

    void gameLoop();

    void nextTurn();

    void checkWin();

    void win(Player winner);

    void hit();

    void place(Card card);

    void stand();

    void endGame();

    void opponentTurn();
}
