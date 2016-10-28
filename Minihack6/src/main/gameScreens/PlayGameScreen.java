package main.gameScreens;

import GameButtons.GameButton;
import controllers.ControllerController;
import controllers.PlayerController;
import main.GameConfig;
import main.GameMap;
import main.LevelManager;
import utilities.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * Created by Le Huy Duc on 26/10/2016.
 */
public class PlayGameScreen extends GameScreen {

    public static boolean[][] wallDown = new boolean[30][30];
    public static boolean[][] wallRight = new boolean[30][30];
    public static boolean playerTurn = true;
    public static int MAP_LEFT = 154, MAP_TOP = 20;

    private LevelManager levelManager;
    private Image backdrop = null;
    public static int exitX = 0, exitY = 0; // chứa tọa độ trên bản đồ game
    public static int screenExitX = 0, screenExitY = 0; // TỌA DỘ EXIT TRÊN MÀN HÌNH
    public static Image exitImage;


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

        g.drawImage(gameMap,MAP_LEFT,MAP_TOP,GameConfig.MAP_SIZE,GameConfig.MAP_SIZE,null);
    }

    @Override
    public void run() {
        if (levelManager.isVictory()) {
            this.screenManager.change(new GameOverScreen(screenManager), false);
        }
        if (levelManager==null) init();
        levelManager.run();
        ControllerController.instance.run();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        PlayerController.instance.keyInputListener.keyPressed(e);

    }

    @Override
    public void keyReleased(KeyEvent e) {
        PlayerController.instance.keyInputListener.keyReleased(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        if (e.getX())
//        System.out.println(e.getX() + " " + e.getY());
//        if (e.getPoint())
//        System.out.println(e.getX());
        if (GameButton.resetMazeButton.isClick(e.getX(), e.getY()) && !PlayerController.instance.isMoving && LevelManager.instance.isFinishMoving() ) {
            levelManager.resetMaze();
        }
        if (GameButton.resetWorldButton.isClick(e.getX(), e.getY()) && !PlayerController.instance.isMoving && LevelManager.instance.isFinishMoving() ) {
            levelManager.restartGame();
        }
        if (GameButton.undoButton.isClick(e.getX(), e.getY()) && !PlayerController.instance.isMoving && LevelManager.instance.isFinishMoving() ) {
            levelManager.undo();
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
