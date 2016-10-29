package main.gameScreens;

import main.GameConfig;
import main.GameWindow;
import utilities.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;

/**
 * Created by Cuong on 10/29/2016.
 */
public class GameOverScreen extends GameScreen {

    private Image background;
    private long[] highScores = new long[10];
    private int nScore = 0;

    public GameOverScreen(ScreenManager screenManager) {
        super(screenManager);
        init();
        Utils.playSound("music/GameOverSound.wav", false);

//        Scanner fileHighScore = null;
//        try {
//            fileHighScore = new Scanner(new File("highscore.txt"));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        nScore = 0;
//        while (fileHighScore.hasNext()) {
//            long k = fileHighScore.nextLong();
//            highScores[++nScore] = k;
//        }
//        highScores[++nScore] = (System.currentTimeMillis() - GameWindow.firstGame)/1000;
//        for (int i=1;i<nScore;i++)
//            for (int j=i+1;j<=nScore;j++) if (highScores[i] < highScores[j]) {
//                long x = highScores[i];
//                highScores[i] = highScores[j];
//                highScores[j] = x;
//            }
//
//        PrintWriter output = null;
//        try {
//            output = new PrintWriter(new File("highscore.txt"));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        for (int i=1;i<=nScore;i++) output.println(highScores[i]);
//        output.close();
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
