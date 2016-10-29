package controllers.mummies;

import controllers.ControllerManager;
import controllers.PlayerController;
import controllers.SingleController;
import main.gameScreens.PlayGameScreen;

import java.io.Serializable;

/**
 * Created by Le Huy Duc on 21/10/2016.
 */
public class EnemyControllerManager extends ControllerManager implements Serializable{

    public EnemyControllerManager() {
        super();
    }
    private boolean inited = false;

    private boolean finished() {
        for (SingleController singleController : singleControllers)
            if (!singleController.finished()) return false;
        return true;
    }

    public EnemyController get(int i) {
        return (EnemyController)singleControllers.get(i);
    }

    public synchronized void run() {
        if (!PlayerController.instance.finished()) return;
        synchronized (singleControllers) {
            if (!inited) {
                for (SingleController singleController : singleControllers)
                    singleController.init();
                inited = true;
            }

            for (SingleController singleController : singleControllers)
                singleController.run();
            // remove();

            if (finished()) {
                PlayGameScreen.playerTurn = true;
                inited = false;
            }
        }
    }

    public static EnemyControllerManager instance = new EnemyControllerManager();
}
