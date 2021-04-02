package monsters;

import java.awt.*;
import game.GameTile;

public class RecklessCanibal extends Monster{
    protected int row;
    protected int col;
    protected Color color;
    //protected int point;
    protected String id;

    public static int ATTACK=4;
    public static int DEFENCE=6;
    public static int MP=8;
    public static int SPEED=10;
    public static boolean isDead;

    public RecklessCanibal(int row, int col, Color color) {
        super(row, col, color, "RC");
        this.row = row;
        this.col = col;
        this.color = color;
        this.ATTACK=ATTACK;
        this.DEFENCE=DEFENCE;
        this.MP=MP;
        this.SPEED=SPEED;
    }

    @Override
    public boolean isMoveValid(int moveRow, int moveCol) {
        int rowCoeficient = Math.abs(moveRow - this.row);
        int colCoeficient = moveCol - this.col;

        return  (rowCoeficient == 10 ||
                colCoeficient == 10);
    }

    @Override
    public boolean isAttackValid(int attackRow, int attackCol) {
        return this.isMoveValid(attackRow, attackCol);
    }

    @Override
    public boolean isPieceDead(boolean isDead) {
        if (DEFENCE<=0){
            return true;
        }
        return false;
    }
}
