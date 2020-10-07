import javax.swing.*;
import java.awt.*;

public class CardBoard extends JPanel {

    private Player player;

    CardBoard(Player pl){
        player = pl;
        setBackground(Color.GRAY);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        String stand = "";
        if (player.isStand()) stand = " STANDING";
        g.drawString(player.getWins() + " " +player.getName()+" sum: "
                        +player.getPoints()+stand,10,
                25);
        for (int i = 0; i < player.getCards().size(); i++) {
            g.setColor(player.getCards().get(i).getColor());
            g.fillRect((i*75)+50,50,75,100);
            g.setColor(Color.BLACK);
            g.drawRect((i*75)+50,50,75,100);
            g.drawString(player.getCards().get(i).toString(),(i*75)+87,100);
        }
    }
}
