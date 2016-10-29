package controllers;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;

import java.io.Serializable;

/**
 * Created by Le Huy Duc on 29/10/2016.
 */
public class UnitState implements Serializable {
    public int column, row;
    public int health;
    public boolean isAlive;
    public int slowDuration = 0;
    public int maxMoveStep = 0;

    public UnitState(int column,int row, int health,boolean isAlive) {
        this.column = column;
        this.row = row;
        this.health = health;
        this.isAlive = isAlive;
    }

    public UnitState(int column,int row, int health,boolean isAlive,int slowDuration,int maxMoveStep) {
        this.column = column;
        this.row = row;
        this.health = health;
        this.isAlive = isAlive;
        this.slowDuration = slowDuration;
        this.maxMoveStep = maxMoveStep;
    }


    public UnitState(SingleController sc) {
        this.column = sc.getColumn();
        this.row = sc.getRow();
        this.health = sc.getHealth();
        this.isAlive = sc.isAlive();
    }
}
