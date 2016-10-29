package controllers.mummies;

import controllers.Effectable;
import controllers.SingleControllerWithAnimation;
import controllers.UnitState;
import main.gameScreens.PlayGameScreen;
import models.GameObject;
import views.AnimationView;

import java.awt.*;
import java.util.Stack;

/**
 * Created by Le Huy Duc on 20/10/2016.
 */
public class EnemyController extends SingleControllerWithAnimation implements Effectable {

    private static int SIZEX = 50, SIZEY = 50;
    protected int moveDirections = 4;
    protected int defaultMaxMoveStep = 0;
    protected int maxMoveStep = 0;
    protected int slowDuration = 1;
    protected boolean firstTime = false;


    public void setMaxMoveStep(int v) {
        maxMoveStep = v;
    }

    public void setSlowDuration(int v) {
        slowDuration = v;
    }

    protected EnemyController(int column, int row) {
        super();
        gameObject = new GameObject(column,row,SIZEX,SIZEY);
        gameView = new AnimationView();
        animationView = (AnimationView)gameView;
        initStack();
    }


    public synchronized void draw(Graphics g) {
        gameView.drawImage(g,gameObject);
    }

    void checkEffect() {
        if (slowDuration > 0) slowDuration--;
        if (slowDuration==0) maxMoveStep = defaultMaxMoveStep;
    }

    protected UnitState currentStateWithEffect() {
        UnitState state = new UnitState(getColumn(),getRow(),getHealth(),isAlive());
        state.slowDuration = slowDuration;
        state.maxMoveStep = maxMoveStep;
        return state;
    }


    public synchronized void run() {
        if (PlayGameScreen.playerTurn) return;
        move(gameObject);
    }

}
