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

    }
}
