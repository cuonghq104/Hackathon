package models;

/**
 * Created by Le Huy Duc on 20/10/2016.
 */
public class Mummy extends GameUnit {

    private static int WIDTH = 50, HEIGHT = 50;

    private Mummy(int column, int row) {
        super(row,column,WIDTH,HEIGHT);
    }

    public static Mummy create(int column,int row,String type) {
        return new Mummy(column,row);
    }
}
