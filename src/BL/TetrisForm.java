package BL;

import GUI.TetrisGUI;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.Random;

/**
 * Created by Chris on 14.03.2017.
 */
public class TetrisForm extends Thread {

    public static boolean falling;
    private int xCoord;
    private int yCoord;
    private int widthOfBlock;
    private int heightOfBlock;
    private boolean first;
    private  int feld[][];
    public Forms form;

    public TetrisForm(int xCoord, int yCoord, int widthOfBlock, int heightOfBlock) {
        this.widthOfBlock = widthOfBlock;
        this.heightOfBlock = heightOfBlock;
        System.out.println("drinadf");
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.falling = true;
        this.first=true;
        this.form=newForm();
        this.feld=new int[2][2];
    }

  /*  public void draw(Graphics2D g2) {
        g2.setColor(Color.GREEN);
       // g2.fillRect(xCoord * widthOfBlock, yCoord * heightOfBlock, widthOfBlock, heightOfBlock);
        RoundRectangle2D rr = new RoundRectangle2D.Float(xCoord * widthOfBlock, (yCoord * heightOfBlock)+2, widthOfBlock-2, heightOfBlock-2,10,10);
        g2.fill(rr);
    }*/

    public void draw(Graphics2D g2)
    {


        if(form==null)
        {
            System.out.println("asasdf");
        }
        g2.setColor(form.getC());
        String str = form.getBlocks();
       //
        for(int i=0;i<4;i++)
        {
            String coords=str.split("#")[i];
            int xCor=Integer.parseInt(coords.split(",")[0]);
            int yCor=Integer.parseInt(coords.split(",")[1]);
            RoundRectangle2D rr = new RoundRectangle2D.Float((xCoord+xCor) * widthOfBlock, ((yCoord+yCor) * heightOfBlock)+2, widthOfBlock-2, heightOfBlock-2,30,30);
            g2.fill(rr);
        }

    }

    public Forms newForm()
    {
        Random rand = new Random();
        int x=rand.nextInt(Forms.values().length);

        return Forms.values()[x];
    }

    public int getxCoord() {
        return xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public void setxCoord(int x) {
        System.out.println("drin");
        System.out.println(x+" "+xCoord);
        System.out.println(TetrisGUI.fields[0].length);
        if(!TetrisGUI.fields[yCoord][xCoord+x])
        { if(xCoord==1&&x==-1)
        {


        }else if(xCoord==TetrisGUI.fields[0].length-2&&x==1)
        {

        }else
        {
            System.out.println("a");
            this.xCoord+=x;
        }}

    }

    public void setyCoord(int y) {
        this.yCoord+=y;
    }

    @Override
    public void run() {
        while (falling) {

           // printFeld();
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
