package controllers;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;

/**
 * Created by Le Huy Duc on 29/10/2016.
 */
public class UnitState {
    public int column, row;
    public int health;
    public boolean isAlive;

    public UnitState(int column,int row, int health,boolean isAlive) {
        this.column = column;
        this.row = row;
        this.health = health;
        this.isAlive = isAlive;
    }

    public UnitState(SingleController sc) {
        this.column = sc.getColumn();
        this.row = sc.getRow();
        this.health = sc.getHealth();
        this.isAlive = sc.isAlive();
    }
}