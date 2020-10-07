import java.util.ArrayList;
import java.util.Stack;

public interface IPlayer {


    String getName();
    void createHand();
    int getPoints();
    Stack<Card> getCards();
    ArrayList<SideCard> getHand();
    boolean isStand();
    void stand();
    void addCard(Card card);
    void place(Card card);
}
