package models;

import main.GameConfig;

/**
 * Created by Le Huy Duc on 19/10/2016.
 */
public class GameUnit extends GameObject {

    public static final int SIZE = GameConfig.TILE_LENGTH / 3 * 4;

    public GameUnit(int column,int row) {
        super(column,row,SIZE,SIZE);
        setColumn(column);
        setRow(row);
    }

    public GameUnit(int column,int row,int width,int height) {
        super(column,row,width,height);
    }
}
