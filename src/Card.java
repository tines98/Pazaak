import java.awt.*;
import java.util.Random;

public class Card {
    protected Random r;
    protected int value;
    protected Color color;


    Card(){
        r = new Random();
        value = r.nextInt(10)+1;
        color = Color.GREEN;
    }

    @Override
    public String toString() {
        if (value > 0)
            return "+"+value;
        else return ""+value;
    }

    public int getValue() {
        return value;
    }

    Color getColor(){
        return color;
    }
}
