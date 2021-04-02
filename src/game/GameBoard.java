package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import monsters.*;
import magic.*;
import message.Message;

public class GameBoard extends JFrame implements MouseListener{
    public static final int WIDTH = 12;
    public static final int HEIGHT = 12;
    private Monster[][] pieceCollection;
    private Magic[][] magicCardCollection;
    private Monster selectedPiece;
    private Monster attackedPiece;

    public static boolean isGameOver=false;
    protected int turnRandomizer=(int)(Math.random()*6+1);
    public int playerMonsters =18;
    public int enemyMonsters =18;
    public int playerLost=0;
    public int enemyLost=0;
    public int playerMagic=10;
    public int enemyMagic=10;
    public boolean isPlayerBlue;
    public boolean isPlayerRed;

    FontMetrics size;

    public GameBoard(){
        this.pieceCollection = new Monster[WIDTH][HEIGHT];
        this.magicCardCollection=new Magic[WIDTH][HEIGHT];

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(800,700);
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        menu();
        addMouseListener(this);

        this.setVisible(true);
    }

    public void menu(){
        JButton monst=new JButton("Draw Monster");
        JButton mag=new JButton("Draw Magic");
        JButton skip=new JButton("Skip");

        monst.setSize(150,30);
        monst.setLocation(620,20);
        mag.setSize(150,30);
        mag.setLocation(620,60);
        skip.setSize(150,30);
        skip.setLocation(620,100);

        this.add(monst);
        this.add(mag);
        this.add(skip);

        skip.addActionListener(e->getMonsterCard());
        mag.addActionListener(e->getMagicCard());
        monst.addActionListener(e->getMagic());

        this.setLayout(null);
        this.setVisible(true);
    }

    public void paint(Graphics g) {
        super.paint(g);
        if (!isGameOver){
            size = getFontMetrics(g.getFont());
            g.setColor(Color.ORANGE);
            g.setFont(new Font("Serif Plain",Font.ITALIC,20));
            g.drawString("Pl Magic: "+ playerMagic, 620,300);
            g.drawString("Player: "+ playerMonsters, 620,350);
            g.drawString("Player lost: "+ playerLost, 620,400);
            g.drawString("En Magic: "+ enemyMagic, 620,450);
            g.drawString("Enemy: "+ enemyMonsters, 620,500);
            g.drawString("Enemy lost: "+ enemyLost, 620,550);

            for(int row = 0; row < WIDTH; row++) {
                for(int col = 0; col < HEIGHT; col++) {
                    GameTile tile=new GameTile(row,col);
                    tile.render(g);
                    this.renderGamePiece(g, row, col);
                    this.renderMagicCard(g,row,col);

                }
            }
        }
        else {
            gameOver(g);
        }
    }

    public void renderGamePiece(Graphics g, int row, int col) {
        if(this.hasBoardPiece(row, col)) {
            Monster m = this.getBoardPiece(row, col);
            m.render(g);
        }
    }

    public void renderMagicCard(Graphics g, int row, int col) {
            Magic mag = this.getMagicCard(row,col);
            mag.render(g);
    }

    private boolean isTileEmpty(int row, int col) {
        if(this.pieceCollection[row][col] != null) {
            return false;
        }
        return true;
    }

    public int getBoardDimention(int coordinates) {
        return coordinates / GameTile.TILE_SIZE;
    }

    protected void getMagic(){
        if (isPlayerBlue){
            playerMagic+=10;
        }
        enemyMagic+=10;
    }

    public void getMonsterCard(){
        int randomizer=(int)(Math.random()*6+1);
        if (isPlayerBlue){
            if (randomizer==1){
                pieceCollection[randomizer][12] = (new DogEatingBug(randomizer, 12, Color.BLUE));
            }
            if (randomizer==2){
                pieceCollection[randomizer][12] = (new DrunkKnight(randomizer, 12, Color.BLUE));
            }
            if (randomizer==3){
                pieceCollection[randomizer][12] = (new MagicCat(randomizer, 12, Color.BLUE));
            }
            if (randomizer==4){
                pieceCollection[randomizer][12] = (new RecklessCanibal(randomizer, 12, Color.BLUE));
            }
            if (randomizer==5){
                pieceCollection[randomizer][12] = (new SandTurtle(randomizer, 12, Color.BLUE));
            }
            if (randomizer==6){
                pieceCollection[randomizer][12] = (new SofisticatedSam(randomizer, 12, Color.BLUE));
            }
        }
        if (isPlayerRed){
            if (randomizer==1){
                pieceCollection[randomizer][0] = (new DogEatingBug(randomizer, 0, Color.RED));
            }
            if (randomizer==2){
                pieceCollection[randomizer][0] = (new DrunkKnight(randomizer, 0, Color.RED));
            }
            if (randomizer==3){
                pieceCollection[randomizer][0] = (new MagicCat(randomizer, 0, Color.RED));
            }
            if (randomizer==4){
                pieceCollection[randomizer][0] = (new RecklessCanibal(randomizer, 0, Color.RED));
            }
            if (randomizer==5){
                pieceCollection[randomizer][0] = (new SandTurtle(randomizer, 0, Color.RED));
            }
            if (randomizer==6){
                pieceCollection[randomizer][0] = (new SofisticatedSam(randomizer, 0, Color.RED));
            }
        }
    }

