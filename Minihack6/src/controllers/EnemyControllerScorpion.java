package controllers;

import main.GameConfig;
import main.gameScreens.PlayGameScreen;
import models.GameObject;
import utilities.Utils;

import java.awt.*;

/**
 * Created by Le Huy Duc on 23/10/2016.
 */
public class EnemyControllerScorpion extends EnemyController implements Colliable {

    private static int SIZEX = 50, SIZEY = 50;
    private int moveStep;

    private EnemyControllerScorpion(int column, int row) {
        super(column,row);
        unitName = "scorpion";
        moveDirections = 4;
        defaultMaxMoveStep = 1;
        maxMoveStep = 1;
        gameObject.setPowerLevel(1);
        gameObject.setHealth(1);
    }

    public static EnemyControllerScorpion create(int column, int row) {
        return new EnemyControllerScorpion(column,row);
    }

    public void init() {
        moveStep = maxMoveStep;
    }

    // Bọ cạp đi ngang trước dọc sau, đi 1 bước
    private Point tryDirection(int column0, int row0) {
        int column, row, sql = GameConfig.DEFAULT_TILE_LENGTH, best = 1000000;
        Point res = new Point(column0,row0);
        GameObject player = PlayerController.instance.getGameObject();

        for (int i=0;i<=moveDirections;i++) {
            column = column0 + dx[i];
            row = row0 + dy[i];
            if (Utils.manDistance(column,row,player.getColumn(),player.getRow()) <= best) {
                best = Utils.manDistance(column,row,player.getColumn(),player.getRow());
                if (Utils.canMoveTo(column0,row0,column,row)) {
                    res = new Point(column,row);
                    moveType = moveTypes[i];
                    beginPoint = new Point(gameObject.getX(),gameObject.getY());
                    targetPoint = new Point(column*sql+sql/2,row*sql+sql/2);
                    targetGrid = new Point(column,row);
                }
            }
        }
        return res;
    }

    //**********  COLLISION *********************************************************************
    @Override
    public GameObject getCollisionObject() {
        return gameObject;
    }

    @Override
    public void onCollide(Colliable col) {
        if (col instanceof EnemyController) {
            GameObject go = col.getCollisionObject();
            go.takeDamage(gameObject.getPowerLevel());
        }
        if (col instanceof PlayerController) {
            GameObject go = col.getCollisionObject();
            go.takeDamage(1000);
        }
    }

    public void move(GameObject go) {
        Point target = tryDirection(go.getColumn(),go.getRow());
        if (target.x==getColumn() && target.y==getRow()) return;
        isMoving = true;
        animationView.firstImage = System.currentTimeMillis();
    }

    public boolean finished() {
        return (!isMoving) && (!isFighting) && moveStep==0;
    }

    public void draw(Graphics g) {
        if (animationView.nImage==0) animationView.setSheet("scorpion_down.png",5);
        if (isMoving) animationView.drawImage(g,gameObject);
        else animationView.drawImage(g,gameObject,true);
    }

    public void run() {
        if (isMoving) {
            moveAnimation(); return;}
        if (PlayGameScreen.playerTurn) return;

        if (firstTime) {
            moveStep = maxMoveStep;
            firstTime = false;
            addState(currentStateWithEffect());
        }

        if (moveStep > 0) {
            checkEffect();
            moveStep--;
            move(gameObject);
        }
    }

}
