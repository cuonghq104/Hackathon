package views;

import models.GameObject;

import java.awt.*;

/**
 * Created by Le Huy Duc on 19/10/2016.
 */
public interface GameView {
    void setImage(String link);
    void setImage(Image im);
    Image getImage();
    void drawImage(Graphics g, GameObject gameObject);
    void run();
}
