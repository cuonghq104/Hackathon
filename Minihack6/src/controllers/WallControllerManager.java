package controllers;

import java.io.Serializable;

/**
 * Created by Le Huy Duc on 21/10/2016.
 */
public class WallControllerManager extends ControllerManager implements Serializable{

    public WallControllerManager() {
        super();
    }

    public WallController get(int i) {
        return (WallController)singleControllers.get(i);
    }

    public static WallControllerManager instance = new WallControllerManager();
}
