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
    //private boolean first;

    //  private boolean boolField[][];
    //private Point2D leftCornerOffield;
    public Forms form;
    private int index;
    private Point2D[] pointField;
    private int[] removeRows;


    // private LinkedList<Point2D> pointList;

    public TetrisForm(int xCoord, int yCoord, int widthOfBlock, int heightOfBlock, Forms form) {
        this.widthOfBlock = widthOfBlock;
        this.heightOfBlock = heightOfBlock;
        //System.out.println("drinadf");
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.falling = true;
        //  this.first = true;
        this.form = form;
        this.index = 0;
        this.pointField = CoordinatesOfForms.getPointCoords(form);
        removeRows = new int[4];

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
    public void draw(Graphics2D g2) {
        g2.setColor(form.getC());

        for (int i = 0; i < pointField.length; i++) {
            if(pointField[i].getX()!=-1){
                System.out.println("1");
            RoundRectangle2D rr = new RoundRectangle2D.Float((int) (xCoord * widthOfBlock + (pointField[i].getY() * widthOfBlock)), (int) ((yCoord * heightOfBlock) + 2 + (pointField[i].getX() * heightOfBlock)),
                    widthOfBlock - 2, heightOfBlock - 2, 10, 10);
            g2.fill(rr);}
        }

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

    public void setyCoord(int y) {
        if (collisionAvoidenceY(y)) {
            this.yCoord += y;
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

    public void rotateRight() {
        if (index == 3) {
            index = 0;
        } else {
            index += 1;
        }

        Point2D[] pointFieldtry = CoordinatesOfForms.rotatetForm(form, index);
        if (collisionAvoidenceX(pointFieldtry) && collisionAvoidenceY(pointFieldtry)) {
            pointField = pointFieldtry;
        }
    }

    public void rotateLeft() {
        if (index == 0) {
            index = 3;
        } else {
            index -= 1;
        }

        Point2D[] pointFieldtry = CoordinatesOfForms.rotatetForm(form, index);
        if (collisionAvoidenceX(pointFieldtry) && collisionAvoidenceY(pointFieldtry)) {
            pointField = pointFieldtry;
        }
    }


    @Override
    public void run() {
        while (!interrupted()) {

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
            //System.out.println("sdfasdf");

            if (collisionAvoidenceY(1)) {
                yCoord++;
            } else {
                falling = false;
                setFieldTrue();
                this.interrupt();
                detectRowFalling();
            }

            //System.out.println("x: "+xCoord+" y: "+yCoord);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                //e.printStackTrace();
                // System.out.println("unten angekommen");
                this.interrupt();
            }

        }

    }

    private void detectRowFalling() {
        if (test1Row()[0] != -1) {
            reWriteField(test1Row());
            System.out.println("adfasdf");
        }
    }

    private void reWriteField(int[] removes) {
        boolean isOk = true;
        int maxy = 0;
        //printFeld();
        for (int i = 0; i < removes.length; i++) {
            int help = removes[i];

            for (int y = help; y >= 1; y--) {
                for (int x = 1; x < TetrisGUI.fields[y].length - 1; x++) {
                    if (TetrisGUI.fields[y][x]) {

                        isOk = false;
                    }

                    TetrisGUI.fields[y][x] = TetrisGUI.fields[y - 1][x];
                }
                if (isOk) {
                    maxy = y;
                    break;
                }
                isOk = true;

            }
            for (int j = 1; j < TetrisGUI.fields[help].length - 1; j++) {
                TetrisGUI.fields[help][j] = false;
            }
        }
        System.out.println("------------------------------------------");
       // printFeld();
        
        TetrisGUI.updateFertigListe(maxy);
    }

    private int[] test1Row() {
        boolean isOk = true;
        int y;
        int counter = 0;
        int[] removes = new int[4];
        removes[0] = -1;
        for (y = 0; y < TetrisGUI.fields.length - 1; y++) {
            for (int x = 1; x < TetrisGUI.fields[y].length - 1; x++) {
                if (!TetrisGUI.fields[y][x]) {

                    isOk = false;
                }
            }
            if (isOk) {
                //System.out.println("adadsfasd");
                removes[counter++] = y;
            }
            isOk = true;

        }

        return removes;

    }

    private void setFieldTrue() {


        for (int i = 0; i < pointField.length; i++) {
            TetrisGUI.fields[(int) (yCoord + pointField[i].getX())][(int) (xCoord + pointField[i].getY())] = true;

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

    public boolean collisionAvoidenceY(Point2D[] pointfield) {
        Boolean isOk = true;

        for (int i = 0; i < pointfield.length; i++) {
            //  System.out.println("yCoord"+(yCoord + pointField[i].getY() + y)+" xCoord: "+(xCoord + pointField[i].getX()));
            try {
                if (TetrisGUI.fields[(int) (yCoord + pointfield[i].getX())][(int) (xCoord + pointfield[i].getY())]) {
                    // System.out.println("yCoord"+(yCoord + pointField[i].getY() + y)+" xCoord: "+(xCoord + pointField[i].getX())+" "+isOk);
                    isOk = false;

                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.out.println("geth net da hin");
            }


        }
        //  System.out.println("yCollison: "+isOk);
        return isOk;
    }

    public boolean collisionAvoidenceX(Point2D[] pointfield) {
        Boolean isOk = true;

        for (int i = 0; i < pointfield.length; i++) {

            //System.out.println(xCoord + pointField[i].getX() + 1);
            try {
                if (TetrisGUI.fields[(int) (yCoord + pointfield[i].getX())][(int) (xCoord + pointfield[i].getY())]) {
                    isOk = false;

                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.out.println("geht net drehen");
            }


        }
        //  System.out.println("xCollison: "+isOk);
        return isOk;
    }


    public boolean collisionAvoidenceX(int x) {
        Boolean isOk = true;

        for (int i = 0; i < pointField.length; i++) {
            //System.out.println(xCoord + pointField[i].getX() + 1);
            if (TetrisGUI.fields[(int) (yCoord + pointField[i].getX())][(int) (xCoord + pointField[i].getY() + x)]) {
                isOk = false;

            }

        }
        //  System.out.println("xCollison: "+isOk);
        return isOk;
    }

    public void printFeld() {
        for (int i = 0; i < TetrisGUI.fields.length; i++) {
            for (int j = 0; j < TetrisGUI.fields[i].length; j++) {

                System.out.print(" " + TetrisGUI.fields[i][j]);
            }
            System.out.println();
        }
    }

    public Point2D[] getPointField() {
        return pointField;
    }

 /*   public void repaintForms(Graphics2D g2) {
        g2.setColor(form.getC());
        //System.out.println("dafsdfasdfsadasdfsadfsadfasdfasdfasd");
        for (int i = 0; i < pointField.length; i++) {
            if(pointField[i].getX()!=-1){
                g2.setColor(form.getC());
            RoundRectangle2D rr = new RoundRectangle2D.Float((int) (xCoord * widthOfBlock + (pointField[i].getY() * widthOfBlock)), (int) ((yCoord * heightOfBlock) + 2 + (pointField[i].getX() * heightOfBlock)),
                    widthOfBlock - 2, heightOfBlock - 2, 10, 10);
            g2.fill(rr);//System.out.println("a");
                 }

            else{
                g2.setColor(Color.DARK_GRAY);
            RoundRectangle2D rr = new RoundRectangle2D.Float((int) (xCoord * widthOfBlock + (pointField[i].getY() * widthOfBlock)), (int) ((yCoord * heightOfBlock) + 2 + (pointField[i].getX() * heightOfBlock)),
                    widthOfBlock - 2, heightOfBlock - 2, 10, 10);
            g2.fill(rr);
            System.out.println("b");
            }

        }
    }*/

    public void repaintForms(Graphics2D g2) {
        for (int y = 0; y < TetrisGUI.fields.length; y++) {
            for (int x = 1; x < TetrisGUI.fields[y].length-1; x++) {
                if (!TetrisGUI.fields[y][x]) {
                    g2.setColor(Color.DARK_GRAY);
                    //g2.fillRect(j * widthOfOneField, i * heightOfOneField, widthOfOneField, heightOfOneField);
                    RoundRectangle2D rr = new RoundRectangle2D.Float(xCoord * widthOfBlock,yCoord*heightOfBlock+2,widthOfBlock-2,heightOfBlock-2,10,10);
                    g2.fill(rr);
                } else {
                    for(int i=0;i<pointField.length;i++)
                    {
                        System.out.println(pointField[i].getY()+" "+pointField[i].getX());
                        if(pointField[i].getX()!=-1)
                        {
                            g2.setColor(form.getC());
                            RoundRectangle2D rr = new RoundRectangle2D.Float((int)(xCoord*widthOfBlock+(pointField[i].getY()*widthOfBlock)),(int)(yCoord*heightOfBlock+(pointField[i].getX())+2),widthOfBlock-2,heightOfBlock-2,10,10);
                            g2.fill(rr);
                        }
                    }
                }
            }
        }
    }

    public int getyCoord() {
        return yCoord;
    }
}
