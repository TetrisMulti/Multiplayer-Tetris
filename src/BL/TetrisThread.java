package BL;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Chris on 13.03.2017.
 */
public class TetrisThread implements Runnable {

    private JPanel pa;
    private int screenWidth;
    private int screenHeight;
    public TetrisThread(JPanel pa,int screenWidth,int screenHeight) {
        this.pa = pa;
        this.screenWidth=screenWidth;
        this.screenHeight=screenHeight;
    }


    @Override
    public void run() {
        Graphics g = pa.getGraphics();
        System.out.println("2");
        for(int i=0;i<10;i++)
        {
            System.out.println("width: "+screenWidth+"\n i: "+i);
            g.setColor(Color.BLACK);
            //g.drawLine(screenWidth/10*i,0,screenWidth/10*i,screenHeight);
            g.fillRect(screenWidth/10*i,0,300,300);
        }
        pa.repaint();
        pa.revalidate();
    }
}
