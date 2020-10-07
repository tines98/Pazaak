import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SideCardBoard extends JPanel {

    private Player player;

    SideCardBoard(Player pl){
        player = pl;
        setBackground(Color.ORANGE);
        addMouseListener(new MyMouseListener());
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        for (int i = 0; i < player.getHand().size(); i++) {
            g.setColor(player.getHand().get(i).getColor());
            g.fillRect((i*75)+50,50,75,100);
            g.setColor(Color.BLACK);
            g.drawRect((i*75)+50,50,75,100);
            g.setColor(Color.WHITE);
            g.drawString(player.getHand().get(i).toString(),(i*75)+87,100);
        }
    }

    class MyMouseListener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
//            System.out.println("grrrrr " + (e.getX()-50)/75);
            if (e.getY() >= 50 && e.getY() <= 125 && e.getX()>=50){
                switch ((e.getX()-50)/75){
                    case 0:
                        System.out.println("card "+0);
                        if (!player.getHand().isEmpty())
                            player.place(player.getHand().get(0));
                        break;
                    case 1:
                        System.out.println("card "+1);
                        if (player.getHand().size()>=2)
                            player.place(player.getHand().get(1));
                        break;
                    case 2:
                        System.out.println("card "+2);
                        if (player.getHand().size()>=3)
                            player.place(player.getHand().get(2));
                        break;
                    case 3:
                        System.out.println("card "+3);
                        if (player.getHand().size()==4)
                            player.place(player.getHand().get(3));
                        break;
                    case 4:
                        break;
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

}
