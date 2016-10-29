package main;

import controllers.*;
import main.GameConfig;
import main.gameScreens.GameScreen;
import main.gameScreens.HighScoreGameScreen;
import main.gameScreens.MenuGameScreen;
import main.gameScreens.ScreenManager;
import models.Player;
import utilities.Audio;
import utilities.Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Stack;

/**
 * Created by Le Huy Duc on 19/10/2016.
 */
public class GameWindow extends Frame implements Runnable, ScreenManager {

    private int BACKGROUND_WIDTH = GameConfig.BACKGROUND_WIDTH, BACKGROUND_HEIGHT = GameConfig.BACKGROUND_HEIGHT;
    public static  GameState gameState = GameState.MENU;

    BufferedImage backBufferImage = new BufferedImage(BACKGROUND_WIDTH,BACKGROUND_HEIGHT+40,
            BufferedImage.TYPE_INT_ARGB);

    private GameScreen currentGameScreen = new MenuGameScreen(this);
    private Stack<GameScreen> screenStack = new Stack<>();
    public static long firstGame = 0;
    public static boolean editing = false;

    public boolean musicOn = true;

    public GameWindow() {
        this.setVisible(true);
        this.setSize(BACKGROUND_WIDTH,BACKGROUND_HEIGHT);

        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

        this.attach(currentGameScreen);

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_M) {
                    if (musicOn) {
                        Audio.setMasterOutputVolume(0);
                        musicOn = false;
                        System.out.println("aa");
                    } else {
                        Audio.setMasterOutputVolume((float) 0);
                        musicOn = true;
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
//

        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    @Override
    public void update(Graphics g) {
        Graphics backBufferGraphics = backBufferImage.getGraphics();

        currentGameScreen.update(backBufferGraphics);

        // 29 để ko bị vướng vào title bar
        g.drawImage(backBufferImage,0,29,BACKGROUND_WIDTH,BACKGROUND_HEIGHT,this);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(25);

                currentGameScreen.run();

                repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    //********** SCREEN MANAGER ****************************************************

    @Override
    public void change(GameScreen newGameScreen, boolean addToStack) {
        if (currentGameScreen!=null) detach(currentGameScreen);

        if (addToStack && currentGameScreen!=null) {
            screenStack.push(currentGameScreen);
        }

        currentGameScreen = newGameScreen;
        attach(currentGameScreen);
    }

    public void back() {

    }

    public void detach(GameScreen oldGameScreen) {
        this.removeKeyListener(oldGameScreen);
        this.removeMouseListener(oldGameScreen);
    }

    public void attach(GameScreen newGameScreen) {
        this.currentGameScreen = newGameScreen;
        this.addKeyListener(newGameScreen);
        this.addMouseListener(newGameScreen);
    }
}
