import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Game extends JFrame implements IGame {

    private int screenX = 1920 / 2;
    private int screenY = 1920 / 2;
    private Timer t;
    private Player player;
    private Player opponent;
    private Player currentTurn;
    private CardBoard plBoard;
    private CardBoard opBoard;


    Game() {
        player = new Player("You");
        opponent = new Player("Jubjub");
        currentTurn = opponent;
        setLayout(new GridLayout(3,1));
        t = new Timer(33, actionEvent -> repaint());
        t.setRepeats(true);
        setTitle("Pazaak");
        setSize(screenX,screenY);
//        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        plBoard = new CardBoard(player);
        opBoard = new CardBoard(opponent);
        opBoard.setBackground(Color.RED);
        add(opBoard);
        add(plBoard);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,2));
        SideCardBoard sideCardBoard = new SideCardBoard(player);
        panel.add(sideCardBoard);
        new Buttonbar(panel,this);
        add(panel);
        t.start();
        setVisible(true);
        System.out.println("asdasdasda "+getContentPane().getComponents().length);
        gameLoop();
    }

    @Override
    public void place(Card card) {
        currentTurn.place(card);
    }

    boolean isPlayerTurn(){
        return currentTurn.equals(player);
    }

    @Override
    public void nextTurn() {
        if (currentTurn.equals(player))
            currentTurn = opponent;
        else
            currentTurn = player;
    }

    @Override
    public void checkWin() {
        boolean playerBust = player.getPoints() > 20;
        boolean opponentBust = opponent.getPoints() > 20;
        if (playerBust && opponentBust)
            newRound(null);
        if (playerBust)
            newRound(opponent);
        else if (opponentBust)
            newRound(player);
        else {
            if (player.getPoints() > opponent.getPoints())
                newRound(player);
            else if (player.getPoints() < opponent.getPoints())
                newRound(opponent);
            else newRound(null);
        }
    }

    @Override
    public void gameLoop() {
        while (!player.isStand() || !opponent.isStand()) {
            if (currentTurn.isStand())
                nextTurn();
            if (currentTurn.equals(opponent)){
                opponentTurn();
                continue;
            }
            System.out.print("");
        }
        checkWin();
    }

    @Override
    public void win(Player winner) {
        if (winner == null) {
            System.out.println("It's a tie!");
        } else {
            System.out.println("Winner is " + winner.getName());
            System.out.println(opponent.getName()+" got " + opponent.getWins());
            System.out.println("You got " + player.getWins());
        }
        endGame();
    }

    @Override
    public void opponentTurn() {
        String name = opponent.getName();
        System.out.println(name+"'s turn!");
        System.out.println(name+"'s sum: " + opponent.getPoints());
        if (player.isStand() && player.getPoints() > 20 && opponent.getPoints() < 20){
            stand();
            return;
        }
        if (player.isStand() && opponent.getPoints() > player.getPoints()) {
            stand();
            return;
        }
        for (int i = 0; i < opponent.getHand().size(); i++) {
            if (opponent.getPoints() + opponent.getHand().get(i).getValue() == 20){
                opponent.place(opponent.getHand().get(i));
                break;
            }
            if (opponent.getPoints() > 20 && opponent.getPoints()-opponent.getHand().get(i).getValue() < 20){
                opponent.place(opponent.getHand().get(i));
                break;
            }
        }
        if (opponent.getPoints() > 20) {
            System.out.println(name+" busts!");
            System.out.println(opponent.getHand());
            stand();
            return;
        }
        if (opponent.getPoints() >= 17) {
            stand();
        }
        else {
            hit();
        }
    }

    public void newRound(Player winner){
        if (winner == null);
        else if (winner.equals(player))
            player.win();
        else if (winner.equals(opponent))
            opponent.win();

        player.newRound();
        opponent.newRound();
        currentTurn = opponent;
        System.out.println("bruh " + player.isStand());

        if (player.getWins() == 3)
            win(player);
        else if (opponent.getWins() == 3)
            win(opponent);
        else {
            System.out.println("NEW ROUND");
            gameLoop();
        }
    }

    @Override
    public void stand() {
        System.out.println(currentTurn.getName() + " stands");
        currentTurn.stand();
        nextTurn();
    }

    @Override
    public void hit() {
        Card card = new Card();
        currentTurn.addCard(card);
        System.out.println(currentTurn.getName() + " drew a " + card.getValue());
        System.out.println("new sum: " + currentTurn.getPoints());
        nextTurn();
    }

    @Override
    public void endGame() {
        t.stop();
        setVisible(false);
        System.exit(1);
    }

}
