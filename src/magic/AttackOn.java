package magic;

import game.GameTile;
import java.awt.*;

public class AttackOn extends Magic{
    protected int row;
    protected int col;
    protected Color color;
    protected String id;
    public int cost=20;

    public AttackOn(int row, int col, Color color) {
        super(row, col, color, "AtkOn");
        this.cost=cost;
    }

    public void render(Graphics g) {
        int x = this.col * GameTile.TILE_SIZE;
        int y = this.row * GameTile.TILE_SIZE;

        g.setColor(this.color);
        g.fillRect(x, y, 50, 50);

        g.setColor(Color.BLACK);
        g.drawString(this.id, x + 25, y + 25);
    }
}
