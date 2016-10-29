package views;

import models.GameObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

/**
 * Created by Le Huy Duc on 23/10/2016.
 */
public class AnimationView implements GameView {

    public int sizeX = 60, sizeY = 60;
    public long firstImage = 0;
    public BufferedImage bigImage;
    public Image[] sheet = new Image[7];
    public int nImage, currentImage = 0;

    public void setImage(String link) {

    }

    public void setImage(Image im) {

    }

    public Image getImage() {
        return null;
    }

    public void setSheet(String link, int n) {
        try {
            bigImage = ImageIO.read(new File("images/" + link));
        } catch (IOException e) {
            e.printStackTrace();
        }

        nImage = n;
        currentImage = 0;
        for (int i=0;i<n;i++) sheet[i] = bigImage.getSubimage(i*sizeX,0,sizeX,sizeY);
    }

    public void drawImage(Graphics g, GameObject go) {
        if (!go.isAlive()) return;
        currentImage %= nImage;
        g.drawImage(sheet[currentImage],go.getCornerX(),go.getCornerY(),go.getWidth(),go.getHeight(),null);
    }

    public void drawImage(Graphics g, GameObject go, boolean stayingStillAlwaysTrue) {
        if (!go.isAlive()) return;
        g.drawImage(sheet[0],go.getCornerX(),go.getCornerY(),go.getWidth(),go.getHeight(),null);
    }

    public void run() {

    }
}
