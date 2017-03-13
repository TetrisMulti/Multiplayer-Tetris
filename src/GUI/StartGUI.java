package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Christoph on 13.03.2017.
 */
public class StartGUI extends JFrame{


    public StartGUI()
    {
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
        this.setLocationRelativeTo(null);                                                                          //set Location to the Center of the Screen
    }

    public static void main(String[] args) {
        StartGUI gui = new StartGUI();
        gui.setVisible(true);
    }
}
