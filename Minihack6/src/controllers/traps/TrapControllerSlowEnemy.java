package controllers.traps;

import controllers.Colliable;
import controllers.mummies.EnemyController;
import models.GameObject;
import models.Trap;
import views.ImageView;

/**
 * Created by Cuong on 10/29/2016.
 */
public class TrapControllerSlowEnemy extends TrapController {

    private int stepLimit = 1;
    private int slowDuration = 3;

    private TrapControllerSlowEnemy(int column,int row) {
        super(column,row);
        gameObject = new Trap(column,row);
        gameView = new ImageView("trap6.gif");
        gameObject.setPowerLevel(100000);
        gameObject.setPowerLevel(100000);
        gameObject.setHealth(1000000);
    }

    public static TrapControllerSlowEnemy create(int column,int row) {
        return new TrapControllerSlowEnemy(column,row);
    }

    //**********  COLLISION ******************************************************************
    @Override
    public GameObject getCollisionObject() {
        return gameObject;
    }

    static int cnt = 0;
    @Override
    public void onCollide(Colliable col) {
        if (col instanceof EnemyController) {
            if (((EnemyController) col).isMoving) return;
            ((EnemyController)col).setMaxMoveStep(stepLimit);
            ((EnemyController)col).setSlowDuration(slowDuration);
        }
    }

}
