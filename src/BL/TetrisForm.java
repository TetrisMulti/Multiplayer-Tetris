package BL;

import GUI.TetrisGUI;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RoundRectangle2D;
import java.util.LinkedList;
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

    private  boolean boolField[][];
    //private Point2D leftCornerOffield;
    public Forms form;


   // private LinkedList<Point2D> pointList;

    public TetrisForm(int xCoord, int yCoord, int widthOfBlock, int heightOfBlock, Forms form) {
        this.widthOfBlock = widthOfBlock;
        this.heightOfBlock = heightOfBlock;
        System.out.println("drinadf");
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.falling = true;
        this.first=true;
        this.form=form;

        this.boolField=new boolean[4][4];
     //   this.leftCornerOffield=new Point2D.Double(xCoord,yCoord);
      //  pointList= new LinkedList<>();

        int[][] feld= CoordinatesOfForms.getCords(form);


        for(int y=0;y<feld.length;y++)
        {
            int[] xC = new int[2];
            for(int x=0;x<feld[y].length;x++)
            {
                xC[x]=feld[y][x];
            }
            boolField[xC[0]][xC[1]]=true;
        }
    }

    //<editor-fold desc="oldDraw">
  /*  public void draw(Graphics2D g2) {
        g2.setColor(Color.GREEN);
       // g2.fillRect(xCoord * widthOfBlock, yCoord * heightOfBlock, widthOfBlock, heightOfBlock);
        RoundRectangle2D rr = new RoundRectangle2D.Float(xCoord * widthOfBlock, (yCoord * heightOfBlock)+2, widthOfBlock-2, heightOfBlock-2,10,10);
        g2.fill(rr);
    }*/
    //</editor-fold>

    //<editor-fold desc="oldDrawv2">
  /*  public void draw(Graphics2D g2)
    {


        if(form==null)
        {
            System.out.println("asasdf");
        }
        g2.setColor(form.getC());
        String blocks = form.getBlocks();
       //
        for(int i=0;i<4;i++)
        {
            String coords=blocks.split("#")[i];
            int xCor=Integer.parseInt(coords.split(",")[0]);
            int yCor=Integer.parseInt(coords.split(",")[1]);
            pointList.add(i, new Point2D.Double(xCor,yCor));

            RoundRectangle2D rr = new RoundRectangle2D.Float((float) ((xCoord+pointList.get(i).getX()) * widthOfBlock), (float) (((yCoord+pointList.get(i).getY()) * heightOfBlock)+2), widthOfBlock-2, heightOfBlock-2,30,30);
            g2.fill(rr);
        }

    }*/
    //</editor-fold>

    public void draw(Graphics2D g2)
    {
        g2.setColor(form.getC());
        for(int y=0;y<boolField.length;y++)
        {
            for(int x=0;x<boolField.length;x++)
            {
                if(boolField[y][x])
                {
                    RoundRectangle2D rr = new RoundRectangle2D.Float(xCoord * widthOfBlock + (y * widthOfBlock), (yCoord * heightOfBlock)+2 + (x * heightOfBlock),
                            widthOfBlock-2, heightOfBlock-2,10,10);
                    g2.fill(rr);;
                }
            }
        }


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
        for(int y = 0; y < boolField.length;y++){
            for (int x = 0; x < boolField[y].length;x++){
                if(boolField[y][x]) {
                    if(TetrisGUI.fields[yCoord + x + y ][xCoord + y]){
                        return;
                    }
                }
            }
        }

        this.yCoord+=y;
    }

    //<editor-fold desc="testCollision">
   /* public Boolean testColission()
    {
        Boolean isOk=true;

        for(int i=0;i<pointList.size();i++)
        {
            System.out.println(TetrisGUI.fields[(int)pointList.get(i).getY()+1][(int)pointList.get(i).getX()]);
            if(TetrisGUI.fields[(int)pointList.get(i).getY()+1][(int)pointList.get(i).getX()])
            {
                isOk=false;
            }
        }

        return isOk;
    }*/
    //</editor-fold>

    public Boolean testColission()
    {
        Boolean isOk=false;



        return isOk;
    }

    @Override
    public void run() {
        while (falling) {

           // printFeld();
            for(int y = 0; y < boolField.length;y++){
                for (int x = 0; x < boolField[y].length;x++){
                    if(boolField[y][x]) {
                        if(TetrisGUI.fields[yCoord + x + 1 ][xCoord + y]){
                            falling = false;
                            this.stop();
                        }
                    }
                }
            }
            yCoord++;
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
