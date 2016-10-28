package main.gameScreens;

import main.GameConfig;
import utilities.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by Cuong on 10/29/2016.
 */
public class GameOverScreen extends GameScreen {

    private Image background;

    public GameOverScreen(ScreenManager screenManager) {
        super(screenManager);
        init();
        Utils.playSound("music/GameOverSound.wav", false);
    }

    @Override
    public void init() {
        background = Utils.getImage("negan-walking-dead1.jpg");
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
        if (e.getKeyCode() == KeyEvent.VK_R) {
            this.screenManager.change(new PlayGameScreen(screenManager), false);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

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

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
