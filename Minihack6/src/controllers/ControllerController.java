package controllers;

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
        PlayerController.instance.setX(-1);
        PlayerController.instance.setY(-1);
        PlayerController.instance.init();
    }

    public void draw(Graphics g) {
//        BufferedImage map = new BufferedImage(360,360,BufferedImage.TYPE_INT_RGB);
//        Graphics mapGraphics = map.getGraphics();
        PlayerController.instance.draw(g);
        EnemyControllerManager.instance.draw(g);
        WallControllerManager.instance.draw(g);
        //g.drawImage(map,60,60,360,360,null);
    }

    public void run() {
        PlayerController.instance.run();
        EnemyControllerManager.instance.run();
        CollisionManager.instance.run();
        WallControllerManager.instance.run();
    }

    public static final ControllerController instance = new ControllerController();
}
