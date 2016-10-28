package controllers.movement;

/**
 * Created by Le Huy Duc on 23/10/2016.
 */
public enum MoveType {
    UP,
    DOWN,
    LEFT,
    RIGHT,
    STAY;

    public static String fileNameOf(MoveType moveType) {
        if (moveType==UP) return "up";
        if (moveType==DOWN) return "down";
        if (moveType==LEFT) return "left";
        if (moveType==RIGHT) return "right";
        return "stay";
    }
}
