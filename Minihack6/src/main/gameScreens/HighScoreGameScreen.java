package main.gameScreens;

import main.GameConfig;
import utilities.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Cuong on 10/29/2016.
 */
public class HighScoreGameScreen extends GameScreen {

    private Image background = null;
    private int nScore = 0;
    private int[] scores = new int[10];

    public HighScoreGameScreen(ScreenManager screenManager) {
        super(screenManager);
        init();
    }

    @Override
    public void init() {
        background = Utils.getImage("menuback.jpg");
    }

    private void getInfo() {
        Scanner input = null;
        try {
            input = new Scanner(new File("highscore.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        nScore = 0;
        while (input.hasNext()) {
            nScore++;
            scores[nScore] = input.nextInt();
        }
    }

    @Override
    public void update(Graphics g) {
        g.drawImage(background,0,0, GameConfig.BACKGROUND_WIDTH,GameConfig.BACKGROUND_HEIGHT,null);

        Font fnt0 = new Font("arial",Font.BOLD,50);
        g.setFont(fnt0);
        g.setColor(Color.GREEN);
        getInfo();
        for (int i=1;i<=nScore;i++) {
            String a = Integer.toString(i);
            System.out.println(a);
            String b = Integer.toString(scores[i]);
            g.drawString("Rank " + a + " " + b + "a",100,175);
        }
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
