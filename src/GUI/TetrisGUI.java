package GUI;

import BL.TetrisThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Chris on 13.03.2017.
 * GUI from The Game
 * drawing all forms in this class
 */
public class TetrisGUI extends JFrame {

    private String nickName;
    private JFrame startGUI;
    private JPanel pa;

    public TetrisGUI(String nickName, JFrame startGUI) {

        this.nickName = nickName;
        this.startGUI = startGUI;
        initialConfigs();
        initComponents();

    }



    private void initialConfigs() {
        Dimension scrnSize = Toolkit.getDefaultToolkit().getScreenSize();                                        //get Screensize to set the application size dynamic
        Rectangle winSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();          // get Height of Taskbar
        int taskBarHeight = scrnSize.height - winSize.height;                                                    // estiminate the height of the taskbar
        this.setTitle(nickName + "'s Game");
        int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 3;                                 //set the Width to a third of the screen size
        int height = ((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - taskBarHeight);                 //set the Height of the frame without the taskbar
        this.setSize(width, height);                                                                              //set Size of GUI
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
        Container cont = this.getContentPane();
        cont.setLayout(new GridLayout(1, 1));
        pa = new JPanel();
        cont.add(pa, 0);

/**        System.out.println("1");
 JPanel pa = new JPanel();
 System.out.println("2");
 cont.add(pa,0);
 System.out.println("3");
 Graphics g = pa.getGraphics();
 System.out.println("4");
 g.setColor(Color.BLACK);
 System.out.println("5");
 g.fillRect(10,10,10,10);

 JPanel pa = new JPanel(){

@Override protected void paintComponent(Graphics g) {
super.paintComponent(g);
g.setColor(Color.BLACK);
int width = pa.getWidth();
int height = pa.getHeight();
for (int i=0;i<10;i++)
{
//g.drawLine(0,width/10*i,height,width/10*i);
g.drawLine(width/10*i,0,width/10*i,height);
}
for (int i=0;i<20;i++)
{
//g.drawLine(0,width/10*i,height,width/10*i);
g.drawLine(0,height/20*i,width,height/20*i);
}
}
};
 cont.add(pa,0);
 */


    }


    /**
     * private void onClose() {
     * <p>
     * startGUI.setTitle("normalerweise zu");
     * startGUI.setVisible(false);
     * <p>
     * }
     */
    public void paint(Graphics g) {
        super.paint(g);
        TetrisThread tt = new TetrisThread(pa);
        Thread thread = new Thread(tt);
        thread.start();
    }

}
