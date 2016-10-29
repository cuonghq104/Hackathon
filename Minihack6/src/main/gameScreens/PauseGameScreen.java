package main.gameScreens;

import java.awt.*;

/**
 * Created by Le Huy Duc on 29/10/2016.
 */
public class PauseGameScreen  {

    public void update(Graphics g) {
        Font fnt0 = new Font("arial",Font.BOLD,50);
        g.setFont(fnt0);
        g.setColor(Color.GREEN);
        g.drawString("GAME PAUSED",100,175);
    }

    public static final PauseGameScreen instance = new PauseGameScreen();
}
