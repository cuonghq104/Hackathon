package main;

import controllers.*;
import controllers.mummies.*;
import javafx.stage.Screen;
import main.gameScreens.GameScreen;
import main.gameScreens.PlayGameScreen;
import main.gameScreens.ScreenManager;
import utilities.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Created by Le Huy Duc on 29/10/2016.
 */
public class EditorGameScreen extends GameScreen {

    private Image background = null;
    private int MAP_LEFT = PlayGameScreen.MAP_LEFT, MAP_TOP = PlayGameScreen.MAP_TOP;
    public int currentColumn, currentRow;
    private int exitX, exitY, nWallDown = 0, nWallRight = 0, nMummy = 0;
    private boolean isRunning = false;

    public EditorGameScreen(ScreenManager screenManager) {
        super(screenManager);
        init();
    }

    @Override
    public void init() {
        background = Utils.getImage("backdrop.jpg");
        ControllerController.instance.init();
        nWallDown = 0;
        nWallRight = 0;
        nMummy = 0;
    }

    @Override
    public void update(Graphics g) {
        g.drawImage(background,0,0,GameConfig.BACKGROUND_WIDTH,GameConfig.BACKGROUND_HEIGHT,null);

        BufferedImage gameMap = new BufferedImage(GameConfig.MAP_SIZE, GameConfig.MAP_SIZE,
                BufferedImage.TYPE_INT_ARGB); // transparent
        Graphics gameMapGraphics = gameMap.getGraphics();
        GameMap.instance.draw(gameMapGraphics);
        ControllerController.instance.draw(gameMapGraphics);
        drawExit(g);

        g.drawImage(gameMap,MAP_LEFT,MAP_TOP,GameConfig.MAP_SIZE,GameConfig.MAP_SIZE,null);
    }

    @Override
    public void run() {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    private void resetPoint() {
        currentRow = 0;
        currentColumn = 0;
    }

    public void drawExit(Graphics g) {
        int x,y, sql = GameConfig.TILE_LENGTH;
        x = MAP_LEFT + exitX * sql;
        y = MAP_TOP + exitY * sql;
        g.drawImage(PlayGameScreen.exitImage,x,y,sql,sql,null);
    }

    public void createExit() {
        PlayGameScreen.exitX = exitX;
        PlayGameScreen.exitY = exitY;

        int x = 0,y = 0;
        if (exitY==0) x = 0*55 + 1;
        if (exitX== GameConfig.MAP_TILE_SIZE +1) x = 1*55 + 2;
        if (exitY== GameConfig.MAP_TILE_SIZE +1) x = 2*55 + 3;
        if (exitX==0) x = 3*55 + 4;

        BufferedImage exitSprite = Utils.getImage("stairs6.gif");
        PlayGameScreen.exitImage = exitSprite.getSubimage(x,y,55,55);
    }

    public void createLevel() {
        GameWindow.editing = true;

        PrintWriter output = null;
        try {
            output = new PrintWriter(new File("map/0.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        output.println(6 + " " + 6);
        output.print(PlayerController.instance.getColumn() + " " + PlayerController.instance.getRow() + " ");
        output.println(exitX + " " + exitY);

        output.println(nWallDown);
        for (int i=0;i<WallControllerManager.instance.size();i++) {
            WallController wallController = WallControllerManager.instance.get(i);
            if (wallController.wallType==WallType.RIGHT) continue;
            output.println(wallController.getColumn() + " " + wallController.getRow());
        }

        output.println(nWallRight);
        for (int i=0;i<WallControllerManager.instance.size();i++) {
            WallController wallController = WallControllerManager.instance.get(i);
            if (wallController.wallType==WallType.DOWN) continue;
            output.println(wallController.getColumn() + " " + wallController.getRow());
        }

        output.println(nMummy);
        for (int i=0;i< EnemyControllerManager.instance.size();i++) {
            EnemyController enemyController = EnemyControllerManager.instance.get(i);
            String type = "";
            if (enemyController instanceof EnemyControllerWhite) type = "WHITE";
            if (enemyController instanceof EnemyControllerRed) type = "RED";
            if (enemyController instanceof EnemyControllerScorpion) type = "SCORPION";

            output.println(enemyController.getColumn() + " " + enemyController.getRow() + " " + type);
        }

        output.println(0);
        output.println(0);
        output.println(0);
        output.close();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_0) {resetPoint(); return;}

        if (e.getKeyCode()==KeyEvent.VK_1 && currentColumn!=0) {
            PlayerController.instance.setColumn(currentColumn);
            PlayerController.instance.setRow(currentRow);
        }

        if (e.getKeyCode()==KeyEvent.VK_2) {
            this.exitX = currentColumn;
            this.exitY = currentRow;
            createExit();
        }

        if (e.getKeyCode()==KeyEvent.VK_3 && currentColumn!=0) {
            EnemyControllerManager.instance.add(EnemyControllerWhite.create(currentColumn,currentRow));
            nMummy++;
            resetPoint(); return;
        }

        if (e.getKeyCode()==KeyEvent.VK_4 && currentColumn!=0) {
            EnemyControllerManager.instance.add(EnemyControllerRed.create(currentColumn,currentRow));
            nMummy++;
            resetPoint(); return;
        }

        if (e.getKeyCode()==KeyEvent.VK_5 && currentColumn!=0) {
            EnemyControllerManager.instance.add(EnemyControllerScorpion.create(currentColumn,currentRow));
            nMummy++;
            resetPoint(); return;
        }

        if (e.getKeyCode()==KeyEvent.VK_6 && currentColumn!=0) {
            WallControllerManager.instance.add(WallController.create(currentColumn,currentRow, WallType.DOWN));
            nWallDown++;
            resetPoint(); return;
        }

        if (e.getKeyCode()==KeyEvent.VK_7 && currentColumn!=0) {
            WallControllerManager.instance.add(WallController.create(currentColumn,currentRow, WallType.RIGHT));
            nWallRight++;
            resetPoint(); return;
        }

        if (e.getKeyCode()==KeyEvent.VK_R) {
            createLevel();
            GameWindow.editing = true;
            this.screenManager.change(new PlayGameScreen(screenManager),false);
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
        Point location = e.getLocationOnScreen();
        if (location.x < MAP_LEFT || location.y < MAP_TOP ||
                location.x > MAP_LEFT + GameConfig.MAP_SIZE || location.y > MAP_TOP + GameConfig.MAP_SIZE) {
            resetPoint();
            return;
        }
        currentColumn = (location.x - MAP_LEFT) / GameConfig.TILE_LENGTH;
        currentRow = (location.y - MAP_TOP) / GameConfig.TILE_LENGTH;
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
