package controllers;

import controllers.movement.MoveType;
import main.GameConfig;
import main.gameScreens.PlayGameScreen;
import models.GameObject;
import models.Player;
import utilities.KeyInput;
import utilities.KeyInputListener;
import utilities.Utils;
import views.AnimationView;

import java.awt.*;
import java.util.Stack;

/**
 * Created by Le Huy Duc on 19/10/2016.
 */
public class PlayerController extends SingleControllerWithAnimation implements Colliable {

    private static int SIZEX = 35, SIZEY = 35;

    private KeyInput keyInput = new KeyInput();
    public KeyInputListener keyInputListener = new KeyInputListener(keyInput);
    long lastMove = System.currentTimeMillis();


    public void init() {
        initStack();
    }

    private PlayerController(int column, int row) {
        super();
        gameObject = new Player(column,row);
        gameView = new AnimationView();
        animationView = (AnimationView)gameView;
        unitName = "explorer";
        gameObject.setPowerLevel(1);
        gameObject.setHealth(1);
        initStack();
    }


    //********* COLLISION **************************************************************//
    @Override
    public GameObject getCollisionObject() {
        return gameObject;
    }

    @Override
    public void onCollide(Colliable col) {
        if (col instanceof EnemyController) {
            GameObject go = col.getCollisionObject();
            if (go.getPowerLevel() >= gameObject.getPowerLevel()) {
                gameObject.takeDamage(go.getPowerLevel());
            }
        }
    }


    static int counter = 0;
    public boolean tryMove(GameObject go,int x2,int y2) {
        int x1 = go.getColumn(), y1 = go.getRow();
        int sql = GameConfig.DEFAULT_TILE_LENGTH;
        if (Utils.canMoveTo(x1,y1,x2,y2)) {
            lastMove = System.currentTimeMillis();
            isMoving = true;
            animationView.firstImage = System.currentTimeMillis();
            beginPoint = new Point(go.getX(), go.getY());
            targetPoint = new Point(x2*sql+sql/2,y2*sql+sql/2);
            targetGrid = new Point(x2,y2);
            PlayGameScreen.playerTurn = false;
            addCurrentState();
            return true;
        }
        return false;
    }

    public void move(GameObject go) {
        int x2 = go.getColumn(), y2 = go.getRow();
        if (keyInput.keyDown) {
            y2++;
            if (tryMove(go,x2,y2)) {
                moveType = MoveType.DOWN;
                Utils.playMoveSound();
                return;
            }
        }
        if (keyInput.keyUp) {
            y2--; if (tryMove(go,x2,y2)) {moveType = MoveType.UP; Utils.playMoveSound();return;}}
        if (keyInput.keyRight) {
            x2++; if (tryMove(go,x2,y2)) {moveType = MoveType.RIGHT; Utils.playMoveSound();return;}}
        if (keyInput.keyLeft) {
            x2--; if (tryMove(go,x2,y2)) {moveType = MoveType.LEFT; Utils.playMoveSound();return;}}
        if (keyInput.keySpace) {
            PlayGameScreen.playerTurn = false; lastMove = System.currentTimeMillis();}
    }

    public boolean finished() {
        return !isMoving && !isFighting;
    }

    public void draw(Graphics g) {
        if (animationView.nImage==0) animationView.setSheet("explorer_right.png",5);
        if (isMoving) animationView.drawImage(g,gameObject);
        else animationView.drawImage(g,gameObject,true);
    }

    boolean moveTurn = true;
    public void run() {
        if (isMoving) {moveAnimation(); return;}
        if (!PlayGameScreen.playerTurn) return;
        long now = System.currentTimeMillis();
        if (now - lastMove < 150) return;
        move(gameObject);

    }

    public static final PlayerController instance = new PlayerController(0,0);
}
