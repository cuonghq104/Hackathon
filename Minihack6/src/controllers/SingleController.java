package controllers;

import controllers.movement.MoveType;
import models.GameObject;
import views.GameView;

import java.awt.*;
import java.util.Stack;

/**
 * Created by Le Huy Duc on 19/10/2016.
 */
public class SingleController implements BaseController {

    protected GameObject gameObject;
    protected GameView gameView;
    public static final int[] dx = {0,0,0,1,-1,-1,1,-1,1};
    public static final int[] dy = {0,1,-1,0,0,-1,-1,1,1};
    public static final MoveType[] moveTypes = {MoveType.STAY,MoveType.DOWN,MoveType.UP,MoveType.RIGHT,MoveType.LEFT};

    public boolean isMoving = false;
    public boolean isFighting = false;

    protected Stack<UnitState> backState;
    protected boolean firstMoveAfterPlayer = false;



    //**********  GETTER  ******************************************************************
    public GameObject getGameObject() {return gameObject;}

    public GameView getGameView() {return gameView;}

    public int getX() {return gameObject.getX();}

    public int getY() {return gameObject.getY();}

    public int getCornerX() {return gameObject.getCornerX();}

    public int getCornerY() {return gameObject.getCornerY();}

    public int getRow() {
        return gameObject.getRow();
    }

    public int getColumn() {
        return gameObject.getColumn();
    }

    public int getWidth() {
        return gameObject.getWidth();
    }

    public int getHeight() {
        return gameObject.getHeight();
    }

    public int getHealth() {
        return gameObject.getHealth();
    }

    public boolean isAlive() {
        return gameObject.isAlive();
    }


    //**********  SETTER ******************************************************************
    public void setX(int v) {gameObject.setX(v);}

    public void setY(int v) {gameObject.setY(v);}

    public void setWidth(int width) {
        gameObject.setWidth(width);
    }

    public void setHeight(int height) {
        gameObject.setHeight(height);
    }

    public void setRow(int row) {
        gameObject.setRow(row);
    }

    public void setColumn(int column) {
        gameObject.setColumn(column);
    }

    public void setHealth(int v) {gameObject.setHealth(v);}

    public void setIsAlive(boolean v) {gameObject.setIsAlive(v);}


    //**********  CONSTRUCTOR ******************************************************************
    public SingleController() {
        initStack();
    }

    public void init() {

    }

    public boolean finished() {
        return !isMoving && !isFighting;
    }

    public boolean deleteNow() {
        return (!gameObject.isAlive());
    }

    public void move(GameObject go) {

    }


    public void draw(Graphics g) {
        gameView.drawImage(g,gameObject);
    }

    public void run() {

    }


    //**********  UNDO  ******************************************************************

    public void initStack() {
        backState = new Stack<>();
    }

    public void addCurrentState() {
        backState.push(new UnitState(this));
    }

    public void undo() {
        if (backState.size()==0) return;
        UnitState previousState = backState.pop();
        this.setColumn(previousState.column);
        this.setRow(previousState.row);
        this.setHealth(previousState.health);
        this.setIsAlive(previousState.isAlive);
    }

}
