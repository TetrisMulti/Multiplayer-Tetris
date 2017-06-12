package GUI;

import BL.PunkteCalculator;
import Beans.Forms;
import BL.TetrisForm;
import Beans.Score;
import Renderer.PanelRenderer;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import java.util.LinkedList;
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
    public LinkedList<TetrisForm> fertigListe;
    public Graphics2D g2;
    public int counter;
    public Color[][] colorField;
    private Forms[] formsQueue;
    private Boolean firstTime;
    private NewFormAndScoreDialog dialog;
    public static Score score;


    public TetrisGUI(String nickName, JFrame startGUI) {

        this.nickName = nickName;
        this.startGUI = startGUI;
        counter = 0;
        colorField = new Color[20][12];
        tetrisGui = this;
        timer = new Timer(20, this);
        fertigListe = new LinkedList<>();
        formsQueue=new Forms[2];
        firstTime=true;
        score = new Score(nickName,0);
        initialConfigs();


    }


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

        // printFeld();
        timer.start();

    }

    private void addListener() {
        this.addWindowListener(new WindowAdapter() {                                                             //Add a Actionlistener to rework the Close Operation
            @Override
            public void windowClosing(WindowEvent e) {
                startGUI.setVisible(true);
                aktivForm.interrupt();
                //set the main gui visible again and close the current window
                //onClose();
                timer.stop();
                dialog.dispose();
                dialog=null;
                dispose();


            }
        });
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_A:
                        aktivForm.setxCoord(-1);
                        break;
                    case KeyEvent.VK_D:
                        aktivForm.setxCoord(1);
                        break;
                    case KeyEvent.VK_S:
                        aktivForm.setyCoord(1);
                        break;
                    case KeyEvent.VK_Q:
                        aktivForm.rotate(-1);
                        break;
                    case KeyEvent.VK_E:
                        aktivForm.rotate(1);
                        break;
                }
            }


            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }


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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (firstActive) {
            try {
                newForm();
                System.out.println("1");
                aktivForm = new TetrisForm(5, 0, widthOfOneField, heightOfOneField, formsQueue[0],colorField);
                System.out.println("2");
                aktivForm.start();

            } catch (Exception ex) {
              this.gameOver();
            }
            firstActive = false;
        }

        if (!TetrisForm.falling) {
            fertigListe.add(aktivForm);
            firstActive = true;
        }
        rend.repaint();
    }


    private void gameOver() {

        JOptionPane.showMessageDialog(this, "Gameover");
        this.timer.stop();

           try {
               GameOverGUI g = new GameOverGUI(score, startGUI);
               g.setVisible(true);
           }catch(Exception ex)
           {
               ex.printStackTrace();
           }


        //this.dispose();
    }

    public static void calculateScore(int x)
    {
        score.setScore(score.getScore()+ PunkteCalculator.calculateRowPoints(x));

    }

    public void newForm() {
        System.out.println("newForm: Setzt neue Form");
        Random rand = new Random();
         if(firstTime)
        {
            formsQueue[0]=Forms.values()[rand.nextInt(Forms.values().length)];
            formsQueue[1]=Forms.values()[rand.nextInt(Forms.values().length)];
            int dialogLocation = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2+screenWidth/2);
            dialog=new NewFormAndScoreDialog(widthOfOneField*6,screenHeight,formsQueue[1],widthOfOneField,heightOfOneField,dialogLocation,score);
            System.out.println("newForm: -> erstes mal");
            firstTime=false;
            this.setAutoRequestFocus(true);
        }
        else
        {
            System.out.println("else");
            formsQueue[0]=formsQueue[1];
            formsQueue[1]=Forms.values()[rand.nextInt(Forms.values().length)];
            dialog.setForm(formsQueue[1]);
            dialog.setScore(score);
        }


    }

}

