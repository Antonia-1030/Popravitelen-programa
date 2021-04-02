package monsters;

import java.awt.*;
import game.GameTile;

public abstract class Monster {
    protected int row;
    protected int col;
    protected Color color;
    //protected int point;
    protected String id;

    public static int ATTACK;
    public static int DEFENCE;
    public static int SPEED;
    public static int MP;
    public static boolean isDead;

    public Monster(int row, int col, Color color, String id) {
        this.row = row;
        this.col = col;
        this.color = color;
        this.id = id;
        this.ATTACK=ATTACK;
        this.DEFENCE=DEFENCE;
        this.MP=MP;
    }

    public Monster(int row, int col, Color color) {
        this(row, col, Color.BLUE, "MC");
        this.row = row;
        this.col = col;
        this.color = color;
        this.ATTACK=ATTACK;
        this.DEFENCE=DEFENCE;
        this.MP=MP;
        this.SPEED=SPEED;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    //get values
    public int getAttack(){return ATTACK;}
    public int getDefence(){return DEFENCE;}
    public int getMP(){return MP;}

    public void setHealth(int health) {
        this.DEFENCE = DEFENCE;
    }

    public void move(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void render(Graphics g) {
        int x = this.col * GameTile.TILE_SIZE;
        int y = this.row * GameTile.TILE_SIZE;

        g.setColor(this.color);
        g.fillRect(x, y, 50, 50);

        g.setColor(Color.BLACK);
        g.drawString(this.id, x + 25, y + 25);
    }

    //move & attack
    public abstract boolean isMoveValid(int moveRow, int moveCol);
    public abstract boolean isAttackValid(int attackRow, int attackCol);
    public abstract boolean isPieceDead(boolean isDead);
}
