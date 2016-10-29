package views;

import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * Created by Cuong on 10/29/2016.
 */
public class MyBufferedImage extends BufferedImage implements Serializable {

    public MyBufferedImage(int width,int height, int imageType) {
        super(width,height,imageType);
    }
}
