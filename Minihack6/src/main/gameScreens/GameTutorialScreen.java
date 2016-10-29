package main.gameScreens;

import GameButtons.GameButton;
import main.GameConfig;
import utilities.Utils;
import views.GameView;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Created by Cuong on 10/29/2016.
 */
public class GameTutorialScreen extends GameScreen {

    ArrayList<String> arrayList;

    int currentImage = 0;

    private Image background;

    public GameTutorialScreen(ScreenManager screenManager) {
//        background = new
        super(screenManager);
        arrayList = new ArrayList<>();
        arrayList.add("movements.png");
        arrayList.add("monsters.png");
        arrayList.add("traps.png");
        init();
    }

    @Override
    public void init() {
        if (currentImage >= arrayList.size())
            return;
        background = Utils.getImage(arrayList.get(currentImage));
    }

    @Override
    public void update(Graphics g) {
        g.drawImage(background,0,0, GameConfig.BACKGROUND_WIDTH,GameConfig.BACKGROUND_HEIGHT,null);
    }

    @Override
    public void run() {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (GameButton.nextTutorialButton.isClick(e.getX(), e.getY())) {
            currentImage++;
            if (currentImage == arrayList.size()) {
                this.screenManager.change(new MenuGameScreen(screenManager), false);
            }
            init();

        }
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

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
