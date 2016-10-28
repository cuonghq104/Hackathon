package models;

import main.GameConfig;

/**
 * Created by Le Huy Duc on 26/10/2016.
 */
public class Trap extends GameObject {

    public static int SIZE = GameConfig.TILE_LENGTH * 3/4;

    public Trap(int column,int row) {
        super(column,row,SIZE,SIZE);
    }
}
