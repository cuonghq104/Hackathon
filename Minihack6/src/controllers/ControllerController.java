package controllers;

import controllers.traps.TrapController;
import controllers.traps.TrapControllerManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

/**
 * Created by Le Huy Duc on 23/10/2016.
 */
public class ControllerController {

    private ControllerController() {

    }

    public void init() {
        EnemyControllerManager.instance.clear();
        WallControllerManager.instance.clear();
        CollisionManager.instance.clear();
        TrapControllerManager.instance.clear();
        PlayerController.instance.setX(-1);
        PlayerController.instance.setY(-1);
        PlayerController.instance.init();
    }

    public synchronized void draw(Graphics g) {
        PlayerController.instance.draw(g);
        WallControllerManager.instance.draw(g);
        TrapControllerManager.instance.draw(g);
        EnemyControllerManager.instance.draw(g);
    }

    public synchronized void run() {
        WallControllerManager.instance.run();
        TrapControllerManager.instance.run();
        PlayerController.instance.run();
        CollisionManager.instance.run();
        EnemyControllerManager.instance.run();
    }

    public static final ControllerController instance = new ControllerController();
}
