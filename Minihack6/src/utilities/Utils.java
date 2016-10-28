package utilities;

import main.GameConfig;
import main.gameScreens.PlayGameScreen;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Le Huy Duc on 10/10/2016.
 */
public class Utils {

    public static void playSound(String audioUrl, boolean repeat) {

        File soundFile = new File(audioUrl);
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
            if(repeat) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
            else {
                clip.loop(0);
            }
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void playMoveSound() {
        playSound("music/Move.wav", false);
    }
    public static BufferedImage getImage(String link) {
        BufferedImage res = null;
        try {
            res = ImageIO.read(new File("images/" + link));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static Scanner getScanner(String link) {
        Scanner res = null;
        try {
            res = new Scanner(new File(link));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static boolean insideMap(int column,int row) {
        int column0 = 0, row0 = 0;
        int width = GameConfig.MAP_TILE_SIZE, height = GameConfig.MAP_TILE_SIZE;
        return (column>column0 && row>row0 && column<=column0+width && row<=row0+height);
    }

    public static boolean hasWall(int x1,int y1,int x2,int y2) {
        if (!insideMap(x2,y2)) return true;
        if (x1==x2) {
            if (y1<y2) return PlayGameScreen.wallDown[x1][y1];
            else return PlayGameScreen.wallDown[x2][y2];
        }
        if (y1==y2) {
            if (x1<x2) return PlayGameScreen.wallRight[x1][y1];
            else return PlayGameScreen.wallRight[x2][y2];
        }
        return true;
    }

    public static boolean canMoveTo(int column1,int row1,int column2,int row2) {
        if (column2==PlayGameScreen.exitX && row2==PlayGameScreen.exitY) return true;
        return !hasWall(column1,row1,column2,row2) && insideMap(column2,row2);
    }

    public static int manDistance(int x1,int y1,int x2,int y2) {
        return Math.abs(x1-x2) + Math.abs(y1-y2);
    }
}