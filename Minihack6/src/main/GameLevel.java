package main;

import controllers.*;
import controllers.traps.*;
import main.gameScreens.PlayGameScreen;
import models.Player;
import utilities.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Le Huy Duc on 21/10/2016.
 */
public class GameLevel {

    protected int MAP_TILE_SIZE = 6;
    protected Point[] wallDowns;
    protected boolean[][] wallDown;
    protected Point[] wallRights;
    protected boolean[][] wallRight;
    protected Image[][] backgroundTile;
    protected EnemyController[] mummyControllers = new EnemyController[20];
    protected TrapController[] trapControllers = new TrapController[20];
    public BlackWallController myBlackWall = new BlackWallController(0,0,WallType.DOWN);

    public static final int[] dx = {0,0,0,1,-1,-1,1,-1,1};
    public static final int[] dy = {0,1,-1,0,0,-1,-1,1,1};

    private int exitX = -124443, exitY = 123123;
    private Scanner input;

    private int numberOfEnemy;

    public GameLevel() {
        wallDowns = new Point[20];
        wallDown = PlayGameScreen.wallDown;
        wallRights = new Point[20];
        wallRight = PlayGameScreen.wallRight;
        backgroundTile = GameMap.backgroundTile;
    }

    public void undo() {
        if (!PlayerController.instance.finished()) return;
        for (int i = 1; i <= numberOfEnemy; i++) {
            if (mummyControllers[i].isMoving) return;
        }

        PlayerController.instance.undo();
        for (int i = 1; i <= numberOfEnemy; i++) {
            mummyControllers[i].undo();
        }

        for (int i=0;i<WallControllerManager.instance.size();i++) {
            WallController myWall = WallControllerManager.instance.get(i);
            if (myWall instanceof BlackWallController) myWall.undo();
        }
    }
    private void levelSetting() {
        MAP_TILE_SIZE = input.nextInt();
        MAP_TILE_SIZE = input.nextInt();
        GameConfig.MAP_TILE_SIZE = MAP_TILE_SIZE;
        GameConfig.MAP_TILE_SIZE = MAP_TILE_SIZE;
        GameConfig.TILE_LENGTH = 360 / MAP_TILE_SIZE;
    }

    private synchronized void createPlayer() {
        int x,y;
        x = input.nextInt();
        y = input.nextInt();
        PlayerController.instance.setColumn(x);
        PlayerController.instance.setRow(y);
        PlayerController.instance.setIsAlive(true);
        PlayerController.instance.setHealth(1);
        PlayGameScreen.playerTurn = true;
    }

    private void createExit() {
        exitX = input.nextInt();
        exitY = input.nextInt();
        PlayGameScreen.exitX = exitX;
        PlayGameScreen.exitY = exitY;

        int x = 0,y = 0;
        if (exitY==0) x = 0*55 + 1;
        if (exitX== MAP_TILE_SIZE +1) x = 1*55 + 2;
        if (exitY== MAP_TILE_SIZE +1) x = 2*55 + 3;
        if (exitX==0) x = 3*55 + 4;

        BufferedImage exitSprite = Utils.getImage("stairs6.gif");
        PlayGameScreen.exitImage = exitSprite.getSubimage(x,y,55,55);
    }

    private void createWallDown() {
        int n, column, row;

        n = input.nextInt();
        for (int i=1;i<=n;i++) {
            column = input.nextInt();
            row = input.nextInt();
            wallDowns[i] = new Point(column,row);
        }

        for (int i=1;i<=n;i++) {
            int x = wallDowns[i].x;
            int y = wallDowns[i].y;
            wallDown[x][y] = true;
            WallControllerManager.instance.add(WallController.create(x,y, WallType.DOWN));
        }
    }

    private void createWallRight() {
        int n, column, row;

        n = input.nextInt();
        for (int i=1;i<=n;i++) {
            column = input.nextInt();
            row = input.nextInt();
            wallRights[i] = new Point(column,row);
        }

        for (int i=1;i<=n;i++) {
            int x = wallRights[i].x;
            int y = wallRights[i].y;
            wallRight[x][y] = true;
            WallControllerManager.instance.add(WallController.create(x,y, WallType.RIGHT));
        }
    }

    private synchronized void createMummy() {
        int n, x, y;
        String type;

        numberOfEnemy = input.nextInt();
        for (int i=1;i<=numberOfEnemy;i++) {
            x = input.nextInt();
            y = input.nextInt();
            type = input.next();
            if (MummyType.valueOf(type)==MummyType.WHITE)
                mummyControllers[i] = EnemyControllerWhite.create(x,y);
            if (MummyType.valueOf(type)==MummyType.RED)
                mummyControllers[i] = EnemyControllerRed.create(x,y);
            if (MummyType.valueOf(type)==MummyType.SCORPION)
                mummyControllers[i] = EnemyControllerScorpion.create(x,y);
        }

        for (int i=1;i<=numberOfEnemy;i++) {
            EnemyController mummy = mummyControllers[i];
            mummy.setColumn(mummy.getColumn());
            mummy.setRow(mummy.getRow());
            EnemyControllerManager.instance.add(mummy);
        }
    }

    public void createTrap() {
        int n, x, y;
        String type;

        n = input.nextInt();
        for (int i=1;i<=n;i++) {
            x = input.nextInt();
            y = input.nextInt();
            type = input.next();

            if (TrapType.valueOf(type)==TrapType.SLOW_ENEMY) {
                trapControllers[i] = TrapControllerSlowEnemy.create(x,y);
            }
        }

        for (int i=1;i<=n;i++) {
            TrapController trap = trapControllers[i];
            trap.setColumn(trap.getColumn());
            trap.setRow(trap.getRow());
            TrapControllerManager.instance.add(trap);
        }
    }

    public void createKey() {
        int n, x, y, wallX, wallY;
        String type;
        n = input.nextInt();
        x = input.nextInt();
        y = input.nextInt();
        wallX = input.nextInt();
        wallY = input.nextInt();
        type = input.next();

        TrapController key = new KeyController(x,y,wallX,wallY,WallType.valueOf(type));
        TrapControllerManager.instance.add(key);
    }

    public void createLevel(int level) {
        ControllerController.instance.init();
        try {
            this.input = new Scanner(new File("map/" + level + ".txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        GameMap.instance.init();
        levelSetting();
        createPlayer();
        createExit();
        createWallDown();
        createWallRight();
        createMummy();
        createTrap();
        createKey();
    }


    public boolean isFinishMoving() {
        for (int i = 1; i <= numberOfEnemy; i++) {
            if (mummyControllers[i].isMoving)
                return false;
        }
        return true;
    }

    public boolean hasLose() {
        return PlayerController.instance.getHealth() <= 0;
    }

    public boolean hasWon() {
        if (PlayerController.instance.getColumn() == PlayGameScreen.exitX &&
                PlayerController.instance.getRow() == PlayGameScreen.exitY) return true;
        return false;
    }

    public void draw() {

    }

    void run() {
        
    }

    public static final GameLevel instance = new GameLevel();
}
