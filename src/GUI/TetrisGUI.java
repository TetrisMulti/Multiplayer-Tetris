package GUI;

import BL.PunkteCalculator;
import Beans.Forms;
import BL.TetrisForm;
import Beans.Score;
import Renderer.PanelRenderer;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;


/**
 * Created by Chris on 13.03.2017.
 * GUI from The Game
 * drawing all forms in this class
 */
public class TetrisGUI extends JFrame implements ActionListener {

    private String nickName;
    private JFrame startGUI;
    private int screenWidth;
    private int screenHeight;
    public int widthOfOneField;
    public int heightOfOneField;
    private TetrisForm aktivForm;
    private static PanelRenderer rend;
    public static TetrisGUI tetrisGui;
    private Timer timer;
    private boolean firstActive = true;
    public Graphics2D g2;
    public int counter;
    public Color[][] colorField;
    private Forms[] formsQueue;
    private Boolean firstTime;
    private NewFormAndScoreDialog dialog;
    public static Score score;
    private HashMap<String, Integer> hmKeys;
    private boolean gameover;
    public static int time;
    public static int level;


    public TetrisGUI(String nickName, JFrame startGUI, HashMap<String, Integer> hmKeys) {

        this.nickName = nickName;
        this.startGUI = startGUI;
        counter = 0;
        colorField = new Color[20][12];
        tetrisGui = this;
        timer = new Timer(20, this);
        formsQueue=new Forms[2];
        firstTime=true;
        score = new Score(nickName,0);
        initialConfigs();
        this.hmKeys=hmKeys;
        gameover=false;
        time =500;
        level=1;
    }

    /**
     * set GUI size
     * calculate Height and Width of one Field
     * call addListener Method
     * ask for playing with a XBox Controller
     * initialize Colorfield
     * start timer
     */
    private void initialConfigs() {
        Dimension scrnSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle winSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        int taskBarHeight = scrnSize.height - winSize.height;
        this.setTitle(nickName + "'s Game");
        this.setResizable(false);
        screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 3;
        screenHeight = ((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - taskBarHeight);
        widthOfOneField = screenWidth / 12;
        heightOfOneField = screenHeight / 20;
        this.setSize(screenWidth, screenHeight);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setLocationRelativeTo(null);

        addListener();

        this.rend = new PanelRenderer();
        this.add(rend);

        for (int i = 0; i < colorField.length; i++) {
            colorField[i][colorField[i].length - 1] = Color.BLACK;
            colorField[i][0] = Color.BLACK;
        }

        for (int i = 0; i < colorField[0].length; i++) {
            colorField[colorField.length-1][i] = Color.BLACK;
        }

        for (int y = 0; y < colorField.length-1;y++){
            for(int x = 1; x <colorField[y].length-1;x++){
                colorField[y][x] = Color.DARK_GRAY;
            }
        }
        timer.start();
    }

    /**
     * add all Keylistener and Windowlistner to the JFrame
     * call method onClose
     */
    private void addListener() {
        this.addWindowListener(new WindowAdapter() {                                                             //Add a Actionlistener to rework the Close Operation
            @Override
            public void windowClosing(WindowEvent e) {
               onClose();


            }
        });
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            /**
             * go over switch to compare keycodes with event
             * @param e
             */
            @Override
            public void keyPressed(KeyEvent e) {

                for (Iterator it = hmKeys.keySet().iterator(); it.hasNext();) {
                    String key = (String) it.next();
                    if (e.getKeyCode() == hmKeys.get((key) ))
                    {
                        switch (key)
                        {
                            case "down":
                                aktivForm.setyCoord(1);
                                break;
                            case "left":
                                aktivForm.setxCoord(-1);
                                break;
                            case "right":
                                aktivForm.setxCoord(1);
                                break;
                            case "rotateLeft":
                                aktivForm.rotate(-1);
                                break;
                            case "rotateRight":
                                aktivForm.rotate(1);
                                break;
                            default:
                                System.out.println("Error in void keyPressed");
                                break;
                        }
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

    }

    /**
     * stop timer, stop thread and delete all object on close
     * open startGui on close
     */
    public void onClose() {
        if(!gameover)
        startGUI.setVisible(true);
        aktivForm.interrupt();
        timer.stop();
        dialog.dispose();
        dialog=null;
        dispose();
    }

    /**
     *method that paint finished forms and call draw method form aktivform
     * @param g
     */
    public void repaint(Graphics g) {
        g2 = (Graphics2D) g;

        for (int i = 0; i < colorField.length; i++) {
            for (int j = 0; j < colorField[i].length; j++) {
                    g2.setColor(colorField[i][j]);
                    RoundRectangle2D rr = new RoundRectangle2D.Float(widthOfOneField * j, (heightOfOneField * i) + 2, widthOfOneField - 2, heightOfOneField - 2, 10, 10);
                    g2.fill(rr);
            }
        }
        if (!firstActive) {
            aktivForm.draw(g2);
        }
    }

    /**
     *create new Form and start the thread
     * detect exception for gameover
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (firstActive) {
            try {
                newForm();
                aktivForm = new TetrisForm(5, 0, widthOfOneField, heightOfOneField, formsQueue[0],colorField,time);
                aktivForm.start();

            } catch (Exception ex) {
              this.gameOver();
            }
            firstActive = false;
        }

        if (!TetrisForm.falling) {
            firstActive = true;
        }
        rend.repaint();
    }

    /**
     * method gets called when game is over and the gameovergui sets visible and Tetrisgui gets closed
     */
    private void gameOver() {

        JOptionPane.showMessageDialog(this, "Gameover");
           try {
               GameOverGUI g = new GameOverGUI(score, startGUI);
               g.setVisible(true);
           }catch(Exception ex)
           {
               ex.printStackTrace();
           }
           gameover=true;
        onClose();
    }

    /**
     * Calculate Score and write it to the score object
     * @param x -> rowcount
     */
    public static void calculateScore(int x)
    {
        score.setScore(score.getScore()+ PunkteCalculator.calculateRowPoints(x));
        if(score.getScore()/(100*level)>level) {
            level++;
            if(time>=50)
            {
                time-=50;
            }
        }
    }

    /**
     * creates 2 new random forms on first start
     * has always the next form in queue for the newformdialog
     * creates object of the dialog class
     */
    public void newForm() {
        Random rand = new Random();
         if(firstTime)
        {
            formsQueue[0]=Forms.values()[rand.nextInt(Forms.values().length)];
            formsQueue[1]=Forms.values()[rand.nextInt(Forms.values().length)];
            int dialogLocation = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2+screenWidth/2);
            dialog=new NewFormAndScoreDialog(widthOfOneField*6,screenHeight,formsQueue[1],widthOfOneField,heightOfOneField,dialogLocation,score);
            firstTime=false;
            this.setAutoRequestFocus(true);
        }
        else
        {
            formsQueue[0]=formsQueue[1];
            formsQueue[1]=Forms.values()[rand.nextInt(Forms.values().length)];
            dialog.setForm(formsQueue[1]);
            dialog.setScore(score);
            dialog.setLevel(level);
        }


    }

}

