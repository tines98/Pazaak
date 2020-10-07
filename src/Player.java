import java.util.ArrayList;
import java.util.Stack;

public class Player implements IPlayer {

    private String name;
    private int points;
    private boolean stand;
    private Stack<Card> cards;
    private ArrayList<SideCard> hand;
    private int wins;

    Player(String name){
        this.name = name;
        wins = 0;
        points = 0;
        stand = false;
        cards = new Stack<>();
        hand = new ArrayList<>();
        createHand();
    }

    public int getWins() {
        return wins;
    }

    public void newRound(){
        cards = new Stack<>();
        points = 0;
        stand = false;
    }

    public void win(){
        wins++;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void createHand(){
        for (int i = 0; i < 4; i++) {
            hand.add(new SideCard());
        }
    }

    @Override
    public ArrayList<SideCard> getHand(){
        return hand;
    }

    @Override
    public Stack<Card> getCards() {
        return cards;
    }

    @Override
    public int getPoints() {
        return points;
    }

    @Override
    public boolean isStand() {
        return stand;
    }

    @Override
    public void stand() {
        this.stand = true;
    }

    @Override
    public void addCard(Card card){
        points += card.getValue();
        cards.push(card);
    }

    @Override
    public void place(Card card) {
        if (!hand.contains(card)) {
            System.out.println("ehhh hand error");
            throw new IllegalArgumentException("CARD NOT IN HAND");
        }
        System.out.println(name+" played a "+card.getValue()+" card");
        cards.push(card);
        points += card.getValue();
        hand.remove(card);
    }
}
