package magic;

import game.GameTile;
import java.awt.*;

public abstract class Magic {
    protected int row;
    protected int col;
    protected Color color;
    protected String id;
    public int cost;

    public Magic(int row, int col, Color color, String id) {
        this.row = row;
        this.col = col;
        this.color = color;
        this.id = id;
        this.cost=cost;
    }
    public Magic(int row, int col, Color color) {
        this(row, col, Color.BLUE, "M");
        this.row = row;
        this.col = col;
        this.color = color;
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

    public void setCost(int cost) {
        this.cost = cost;
    }
}
