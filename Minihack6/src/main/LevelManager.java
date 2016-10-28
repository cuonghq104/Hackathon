package main;

import controllers.ControllerController;
import controllers.EnemyControllerManager;
import controllers.WallControllerManager;
import main.gameScreens.PlayGameScreen;

/**
 * Created by Le Huy Duc on 20/10/2016.
 */
public class LevelManager {

    private int currentLevel = 1;
    public static final int MAX_LEVEL = 10;

    public LevelManager() {
        currentLevel = 0;
    }

    private void newLevel() {
        ControllerController.instance.init();
        for (int i=0;i<30;i++)
            for (int j=0;j<30;j++) PlayGameScreen.wallDown[i][j] = PlayGameScreen.wallRight[i][j] = false;
    }

    private void nextLevel() {
        currentLevel++;
    }

    public boolean isVictory() {
        if (currentLevel == MAX_LEVEL) {
            return true;
        }
        return false;
    }
    public void run() {
        if (currentLevel==0) {
            currentLevel = 1;
            newLevel();
            GameLevel.instance.createLevel(1);
        }

        if (GameLevel.instance.hasWon()) {
            if (currentLevel == MAX_LEVEL) {

            }
            moveToNextLevel();
        }

        if (GameLevel.instance.hasLose()) {
            newLevel();
            GameLevel.instance.createLevel(currentLevel);
        }

        GameLevel.instance.run();

    }

    public void moveToNextLevel() {
        newLevel(); nextLevel();
        GameLevel.instance.createLevel(currentLevel);
    }

    public void resetMaze() {
        newLevel();
        GameLevel.instance.createLevel(currentLevel);
    }

    public void restartGame() {
        currentLevel = 1;
        newLevel();
        GameLevel.instance.createLevel(currentLevel);
    }

    public void undo() {
        GameLevel.instance.undo();
    }
    public static final LevelManager instance = new LevelManager();
}
