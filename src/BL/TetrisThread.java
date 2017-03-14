package BL;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * Created by Chris on 13.03.2017.
 */
public class TetrisThread implements Runnable {

    private Graphics2D g2;
    private int screenWidth;
    private int screenHeight;
    private int widthOfOneField;
    private int heightOfOneField;
    private boolean [][] fields;

    public TetrisThread(Graphics2D g2, int screenWidth, int screenHeight, int widthOfOneField, int heightOfOneField,boolean[][] fields) {
        this.g2 = g2;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.widthOfOneField = widthOfOneField;
        this.heightOfOneField = heightOfOneField;
        this.fields=fields;
    }

    @Override
    public void run() {
       int i=0;
       int y=5;
        System.out.println("adsf");
        System.out.println("width: "+widthOfOneField+" heihgth: "+heightOfOneField);
        while(fields[i][y]==false)
        {
            i++;
            if(fields[i+1][y]==true)
            {
                fields[i][y]=true;
            }

            RoundRectangle2D rr = new RoundRectangle2D.Float(widthOfOneField*y,(heightOfOneField*i)+2,widthOfOneField-2,heightOfOneField-2,10,10);
            g2.fill(rr);
            System.out.println("startx: "+widthOfOneField*y+" starty :"+((heightOfOneField*i)+2));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(i==19)break;

        }
    }
}
