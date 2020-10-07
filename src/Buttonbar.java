import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Buttonbar extends JPanel {

    JButton hit;
    JButton stand;
    Game game;

    Buttonbar(JPanel panel, Game game){
        this.game = game;
        setLayout(new GridLayout(2,1));
        hit = new JButton("Hit");
        stand = new JButton("Stand");
        hit.addActionListener(new ButtonListener());
        stand.addActionListener(new ButtonListener());
        hit.setActionCommand("Hit");
        stand.setActionCommand("Stand");
        add(hit);
        add(stand);
        panel.add(this);
    }


    class ButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Hit")){
                if (game.isPlayerTurn())
                    game.hit();
            }
            if (e.getActionCommand().equals("Stand")){
                if (game.isPlayerTurn())
                    game.stand();
            }
        }
    }
}
