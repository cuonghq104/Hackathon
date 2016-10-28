package controllers;

import main.gameScreens.PlayGameScreen;

/**
 * Created by Le Huy Duc on 21/10/2016.
 */
public class EnemyControllerManager extends ControllerManager {

    private EnemyControllerManager() {
        super();
    }
    private boolean inited = false;

    public boolean finished() {
        for (SingleController singleController : singleControllers)
            if (!singleController.finished()) return false;
        return true;
    }

    public void run() {
        if (!PlayerController.instance.finished()) return;
        if (!inited) {
            for (SingleController singleController : singleControllers)
                singleController.init();
            inited = true;
        }

        for (SingleController singleController : singleControllers)
            singleController.run();
        remove();

        if (finished()) {
            PlayGameScreen.playerTurn = true;
            inited = false;
        }
    }

    public static EnemyControllerManager instance = new EnemyControllerManager();
}
