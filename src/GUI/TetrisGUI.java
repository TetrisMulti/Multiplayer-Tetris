package GUI;

import BL.TetrisForm;
import BL.TetrisThread;
import Renderer.PanelRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.util.LinkedList;


/**
 * Created by Chris on 13.03.2017.
 * GUI from The Game
 * drawing all forms in this class
 */
public class TetrisGUI extends JFrame implements ActionListener  {

    private String nickName;
    private JFrame startGUI;
    // private JPanel pa;
    private int screenWidth;
    private int screenHeight;
    //private Container cont;
    public static boolean[][] fields;
    //private boolean first=true;
    //private   Thread t;
    public int widthOfOneField;
    public int heightOfOneField;
    //private boolean falling = true;
    private TetrisForm aktivForm;
    private PanelRenderer rend;
    public static TetrisGUI tetrisGui;
    private Timer timer;
    private boolean firstActive = true;
    private LinkedList<TetrisForm> fertigListe;

    public TetrisGUI(String nickName, JFrame startGUI) {

        this.nickName = nickName;
        this.startGUI = startGUI;
        fields = new boolean[20][12];
        tetrisGui = this;
        timer = new Timer(20, this);
        fertigListe = new LinkedList<>();
        initialConfigs();
        //  initComponents();

        //  paintComponents(pa.getGraphics());
    }


    private void initialConfigs() {
        Dimension scrnSize = Toolkit.getDefaultToolkit().getScreenSize();                                               //get Screensize to set the application size dynamic
        Rectangle winSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();                 // get Height of Taskbar
        int taskBarHeight = scrnSize.height - winSize.height;                                                           // estiminate the height of the taskbar
        this.setTitle(nickName + "'s Game");
        this.setResizable(false);
        screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 3;                                   //set the Width to a third of the screen size
        screenHeight = ((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - taskBarHeight);                   //set the Height of the frame without the taskbar
        widthOfOneField = screenWidth / 12;
        heightOfOneField = screenHeight / 20;
        this.setSize(screenWidth, screenHeight);                                                                                    //set Size of GUI
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);                                                             //disable the default close operation
        this.setLocationRelativeTo(null);                                                                           //
        addListener();



        this.rend = new PanelRenderer();
        this.add(rend);

        for (int i = 0; i < fields.length; i++) {

            fields[i][fields[i].length - 1] = true;
            fields[i][0] = true;

        }

        for (int i = 0; i < fields[0].length; i++) {

            fields[fields.length - 1][i] = true;

        }

        printFeld();
        timer.start();

    }

    private void addListener() {
        this.addWindowListener(new WindowAdapter() {                                                             //Add a Actionlistener to rework the Close Operation
            @Override
            public void windowClosing(WindowEvent e) {
                startGUI.setVisible(true);
                aktivForm.stop();                                                                                            //set the main gui visible again and close the current window
                dispose();

            }
        });
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch(e.getKeyCode())
                {
                    case KeyEvent.VK_A: aktivForm.setxCoord(-1);break;
                    case KeyEvent.VK_D: aktivForm.setxCoord(1);break;
                }
            }
        });

    }


    public void printFeld() {
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length; j++) {

                if (fields[i][j])
                    System.out.print(1);
                else
                    System.out.print(0);
            }
            System.out.println();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);


//
        /*
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length; j++) {

                g2.setColor(Color.WHITE);
                g2.drawRect(j * widthOfOneField, i * heightOfOneField, widthOfOneField, heightOfOneField);

            }

        }*/


        /*
        for (int i=0;i<12;i++)
        {

            g2.drawLine(widthOfOneField*i,0,widthOfOneField*i,screenHeight);
        }
        for (int i=0;i<20;i++)
        {

            g2.drawLine(0,heightOfOneField*i,screenWidth,heightOfOneField*i);
        }
        g2.setColor(Color.gray);
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
                    RoundRectangle2D rr = new RoundRectangle2D.Float(widthOfOneField*y,heightOfOneField*i+2,widthOfOneField-2,heightOfOneField-2,10,10);
                    g2.fill(rr);
                }
                if(i==19)
                {
                    RoundRectangle2D rr = new RoundRectangle2D.Float(widthOfOneField*y,heightOfOneField*i+2,widthOfOneField-2,heightOfOneField-2,10,10);
                    g2.fill(rr);
                }
            }
        }
        */
        //
        //  if(first)
        //{
        //TetrisThread tt = new TetrisThread(g2,screenWidth,screenHeight,widthOfOneField,heightOfOneField,fields);
        //t = new Thread(tt);
        //t.start();
        //first=false;
        // startGame(g2,screenWidth,screenHeight,widthOfOneField,heightOfOneField,fields);

        // }

    }


    public void repaint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;


        g.setColor(Color.BLACK);
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length; j++) {
                if (fields[i][j]) {
                    g2.setColor(Color.BLACK);
                    g2.fillRect(j * widthOfOneField, i * heightOfOneField, widthOfOneField, heightOfOneField);
                } else {
                    g2.setColor(Color.DARK_GRAY);
                    g2.fillRect(j * widthOfOneField, i * heightOfOneField, widthOfOneField, heightOfOneField);
                }
            }
        }

        for (TetrisForm tf : fertigListe) {
            tf.draw(g2);
        }

        if (!firstActive) {
            aktivForm.draw(g2);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (firstActive) {
            aktivForm = new TetrisForm(6, 1, widthOfOneField, heightOfOneField);
            aktivForm.start();
            firstActive = false;
        }

        if (!TetrisForm.falling) {
            fertigListe.add(aktivForm);
            fields[aktivForm.getyCoord()][aktivForm.getxCoord()] = true;
            firstActive = true;
        }

        rend.repaint();
    }}

    //<editor-fold desc="oldCode">
 /*   private void startGame(Graphics2D g2, int screenWidth, int screenHeight, int widthOfOneField, int heightOfOneField, boolean[][] fields) {

        //new Thread(()->{
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
                    break;
                }

                RoundRectangle2D rr = new RoundRectangle2D.Float(widthOfOneField*y,(heightOfOneField*i)+2,widthOfOneField-2,heightOfOneField-2,10,10);
                g2.fill(rr);
                printFeld();
                System.out.println("startx: "+widthOfOneField*y+" starty :"+((heightOfOneField*i)+2));
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
       // }
        //).start();
    }

*/
    //</editor-fold>



//<editor-fold desc="oldCode">
    /*  private void initComponents() {
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



}



 * private void onClose() {
 * <p>
 * startGUI.setTitle("normalerweise zu");
 * startGUI.setVisible(false);
 * <p>
 * }
 * <p>
 * public void paint(Graphics g) {
 * super.paint(g);
 * //TetrisThread tt = new TetrisThread(pa,screenWidth,screenHeight);
 * //Thread thread = new Thread(tt);
 * //thread.start();
 * <p>
 * <p>
 * }
 *
 * @Override public void paintComponents(Graphics g) {
 * super.paintComponents(g);
 * <p>
 * System.out.println("2");
 * for(int i=0;i<10;i++)
 * {
 * System.out.println("width: "+screenWidth+"\n i: "+i);
 * g.setColor(Color.BLACK);
 * g.drawLine(screenWidth/10*i,0,screenWidth/10*i,screenHeight);
 * //g.fillRect(screenWidth/10*i,0,300,300);
 * }
 * cont.add(pa, 0);
 * <p>
 * }
 * }
 */
//</editor-fold>
