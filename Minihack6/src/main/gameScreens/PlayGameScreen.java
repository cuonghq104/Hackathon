package main.gameScreens;

import GameButtons.GameButton;
import controllers.ControllerController;
import controllers.PlayerController;
import controllers.SaveController;
import controllers.SingleController;
import controllers.mummies.EnemyControllerManager;
import main.GameConfig;
import main.GameMap;
import main.LevelManager;
import utilities.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by Le Huy Duc on 26/10/2016.
 */
public class PlayGameScreen extends GameScreen implements Serializable {

    public static boolean[][] wallDown = new boolean[30][30];
    public static boolean[][] wallRight = new boolean[30][30];
    public static boolean playerTurn = true;
    public static int MAP_LEFT = 154, MAP_TOP = 20;

    private LevelManager levelManager;
    private Image backdrop = null;
    public static int exitX = 0, exitY = 0; // chứa tọa độ trên bản đồ game
    public static int screenExitX = 0, screenExitY = 0; // TỌA DỘ EXIT TRÊN MÀN HÌNH
    public static Image exitImage;
    public boolean pausing = false;


    public PlayGameScreen(ScreenManager screenManager) {
        super(screenManager);
        backdrop = Utils.getImage("backdrop.jpg");
        levelManager = new LevelManager();
    }

    @Override
    public void init() {
        levelManager = new LevelManager();
    }

    public void drawExit(Graphics g) {
        int x,y, sql = GameConfig.TILE_LENGTH;
        x = MAP_LEFT + exitX * sql;
        y = MAP_TOP + exitY * sql;
        g.drawImage(exitImage,x,y,sql,sql,null);
    }

    @Override
    public void update(Graphics g) {
        g.drawImage(backdrop,0,0,GameConfig.BACKGROUND_WIDTH,GameConfig.BACKGROUND_HEIGHT,null);

        BufferedImage gameMap = new BufferedImage(GameConfig.MAP_SIZE, GameConfig.MAP_SIZE,
                BufferedImage.TYPE_INT_ARGB); // transparent

        Graphics gameMapGraphics = gameMap.getGraphics();
        GameMap.instance.draw(gameMapGraphics);
        drawExit(g);
        ControllerController.instance.draw(gameMapGraphics);

        if (pausing) PauseGameScreen.instance.update(gameMapGraphics);

        g.drawImage(gameMap,MAP_LEFT,MAP_TOP,GameConfig.MAP_SIZE,GameConfig.MAP_SIZE,null);
    }

    @Override
    public void run() {
        if (levelManager.isVictory()) {
            this.screenManager.change(new GameOverScreen(screenManager), false);
        }
        if (levelManager==null) init();
        if (pausing) return;
        levelManager.run();

        ControllerController.instance.run();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    long lastUndo = 0, lastSave = 0, lastLoad = 0, lastPause = 0;
    @Override
    public void keyPressed(KeyEvent e) {
        PlayerController.instance.keyInputListener.keyPressed(e);
        if (e.getKeyCode() == KeyEvent.VK_C) {
            levelManager.moveToNextLevel();
        }
        if (e.getKeyCode() == KeyEvent.VK_B && !PlayerController.instance.isMoving && LevelManager.instance.isFinishMoving()) {
            long now = System.currentTimeMillis();
            if (now - lastUndo >= 500) {
                levelManager.undo();
                lastUndo = now;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_F8) {
            if (PlayerController.instance.isMoving) return;
            for (int i=0;i<EnemyControllerManager.instance.size();i++) {
                SingleController singleController = EnemyControllerManager.instance.get(i);
                if (singleController.isMoving) return;
            }

            long now = System.currentTimeMillis();
            if (now - lastSave <= 1000) return;
            SaveController.instance.saveGame();
            lastSave = now;
        }
        if (e.getKeyCode() == KeyEvent.VK_F9) {
            if (PlayerController.instance.isMoving) return;
            for (int i=0;i<EnemyControllerManager.instance.size();i++) {
                SingleController singleController = EnemyControllerManager.instance.get(i);
                if (singleController.isMoving) return;
            }
            long now = System.currentTimeMillis();
            if (now - lastLoad <= 1000) return;
            lastLoad = now;
            SaveController.instance.loadGame();
            System.out.println("bb");
        }

        if (e.getKeyCode()==KeyEvent.VK_P) {
            long now = System.currentTimeMillis();
            if (now - lastPause < 500) return;
            lastPause = now;
            pausing = !pausing;
        }

//        if (e.getKeyCode() == KeyEvent.VK_A) {
//            levelManager.restartGame();
//        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        PlayerController.instance.keyInputListener.keyReleased(e);
    }

    long lastRestart = 0;
    @Override
    public void mouseClicked(MouseEvent e) {
//        if (e.getX())
//        System.out.println(e.getX() + " " + e.getY());
//        if (e.getPoint())
//        System.out.println(e.getX());
        if (GameButton.resetMazeButton.isClick(e.getX(), e.getY())) {
            long now = System.currentTimeMillis();
            if (now - lastRestart >= 700) {
                levelManager.resetMaze();
                lastRestart = now;
            }
        }
        if (GameButton.resetWorldButton.isClick(e.getX(), e.getY())) {
            levelManager.restartGame();
        }
        if (GameButton.undoButton.isClick(e.getX(), e.getY()) && !PlayerController.instance.isMoving && LevelManager.instance.isFinishMoving() ) {
            levelManager.undo();
        }
        if (GameButton.exitGameButton.isClick(e.getX(), e.getY())) {
            this.screenManager.change(new MenuGameScreen(screenManager), false);
        }
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
