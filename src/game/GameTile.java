package game;

import java.awt.*;

public class GameTile {
    private int row;
    private int col;
    public static int TILE_SIZE=50;

    public GameTile(int row,int col){
        this.row=row;
        this.col=col;
    }

    public void render(Graphics g){
        boardGrid(g);
    }

    //draw border between different squares
    private void boardGrid(Graphics g){
        g.setColor(Color.BLACK);
        for(int i = 0; i <=550; i+= 50){
            for (int j = 20; j<= 600; j += 50){
                g.drawRect(i,j,50,50);
            }
        }
    }
}
