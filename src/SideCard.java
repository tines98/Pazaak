import java.awt.*;

public class SideCard extends Card {

    SideCard(){
        int val = r.nextInt(6)+1;
        if (r.nextBoolean()) val *= -1;
        this.value = val;
        if (value > 0) color = Color.BLUE;
        else color = Color.RED;
    }

}
