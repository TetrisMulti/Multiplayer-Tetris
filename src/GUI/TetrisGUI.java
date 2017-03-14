package GUI;

import BL.TetrisThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.RoundRectangle2D;

/**
 * Created by Chris on 13.03.2017.
 * GUI from The Game
 * drawing all forms in this class
 */
public class TetrisGUI extends JFrame {

    private String nickName;
    private JFrame startGUI;
    private JPanel pa;
    private int screenWidth;
    private int screenHeight;
    private  Container cont;
    private boolean [][] fields;

    public TetrisGUI(String nickName, JFrame startGUI) {

        this.nickName = nickName;
        this.startGUI = startGUI;
        fields=new boolean[20][12];
        initialConfigs();
      //  initComponents();

      //  paintComponents(pa.getGraphics());
    }



    private void initialConfigs() {
        Dimension scrnSize = Toolkit.getDefaultToolkit().getScreenSize();                                               //get Screensize to set the application size dynamic
        Rectangle winSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();                 // get Height of Taskbar
        int taskBarHeight = scrnSize.height - winSize.height;                                                           // estiminate the height of the taskbar
        this.setTitle(nickName + "'s Game");
        screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 3;                                   //set the Width to a third of the screen size
        screenHeight = ((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - taskBarHeight);                   //set the Height of the frame without the taskbar
        this.setSize(screenWidth, screenHeight);                                                                                    //set Size of GUI
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);                                                             //disable the default close operation
        this.setLocationRelativeTo(null);                                                                               //
        this.addWindowListener(new WindowAdapter() {                                                             //Add a Actionlistener to rework the Close Operation
            @Override
            public void windowClosing(WindowEvent e) {
                startGUI.setVisible(true);                                                                              //set the main gui visible again and close the current window
                dispose();

            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2=(Graphics2D) g;
        g2.setColor(Color.BLACK);

        int widthOfOneField = screenWidth/12;
        int heightOfOneField = screenHeight/20;
        for (int i=0;i<12;i++)
        {

            g2.drawLine(widthOfOneField*i,0,widthOfOneField*i,screenHeight);
        }
        for (int i=0;i<20;i++)
        {

            g2.drawLine(0,heightOfOneField*i,screenWidth,heightOfOneField*i);
        }
        g2.setColor(Color.BLUE);
        //RoundRectangle2D rr = new RoundRectangle2D.Float(200,200,widthOfOneField-10,heightOfOneField-10,10,10);
        //g2.fill(rr);
        for(int i=0;i<20;i++)
        {
           // System.out.println("i: "+i);
            for(int y=0;y<12;y++)
            {
              //  System.out.println("y: "+y);

                if(y==0||y==11)
                {
                    //System.out.println("drinnen bei y: "+y+" i: "+i);
                    //System.out.println("startx: "+(screenWidth*y-10)+" starty :"+(screenHeight*i-10));
                    fields[i][y]=true;
                    RoundRectangle2D rr = new RoundRectangle2D.Float(widthOfOneField*y,heightOfOneField*i,widthOfOneField,heightOfOneField,10,10);
                    g2.fill(rr);
                }
                if(i==19)
                {
                    RoundRectangle2D rr = new RoundRectangle2D.Float(widthOfOneField*y,heightOfOneField*i,widthOfOneField,heightOfOneField,10,10);
                    g2.fill(rr);
                }
            }
        }
    }



















    /**  private void initComponents() {
        cont = this.getContentPane();
        cont.setLayout(new GridLayout(1, 1));
        pa = new JPanel();


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

    public void paint(Graphics g) {
        super.paint(g);
        //TetrisThread tt = new TetrisThread(pa,screenWidth,screenHeight);
        //Thread thread = new Thread(tt);
        //thread.start();


    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);

        System.out.println("2");
        for(int i=0;i<10;i++)
        {
            System.out.println("width: "+screenWidth+"\n i: "+i);
            g.setColor(Color.BLACK);
            g.drawLine(screenWidth/10*i,0,screenWidth/10*i,screenHeight);
            //g.fillRect(screenWidth/10*i,0,300,300);
        }
        cont.add(pa, 0);

    }
}*/
