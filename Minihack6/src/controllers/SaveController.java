package controllers;

import com.sun.org.glassfish.gmbal.GmbalException;
import controllers.mummies.*;
import controllers.traps.KeyController;
import controllers.traps.TrapController;
import controllers.traps.TrapControllerManager;
import controllers.traps.TrapControllerSlowEnemy;
import main.GameConfig;
import main.GameLevel;
import main.gameScreens.PlayGameScreen;
import models.Trap;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;

/**
 * Created by Cuong on 10/29/2016.
 */
public class SaveController {

    public EnemyControllerManager enemyControllerManager = new EnemyControllerManager();
    public WallControllerManager wallControllerManager = new WallControllerManager();
    public TrapControllerManager trapControllerManager = new TrapControllerManager();
    public CollisionManager collisionManager = new CollisionManager();
    public PlayerController playerController = new PlayerController();
    public boolean[][] wallDown = new boolean[30][30];
    public boolean[][] wallRight = new boolean[30][30];
    public int MAP_SIZE, exitX, exitY;
//
//    private void writeToFile() throws IOException {
//        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("save.txt"));
//        objectOutputStream.writeObject(enemyControllerManager);
//        objectOutputStream.writeObject(wallControllerManager);
//        objectOutputStream.writeObject(trapControllerManager);
//        objectOutputStream.writeObject(collisionManager);
//        objectOutputStream.writeObject(playerController);
//        objectOutputStream.writeObject(wallDown);
//        objectOutputStream.writeObject(wallRight);
//        objectOutputStream.close();
//    }
//
//    public void saveGame() throws IOException {
//        enemyControllerManager = EnemyControllerManager.instance;
//        wallControllerManager = WallControllerManager.instance;
//        trapControllerManager = TrapControllerManager.instance;
//        collisionManager = CollisionManager.instance;
//        playerController = PlayerController.instance;
//        wallDown = PlayGameScreen.wallDown;
//        wallRight = PlayGameScreen.wallRight;
//        writeToFile();
//    }
//
//    private void readFromFile() throws IOException, ClassNotFoundException {
//        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("save.txt"));
//        enemyControllerManager = (EnemyControllerManager) objectInputStream.readObject();
//        wallControllerManager = (WallControllerManager) objectInputStream.readObject();
//        trapControllerManager = (TrapControllerManager) objectInputStream.readObject();
//        collisionManager = (CollisionManager) objectInputStream.readObject();
//        playerController = (PlayerController) objectInputStream.readObject();
//        wallDown = (boolean[][]) objectInputStream.readObject();
//        wallRight = (boolean[][]) objectInputStream.readObject();
//    }
//
//    public void loadGame() throws IOException, ClassNotFoundException {
//        readFromFile();
//        EnemyControllerManager.instance = enemyControllerManager;
//        WallControllerManager.instance = wallControllerManager;
//        TrapControllerManager.instance = trapControllerManager;
//        CollisionManager.instance = collisionManager;
//        PlayerController.instance = playerController;
//        PlayGameScreen.wallDown = wallDown;
//        PlayGameScreen.wallRight = wallRight;
//    }
//

    public void saveGame() {
        enemyControllerManager = EnemyControllerManager.instance;
        wallControllerManager = WallControllerManager.instance;
        trapControllerManager = TrapControllerManager.instance;
        collisionManager = CollisionManager.instance;
        playerController = PlayerController.instance;
        wallDown = PlayGameScreen.wallDown;
        wallRight = PlayGameScreen.wallRight;

        PrintWriter output = null;
        try {
            output = new PrintWriter(new File("save.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        output.println(GameConfig.MAP_TILE_SIZE + " " + GameConfig.MAP_TILE_SIZE);
        output.println(playerController.getColumn() + " " + playerController.getRow());
        output.println(PlayGameScreen.exitX + " " + PlayGameScreen.exitY);

        int nWallDown = 0, nWallRight = 0;
        for (int i=0;i<wallControllerManager.size();i++) {
            WallController myWall = wallControllerManager.get(i);
            if (myWall.wallType==WallType.DOWN && !(myWall instanceof BlackWallController)) nWallDown++;
            if (myWall.wallType==WallType.RIGHT && !(myWall instanceof BlackWallController)) nWallRight++;
        }

        output.println(nWallDown);
        for (int i=0;i<wallControllerManager.size();i++) {
            WallController myWall = wallControllerManager.get(i);
            if (myWall.wallType==WallType.DOWN && !(myWall instanceof BlackWallController)) {
                output.println(myWall.getColumn() + " " + myWall.getRow());
            }
        }

        output.println(nWallRight);
        for (int i=0;i<wallControllerManager.size();i++) {
            WallController myWall = wallControllerManager.get(i);
            if (myWall.wallType==WallType.RIGHT && !(myWall instanceof BlackWallController)) {
                output.println(myWall.getColumn() + " " + myWall.getRow());
            }
        }

        output.println(enemyControllerManager.size());
        for (int i=0;i<enemyControllerManager.size();i++) {
            EnemyController enemyController = enemyControllerManager.get(i);
            String type = "";
            if (enemyController instanceof EnemyControllerWhite) type = "WHITE";
            if (enemyController instanceof EnemyControllerRed) type = "RED";
            if (enemyController instanceof EnemyControllerScorpion) type = "SCORPION";
            output.println(enemyController.getColumn() + " " + enemyController.getRow() + " " + type);
        }

        int nTrap=0, nKey = 0;
        for (int i=0;i<trapControllerManager.size();i++) {
            TrapController trapController = (TrapController)trapControllerManager.get(i);
            if (trapController instanceof TrapControllerSlowEnemy) nTrap++;
            if (trapController instanceof KeyController) nKey++;
        }

        output.println(nTrap);
        for (int i=0;i<trapControllerManager.size();i++) {
            TrapController trapController = (TrapController)trapControllerManager.get(i);
            if (trapController instanceof TrapControllerSlowEnemy)
                output.println(trapController.getColumn() + " " + trapController.getRow() + " " + "SLOW_ENEMY");
        }

        output.println(nKey);
        for (int i=0;i<trapControllerManager.size();i++) {
            TrapController trapController = (TrapController) trapControllerManager.get(i);
            if (trapController instanceof KeyController) {
                KeyController keyController = (KeyController)trapController;
                output.print(trapController.getColumn() + " " + trapController.getRow() + " ");
                BlackWallController blackWallController = keyController.getBlackWallController();
                output.print(blackWallController.getColumn() + " " + blackWallController.getRow() + " ");

                String type = "";
                if (blackWallController.wallType==WallType.DOWN) type = "DOWN";
                else type = "RIGHT";
                output.print(type + " ");

                type = "";
                if (blackWallController.isClose) type = "CLOSE";
                else type = "OPEN";
                output.println(type);
            }
        }

        output.close();
    }

    public void loadGame() {
        GameLevel.instance.createLevel("save.txt");
    }

    public static SaveController instance = new SaveController();
}
