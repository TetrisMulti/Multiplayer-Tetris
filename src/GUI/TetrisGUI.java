package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Chris on 13.03.2017.
 * GUI from The Game
 * drawing all forms in this class
 * 
 */
public class TetrisGUI extends JFrame{

    private String nickName; 
    public TetrisGUI(String nickName)
    {
        this.nickName=nickName;
        initComponents();
    }

    private void initComponents() {
        Dimension scrnSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle winSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();            // get Height of Taskbar
        int taskBarHeight = scrnSize.height - winSize.height;

        int width=(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/3;
        int height=((int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()-taskBarHeight)/2;                     //get Height and Width of Screen
        this.setSize(width,height);                                                                                //set Size of GUI
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);                                                              //
        this.setLocationRelativeTo(null);
    }


}
