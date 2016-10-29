package main;

import controllers.ControllerController;
import controllers.PlayerController;
import main.gameScreens.GameScreen;
import main.gameScreens.PlayGameScreen;

/**
 * Created by Le Huy Duc on 20/10/2016.
 */
public class LevelManager {

    private int currentLevel = 1;
    public static final int MAX_LEVEL = 22;

    public LevelManager() {
        currentLevel = -1;
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
        if (currentLevel==-1) {
            newLevel();
            if (GameWindow.editing) {
                currentLevel = 0;
                GameLevel.instance.createLevel(0);
                GameWindow.editing = false;
            }
            else {
                currentLevel = 1;
                GameLevel.instance.createLevel(1);
            }
        }

        if (GameLevel.instance.hasWon()) {
            if (currentLevel == MAX_LEVEL) {
                return;
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
        PlayerController.instance.initStack();
        GameLevel.instance.createLevel(currentLevel);
    }

    public void resetMaze() {
        if (PlayerController.instance.isMoving) return;
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

    public boolean isFinishMoving() {
        return GameLevel.instance.isFinishMoving();
    }
}
