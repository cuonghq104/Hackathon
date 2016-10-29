package main.gameScreens;

import GameButtons.GameButton;
import main.GameConfig;
import utilities.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by Le Huy Duc on 26/10/2016.
 */
public class MenuGameScreen extends GameScreen{

    private Image background;

    public MenuGameScreen(ScreenManager screenManager) {
        super(screenManager);
        init();
        Utils.playSound("music/background_music.wav", true);
    }

    @Override
    public void init() {
        background = Utils.getImage("title.jpg");
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
        if (e.getKeyCode() == KeyEvent.VK_T) {
            this.screenManager.change(new GameTutorialScreen(screenManager), false);
        } else if (e.getKeyCode()==KeyEvent.VK_E) {
            this.screenManager.change(new EditorGameScreen(screenManager),false);
        }
        else    {
//        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                this.screenManager.change(new PlayGameScreen(screenManager), true);
//        }
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
        if (GameButton.editorButton.isClick(e.getX(), e.getY())) {
            this.screenManager.change(new EditorGameScreen(screenManager), false);
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
