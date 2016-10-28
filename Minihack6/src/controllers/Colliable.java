package controllers;

import models.GameObject;

/**
 * Created by Le Huy Duc on 26/10/2016.
 */
public interface Colliable {
    GameObject getCollisionObject();
    void onCollide(Colliable col);
}
