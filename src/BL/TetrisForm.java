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

  //  private boolean boolField[][];
    //private Point2D leftCornerOffield;
    public Forms form;


    // private LinkedList<Point2D> pointList;

    public TetrisForm(int xCoord, int yCoord, int widthOfBlock, int heightOfBlock, Forms form) {
        this.widthOfBlock = widthOfBlock;
        this.heightOfBlock = heightOfBlock;
        //System.out.println("drinadf");
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.falling = true;
        this.first = true;
        this.form = form;

       // this.boolField = new boolean[4][4];
        //   this.leftCornerOffield=new Point2D.Double(xCoord,yCoord);
        //  pointList= new LinkedList<>();

        //<editor-fold desc="oldField">
        /*int[][] feld = CoordinatesOfForms.getCords(this.form);


        for (int y = 0; y < feld.length; y++) {
            int[] xC = new int[2];
            for (int x = 0; x < feld[y].length; x++) {
                xC[x] = feld[y][x];
                // System.out.println(feld[y][x]);
            }
            try {
                // System.out.println("xC1= "+xC[0]+" xC2= "+xC[1]);
                boolField[xC[0]][xC[1]] = true;
            } catch (IndexOutOfBoundsException ex) {
                ex.printStackTrace();
                this.stop();
            }

        }*/
        //</editor-fold>
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

    //<editor-fold desc="oldDraw">
    /*public void draw(Graphics2D g2) {
        g2.setColor(form.getC());
        for (int y = 0; y < boolField.length; y++) {
            for (int x = 0; x < boolField.length; x++) {
                if (boolField[y][x]) {
                    RoundRectangle2D rr = new RoundRectangle2D.Float(xCoord * widthOfBlock + (y * widthOfBlock), (yCoord * heightOfBlock) + 2 + (x * heightOfBlock),
                            widthOfBlock - 2, heightOfBlock - 2, 10, 10);
                    g2.fill(rr);
                }
            }
        }
    }*/
    //</editor-fold>
    public void draw(Graphics2D g2)
    {
        g2.setColor(form.getC());
        Point2D[] pointField = CoordinatesOfForms.getPointCoords(form);
        for(int i =0;i<pointField.length;i++)
        {
            RoundRectangle2D rr = new RoundRectangle2D.Float((int)(xCoord * widthOfBlock + (pointField[i].getY() * widthOfBlock)), (int)((yCoord * heightOfBlock) + 2 + (pointField[i].getX() * heightOfBlock)),
                    widthOfBlock - 2, heightOfBlock - 2, 10, 10);
            g2.fill(rr);
        }

    }

    public int getxCoord() {
        return xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public void setxCoord(int x) {

        if (collisionAvoidenceX(x))
            this.xCoord += x;


    }

    //<editor-fold desc="oldSetyCoord">
    /*public void setyCoord(int y2) {
        for(int y = 0; y < boolField.length;y++){
            for (int x = 0; x < boolField[y].length;x++){
                if(boolField[y][x]) {
                    if(TetrisGUI.fields[yCoord + x + y2 ][xCoord + y]){
                        return;
                    }
                }
            }
        }

        this.yCoord+=y2;
    }*/
    //</editor-fold>

    public void setyCoord(int y)
    {
        if(collisionAvoidenceY(y))
        {
            this.yCoord+=y;
        }
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



    @Override
    public void run() {
        while (falling) {

            //<editor-fold desc="old run">
           /* // printFeld();

       /*     for (int y = 0; y < boolField.length; y++) {
                for (int x = 0; x < boolField[y].length; x++) {
                    if (boolField[y][x]) {
                        if (TetrisGUI.fields[yCoord + x + 1][xCoord + y]) {
                            falling = false;
                            this.stop();
                        }
                    }
                }
            }*/
            //</editor-fold>
       if(collisionAvoidenceY(1))
       {
           yCoord++;
       }
       else {
           falling=false;
           setFieldTrue();
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

    private void setFieldTrue() {
        Point2D[] pointfield = CoordinatesOfForms.getPointCoords(form);

        for(int i = 0; i< pointfield.length;i++)
        {
            TetrisGUI.fields[(int)(yCoord+pointfield[i].getX())][(int)(xCoord+pointfield[i].getY())]=true;
        }
    }


    //<editor-fold desc="getLowestBlockofColum">
   /* public void getLowestBlockOfColum() {
        int[][] blocks = CoordinatesOfForms.getCords(form);
        for (int y = 0; y < blocks.length; y++) {
            for (int x = 0; x < blocks[y].length; x++) {
                System.out.println(blocks[y][x] + "a\n");
            }
        }
    }*/
    //</editor-fold>


    public boolean collisionAvoidenceY(int y) {
        Boolean isOk = true;
        Point2D[] pointField = CoordinatesOfForms.getPointCoords(form);
        for (int i = 0; i < pointField.length; i++) {
          //  System.out.println("yCoord"+(yCoord + pointField[i].getY() + y)+" xCoord: "+(xCoord + pointField[i].getX()));
            if (TetrisGUI.fields[(int) (yCoord + pointField[i].getX() + y)][(int) (xCoord + pointField[i].getY())]) {
               // System.out.println("yCoord"+(yCoord + pointField[i].getY() + y)+" xCoord: "+(xCoord + pointField[i].getX())+" "+isOk);
                    isOk = false;

            }

        }
      //  System.out.println("yCollison: "+isOk);
        return isOk;
    }

    public boolean collisionAvoidenceX(int x) {
        Boolean isOk = true;
        Point2D[] pointField = CoordinatesOfForms.getPointCoords(form);
        for (int i = 0; i < pointField.length; i++) {
            //System.out.println(xCoord + pointField[i].getX() + 1);
            if (TetrisGUI.fields[(int) (yCoord + pointField[i].getX())][(int) (xCoord + pointField[i].getY()+x)]) {
                isOk = false;

            }

        }
      //  System.out.println("xCollison: "+isOk);
        return isOk;
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
