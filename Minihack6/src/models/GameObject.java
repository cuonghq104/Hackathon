package models;

import main.GameConfig;

import java.io.Serializable;

/**
 * Created by Le Huy Duc on 19/10/2016.
 */
public class GameObject implements Serializable {
    public static final int TILE_LENGTH = GameConfig.TILE_LENGTH;
    protected int x = 0, y = 0;
    protected int row = 0, column = 0;
    protected int width = 0, height = 0, cornerX = 0, cornerY = 0;
    protected int health = 1;
    protected boolean isAlive = true;

    protected int powerLevel = 0;

    public int getX() {return x;}

    public int getY() {return y;}

    public int getCornerX() {return cornerX;}

    public int getCornerY() {return cornerY;}

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getPowerLevel() {return powerLevel;}

    public int getHealth() {return health;}

    public boolean isAlive() {return isAlive;}

    public void setX(int v) {x = v; cornerX = x - width/2;}

    public void setY(int v) {y = v; cornerY = y - height/2;}

    public void setWidth(int width) {
        this.width = width;
        this.cornerX = x - width/2;
    }

    public void setHeight(int height) {
        this.height = height;
        this.cornerY = y - height/2;
    }

    public void setColumn(int v) {
        int sql = GameConfig.TILE_LENGTH;
        column = v;
        setX(column * sql + sql/2);
    }

    public void setRow(int v) {
        int sql = GameConfig.TILE_LENGTH;
        row = v;
        setY(row * sql + sql/2);
    }

    public void setPowerLevel(int v) {powerLevel = v;}

    public void setHealth(int v) {health = v; if (health<=0) isAlive = false;}

    public void takeDamage(int v) {setHealth(health-v);}

    public void setIsAlive(boolean v) {isAlive = v;}

    public void move(int dx,int dy) {
        column += dx;
        row += dy;
    }

    public GameObject() {

    }


    public GameObject(int column, int row,int width,int height) {
        setColumn(column);
        setRow(row);
        width = GameConfig.TILE_LENGTH;
        height = GameConfig.TILE_LENGTH;
        setWidth(width);
        setHeight(height);
    }
}
