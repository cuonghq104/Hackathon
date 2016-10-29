package controllers;

/**
 * Created by Le Huy Duc on 21/10/2016.
 */
public class WallControllerManager extends ControllerManager {

    private WallControllerManager() {
        super();
    }

    public WallController get(int i) {
        return (WallController)singleControllers.get(i);
    }

    public static final WallControllerManager instance = new WallControllerManager();
}
