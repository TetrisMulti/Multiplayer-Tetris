package BL;

import GUI.TetrisGUI;

import java.awt.*;

/**
 * Created by Chris on 14.03.2017.
 */
public class TetrisForm extends Thread {

    public static boolean falling;
    private int xCoord;
    private int yCoord;
    private int widthOfBlock;
    private int heightOfBlock;

    public TetrisForm(int xCoord, int yCoord, int widthOfBlock, int heightOfBlock) {
        this.widthOfBlock = widthOfBlock;
        this.heightOfBlock = heightOfBlock;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.falling = true;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.GREEN);
        g2.fillRect(xCoord * widthOfBlock, yCoord * heightOfBlock, widthOfBlock, heightOfBlock);
    }

    public int getxCoord() {
        return xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    @Override
    public void run() {
        while (falling) {

            printFeld();
            if (!TetrisGUI.fields[yCoord + 1][xCoord]) {
                yCoord++;
            } else {
                falling = false;
                this.stop();
            }
            //System.out.println("x: "+xCoord+" y: "+yCoord);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void printFeld() {
        for (int i = 0; i < TetrisGUI.fields.length; i++) {
            for (int j = 0; j < TetrisGUI.fields[i].length; j++) {

                if (j == xCoord && i == yCoord)
                    System.out.print(2);
                else if (TetrisGUI.fields[i][j])
                    System.out.print(1);
                else
                    System.out.print(0);
            }
            System.out.println();
        }
    }
}
