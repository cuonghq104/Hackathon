package GameButtons;

import java.awt.*;

/**
 * Created by Cuong on 10/29/2016.
 */
public class GameButton {

    public Rectangle button;

    private GameButton(int x, int y, int width, int height) {
        button = new Rectangle(x, y, width, height);
//        this.button = button;
    }

    public static GameButton resetMazeButton = new GameButton(13, 190, 120, 33);

    public static GameButton resetWorldButton = new GameButton(13, 282, 120, 33);

    public static GameButton undoButton = new GameButton(13, 157, 120, 33);

    public static GameButton nextTutorialButton = new GameButton(298, 411, 45, 45);

    public boolean isClick(int x, int y) {
//        System.out.println(x);
//        System.out.println(y);
        if (x < button.getX())
            return false;
        if (x > button.getX() + button.getWidth())
            return false;
        if (y < button.getY())
            return false;
        if (y > button.getY() + button.getHeight())
            return false;
            return true;
    }
}