    public void getMagicCard(){
        int magicRandomizer=(int)(Math.random()*5+1);
        if (isPlayerBlue){
            if (magicRandomizer==1){
                magicCardCollection[magicRandomizer][12] = (new AttackOn(magicRandomizer, 12, Color.BLUE));
            }
            if (magicRandomizer==2){
                magicCardCollection[magicRandomizer][12] = (new DefenceUp(magicRandomizer, 12, Color.BLUE));
            }
            if (magicRandomizer==3){
                magicCardCollection[magicRandomizer][12] = (new Heal(magicRandomizer, 12, Color.BLUE));
            }
            if (magicRandomizer==4){
                magicCardCollection[magicRandomizer][12] = (new Move(magicRandomizer, 12, Color.BLUE));
            }
            if (magicRandomizer==5){
                magicCardCollection[magicRandomizer][12] = (new War(magicRandomizer, 12, Color.BLUE));
            }
        }
        if (isPlayerRed){
            if (magicRandomizer==1){
                magicCardCollection[magicRandomizer][0] = (new AttackOn(magicRandomizer, 0, Color.RED));
            }
            if (magicRandomizer==2){
                magicCardCollection[magicRandomizer][0] = (new DefenceUp(magicRandomizer, 0, Color.RED));
            }
            if (magicRandomizer==3){
                magicCardCollection[magicRandomizer][0] = (new Heal(magicRandomizer, 0, Color.RED));
            }
            if (magicRandomizer==4){
                magicCardCollection[magicRandomizer][0] = (new Move(magicRandomizer, 0, Color.RED));
            }
            if (magicRandomizer==5){
                magicCardCollection[magicRandomizer][0] = (new War(magicRandomizer, 0, Color.RED));
            }
        }
    }

    public void movePiece(int row, int col, Monster m) {
        int initialRow = m.getRow();
        int initialCol = m.getCol();

        m.move(row, col);

        this.pieceCollection[m.getRow()][m.getCol()] = this.selectedPiece;
        this.pieceCollection[initialRow][initialCol] = null;

        this.selectedPiece = null;
    }

    public int chosenMonster () {
        Monster m = (Monster) this.selectedPiece;
        this.pieceCollection[m.getRow()][m.getCol()] = this.selectedPiece;
        int atk = m.getAttack();
        return atk;
    }

    public int enemyMonster (int row, int col) {
        this.pieceCollection[row][col] = this.attackedPiece;
        Monster m = (Monster) this.attackedPiece;
        int defence = m.getDefence();
        int health = m.getDefence();
        int healthRemaining = health - (chosenMonster() - defence);
        return healthRemaining;
    }

    public int useMagic(int row,int col){
        Monster m = (Monster) this.selectedPiece;
        this.pieceCollection[m.getRow()][m.getCol()] = this.selectedPiece;
        int defence = m.getDefence();

        if (isPlayerRed){
            
            return enemyMagic;
        }
        return playerMagic;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = this.getBoardDimention(e.getY());
        int col = this.getBoardDimention(e.getX());

        if(this.selectedPiece != null) {
            Monster m = this.selectedPiece;

            if(m.isMoveValid(row, col) && isTileEmpty(row,col)) {
                int initialRow = m.getRow();
                int initialCol = m.getCol();

                movePiece(row, col, m);
                this.pieceCollection[initialRow][initialCol] = null;
                this.pieceCollection[row][col] = this.selectedPiece;
                this.repaint();
                return;
            }
            if (m.isAttackValid(row,col) && !isTileEmpty(row,col)){
                useMagic(row,col);
                m.setHealth(enemyMonster(row,col));

                if (m.isPieceDead(true) && isPlayerBlue){
                    enemyMonsters=enemyMonsters-1;
                    enemyLost++;
                }
                if (m.isPieceDead(true) && !isPlayerBlue){
                    playerMonsters=playerMonsters-1;
                    playerLost++;
                }
            }
        }
        else {
            Message.render(this, "Error", "Invalid move!");
            return;
        }

        if(this.hasBoardPiece(row, col)) {
            this.selectedPiece = this.getBoardPiece(row, col);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public Monster getBoardPiece(int row, int col) {
        return this.pieceCollection[row][col];
    }

    public Magic getMagicCard(int row, int col) {
        return this.magicCardCollection[row][col];
    }

    public boolean hasBoardPiece(int row, int col) {
        return this.getBoardPiece(row, col) != null;
    }

    public void playerTurn(){
        if (turnRandomizer%2==0){
            isPlayerBlue=true;
            while (isPlayerBlue){
                isPlayerBlue=isPlayerRed;
            }
        }
        if (turnRandomizer%2 !=0){
            isPlayerRed=true;
            while (isPlayerBlue){
                isPlayerRed=isPlayerBlue;
            }
        }
    }

    public void gameOver(Graphics g){
        size = getFontMetrics(g.getFont());

        if (isGameOver && enemyMonsters ==0){
            g.setColor(Color.YELLOW);
            g.setFont(new Font("Serif Italic",Font.BOLD,90));
            g.drawString("You win! Congrats!",120,HEIGHT/2);
        }
        if (isGameOver && playerMonsters ==0){
            g.setColor(Color.RED);
            g.setFont(new Font("Serif Plain",Font.BOLD,90));
            g.drawString("Enemy wins!",120,HEIGHT/2);
        }
    }
}
