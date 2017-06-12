package BL;

import Beans.CoordinatesOfForms;
import Beans.Forms;
import GUI.TetrisGUI;
import com.sun.corba.se.impl.orb.ParserTable;


import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RoundRectangle2D;

/**
 * Created by Chris on 14.03.2017.
 */
public class TetrisForm extends Thread {

    public static boolean falling;
    private int xCoord;
    private int yCoord;
    private int widthOfBlock;
    private int heightOfBlock;
    public Forms form;
    private int index;
    private Point2D[] pointField;
    private Color[][] colorField;


    public TetrisForm(int xCoord, int yCoord, int widthOfBlock, int heightOfBlock, Forms form,Color[][] colorField) throws Exception {
        this.widthOfBlock = widthOfBlock;
        this.heightOfBlock = heightOfBlock;

        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.falling = true;

        this.form = form;
        this.index = 0;
        this.pointField = CoordinatesOfForms.getPointCoords(form);
        this.colorField=colorField;
        if(collisionAvoidence(xCoord, yCoord+1)){
           throw  new Exception();
        }


    }



    public void draw(Graphics2D g2) {
        g2.setColor(form.getC());

        for (int i = 0; i < pointField.length; i++) {
            if (pointField[i].getX() != -1) {
                RoundRectangle2D rr = new RoundRectangle2D.Float((int) (xCoord * widthOfBlock + (pointField[i].getY() * widthOfBlock)), (int) ((yCoord * heightOfBlock) + 2 + (pointField[i].getX() * heightOfBlock)),
                        widthOfBlock - 2, heightOfBlock - 2, 10, 10);
                g2.fill(rr);
            } else {
            }
        }

    }


    // Überprüft Collision
    public boolean collisionAvoidence(int x, int y){
        for (Point2D p : pointField) {
            if(colorField[(int)p.getX() + y][(int)p.getY()+ x] != Color.DARK_GRAY ){
                return  true;
            }
        }
        return  false;
    }

    // Setzt die X Coord
    public void setxCoord(int x) {
        if (!collisionAvoidence(xCoord + x , yCoord ))
            this.xCoord += x;

    }


    // Setz die Y Coord
    public void setyCoord(int y) {
        if (!collisionAvoidence(xCoord  , yCoord  + y)) {
            this.yCoord += y;
        }else {
            System.out.println("setYCord: Collision -> Thread interrupt");
            falling = false;
            setFieldTrue();
            this.interrupt();
        }
    }

    // Rotiert
    public  void rotate(int i){
        System.out.println("rotate: Rotiert");

        int newindex = (index + i) % 4;

        if(newindex < 0){
            newindex = 3;
        }

        Point2D[] pointFieldtry = pointField;
        pointField = CoordinatesOfForms.rotatetForm(form, newindex);

        if (collisionAvoidence(xCoord, yCoord)) {
            System.out.println("rotate: Rotate failed");
            pointField = pointFieldtry;

        }else {
            System.out.println("rotate: Rotate sucess");
            index = newindex;
        }
    }


    @Override
    public void run() {
        while (!interrupted()) {
            setyCoord(1);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                this.interrupt();
            }

        }

    }


    private void setFieldTrue() {
        for (int i = 0; i < pointField.length; i++) {
            colorField[(int) (yCoord + pointField[i].getX())][(int) (xCoord + pointField[i].getY())] = form.getC();
        }
        System.out.println("setFieldTrue: updated Binärfeld");

        clearRows();


    }

    private void clearRows(){
        boolean foundRow = false;
        int countDeleRows = 0;

        for(int y = colorField.length - 2; y > 0; y--){
            foundRow = false;

            for(int x = 1; x < colorField[y].length - 1; x++){
                if (colorField[y][x] == Color.DARK_GRAY) {
                        foundRow = true;
                        break;
                }
            }

            if(foundRow == false){
                deleteRow(y);
                y+=1;
                countDeleRows++;
            }
        }
        if(countDeleRows>0)
        TetrisGUI.calculateScore(countDeleRows);

    }

    public  void deleteRow(int y) {
        for (int j = y -1; j > 0; j--){
            for(int i = 1;i< colorField[j].length - 1;i++ ){
                colorField[j+1][i] = colorField[j][i];
            }
        }
    }
}
