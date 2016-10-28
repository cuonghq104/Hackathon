package controllers;

import models.GameObject;
import models.Player;
import models.Trap;
import views.ImageView;

/**
 * Created by Le Huy Duc on 26/10/2016.
 */
public class TrapController extends SingleController implements Colliable {

    public TrapController(int column, int row) {
        super();
        gameObject = new Trap(column,row);
        gameView = new ImageView("trap6.gif");
        gameObject.setPowerLevel(0);
        gameObject.setHealth(1000000);
    }

    //**********  COLLISION ******************************************************************
    @Override
    public GameObject getCollisionObject() {
        return gameObject;
    }

    @Override
    public void onCollide(Colliable col) {
        if (col instanceof PlayerController) {
            GameObject go = col.getCollisionObject();
            go.takeDamage(1000);
        }
    }
}
