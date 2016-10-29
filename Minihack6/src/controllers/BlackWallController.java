package controllers;

import main.gameScreens.PlayGameScreen;

import java.util.Stack;

/**
 * Created by Le Huy Duc on 29/10/2016.
 */
public class BlackWallController extends WallController {

    private boolean isClose;
    Stack<Boolean> myStack = new Stack<>();

    public BlackWallController(int column, int row, WallType wallType) {
        super(column, row, wallType);
        isClose = true;
        gameView.setImage("blackwall.gif");
        WallControllerManager.instance.add(this);

        if (wallType == WallType.DOWN) {
            PlayGameScreen.wallDown[getColumn()][getRow()] = true;
        } else {
            PlayGameScreen.wallRight[getColumn()][getRow()] = true;
        }
    }

    public void changeWallStatus() {
        myStack.push(isClose);
        if (isClose) {
            isClose = false;
            gameView.setImage("fadedblackwall.png");
            if (wallType == WallType.DOWN) {
                PlayGameScreen.wallDown[getColumn()][getRow()] = false;
            } else {
                PlayGameScreen.wallRight[getColumn()][getRow()] = false;
            }
        } else {
            isClose = true;
            gameView.setImage("blackwall.gif");
            if (wallType == WallType.DOWN) {
                PlayGameScreen.wallDown[getColumn()][getRow()] = true;

            } else {
                PlayGameScreen.wallRight[getColumn()][getRow()] = true;
            }
        }
    }

    public void undo() {
        if (myStack.empty()) return;
        myStack.pop();
        if (isClose) {
            isClose = false;
            gameView.setImage("fadedblackwall.png");
            if (wallType == WallType.DOWN) {
                PlayGameScreen.wallDown[getColumn()][getRow()] = false;
            } else {
                PlayGameScreen.wallRight[getColumn()][getRow()] = false;
            }
        } else {
            isClose = true;
            gameView.setImage("blackwall.gif");
            if (wallType == WallType.DOWN) {
                PlayGameScreen.wallDown[getColumn()][getRow()] = true;

            } else {
                PlayGameScreen.wallRight[getColumn()][getRow()] = true;
            }
        }
    }
}
