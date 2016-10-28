package controllers;

import main.gameScreens.PlayGameScreen;
import models.GameObject;
import views.AnimationView;

import java.awt.*;
import java.util.Stack;

/**
 * Created by Le Huy Duc on 20/10/2016.
 */
public class EnemyController extends SingleControllerWithAnimation {

    Stack<Point> backMove;
    Stack<Point> backRC;

    private static int SIZEX = 50, SIZEY = 50;
    protected int moveDirections = 4;
    protected int maxMoveStep = 0;

    protected EnemyController(int column, int row) {
        super();
        gameObject = new GameObject(column,row,SIZEX,SIZEY);
        gameView = new AnimationView();
        animationView = (AnimationView)gameView;
        backMove = new Stack<>();
        backRC = new Stack<>();
    }

    public void addToStack() {
        backMove.push(new Point(gameObject.getX(), gameObject.getY()));
        backRC.push(new Point(gameObject.getRow(), gameObject.getColumn()));
    }

    public void draw(Graphics g) {
        gameView.drawImage(g,gameObject);
    }

    public void run() {
        if (PlayGameScreen.playerTurn) return;
        move(gameObject);
    }

    public void undo() {
//        for (int i = 0; i < )
        Point pm = backMove.pop();
        gameObject.setX(pm.x);
        gameObject.setY(pm.y);
        System.out.println(backRC.size());
        Point prc = backRC.pop();

        for (int i = 0; i < 9; i++) {
            prc = backRC.pop();
        }
        gameObject.setRow(prc.x);
        gameObject.setColumn(prc.y);
    }
}
