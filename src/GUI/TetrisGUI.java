package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Chris on 13.03.2017.
 * GUI from The Game
 * drawing all forms in this class
 * 
 */
public class TetrisGUI extends JFrame{

    private String nickName;
    private JFrame startGUI;
    public TetrisGUI(String nickName,JFrame startGUI)
    {

        this.nickName=nickName;
        this.startGUI=startGUI;
        initialConfigs();
        initComponents();
    }

    private void initialConfigs() {
        Dimension scrnSize = Toolkit.getDefaultToolkit().getScreenSize();                                        //get Screensize to set the application size dynamic
        Rectangle winSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();          // get Height of Taskbar
        int taskBarHeight = scrnSize.height - winSize.height;                                                    // estiminate the height of the taskbar
        this.setTitle(nickName+"'s Game");
        int width=(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/3;                                 //set the Width to a third of the screen size
        int height=((int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()-taskBarHeight);                 //set the Height of the frame without the taskbar
        this.setSize(width,height);                                                                              //set Size of GUI
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);                                                      //disable the default close operation
        this.setLocationRelativeTo(null);                                                                        //
        this.addWindowListener(new WindowAdapter() {                                                             //Add a Actionlistener to rework the Close Operation
            @Override
            public void windowClosing(WindowEvent e) {
                startGUI.setVisible(true);                                                                       //set the main gui visible again and close the current window
                dispose();

            }
        });
    }

    private void initComponents() {


    }






    /**private void onClose() {

        startGUI.setTitle("normalerweise zu");
        startGUI.setVisible(false);

    }*/


}
