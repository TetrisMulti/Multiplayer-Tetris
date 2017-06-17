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
    private Point2D[] pointField;
    private Color[][] colorField;
    private int timetoFall;


    public TetrisForm(int xCoord, int yCoord, int widthOfBlock, int heightOfBlock, Forms form,Color[][] colorField,int time) throws Exception {
        this.widthOfBlock = widthOfBlock;
        this.heightOfBlock = heightOfBlock;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.falling = true;
        this.form = form;
        this.pointField = CoordinatesOfForms.getPointCoords(form);
        this.colorField=colorField;
        this.timetoFall=time;
        if(collisionAvoidence(xCoord, yCoord)){
           throw  new Exception();
        }
    }
    /**
     *  draw new Forms  with Graphics 2D
     * @param g2
     */
    public void draw(Graphics2D g2) {
        g2.setColor(form.getC());

        for (int i = 0; i < pointField.length; i++) {
            if (pointField[i].getX() != -1) {
                RoundRectangle2D rr = new RoundRectangle2D.Float((int) (xCoord * widthOfBlock + (pointField[i].getY() * widthOfBlock)), (int) ((yCoord * heightOfBlock) + 2 + (pointField[i].getX() * heightOfBlock)),
                        widthOfBlock - 2, heightOfBlock - 2, 10, 10);
                g2.fill(rr);
            }
        }

    }


    /**
     *   Proove Collision
     * @param x -> xCoord of Form
     * @param y -> yCoord of Form
     * @return boolean for collision
     */
    public boolean collisionAvoidence(int x, int y){
        for (Point2D p : pointField) {
            if(colorField[(int)p.getX() + y][(int)p.getY()+ x] != Color.DARK_GRAY ){
                return  true;
            }
        }
        return  false;
    }

    /**
     * set X Coord and proove collision
     * @param x
     */
    public void setxCoord(int x) {
        if (!collisionAvoidence(xCoord + x , yCoord ))
            this.xCoord += x;

    }


    /**
     *    set Y Coord and detect if form is on the bottom
     * @param y
     */
    public void setyCoord(int y) {
        if (!collisionAvoidence(xCoord  , yCoord  + y)) {
            this.yCoord += y;
        }else {
            falling = false;
            setFieldTrue();
            this.interrupt();
        }
    }

    /**
     * rotated forms
     * @param i -> direction of rotation
     */
    public  void rotate(int i){

        if(form!=Forms.BLOCK)
        {
        Point2D[] pointFieldtry = pointField;
        pointField = CoordinatesOfForms.rotateForm(pointField, i);

        if (collisionAvoidence(xCoord, yCoord)) {
            pointField = pointFieldtry;

        }}
    }

    /**
     *     run method, increase yCoord for falling
     */
    @Override
    public void run() {
        while (!interrupted()) {
            setyCoord(1);

            try {
                Thread.sleep(timetoFall);
            } catch (InterruptedException e) {
                this.interrupt();
            }
        }
    }

    /**
     *   update colorfield with the new form for collision detection
     */
    private void setFieldTrue() {
        for (int i = 0; i < pointField.length; i++) {
            colorField[(int) (yCoord + pointField[i].getX())][(int) (xCoord + pointField[i].getY())] = form.getC();
        }
        clearRows();


    }

    /**
     * detect if row is full and needs to be deleted + gives the deleted rows to TetrisGUI for Score calculating
     */
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

    /**
     * delete rows
     * @param y -> row
     */
    public  void deleteRow(int y) {
        for (int j = y -1; j > 0; j--){
            for(int i = 1;i< colorField[j].length - 1;i++ ){
                colorField[j+1][i] = colorField[j][i];
            }
        }
    }
}
