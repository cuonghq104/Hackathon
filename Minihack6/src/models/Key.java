package models;

import main.GameConfig;

/**
 * Created by Le Huy Duc on 29/10/2016.
 */
public class Key extends GameObject {

    public static final int SIZE = GameConfig.TILE_LENGTH;

    public Key(int column, int row) {
        super(column, row, SIZE, SIZE);
    }

}
