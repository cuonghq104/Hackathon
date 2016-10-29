package controllers.traps;

import controllers.*;
import models.GameObject;
import models.Key;
import models.Player;

import java.awt.*;

/**
 * Created by Le Huy Duc on 29/10/2016.
 */
public class KeyController extends TrapController implements Colliable {

    BlackWallController blackWallController;
    private int lastPlayerColumn;
    private int lastPlayerRow;

    public KeyController(int column, int row, int wall_column, int wall_row, WallType wallType) {
        super(column,row);
        blackWallController = new BlackWallController(wall_column, wall_row, wallType);
        gameObject = new Key(column,row);
        gameView.setImage("plane2.png");
        gameObject.setPowerLevel(1000000);
        gameObject.setHealth(1000000);
    }


    @Override
    public GameObject getCollisionObject() {
        return gameObject;
    }

    @Override
    public void onCollide(Colliable col) {
        if (col instanceof PlayerController) {
            if (PlayerController.instance.getColumn()==PlayerController.instance.lastColumn &&
                    PlayerController.instance.getRow()== PlayerController.instance.lastRow) return;
            if (PlayerController.instance.isMoving) return;
            changeWallStatus();
        }
    }

    public void changeWallStatus() {
        blackWallController.changeWallStatus();
    }

    @Override
    public void run() {

    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }
}
