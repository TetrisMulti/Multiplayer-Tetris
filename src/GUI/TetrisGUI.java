package GUI;

import Beans.Forms;
import BL.TetrisForm;
import Renderer.PanelRenderer;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
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
    public static boolean[][] fields;
    public int widthOfOneField;
    public int heightOfOneField;
    private TetrisForm aktivForm;
    private static PanelRenderer rend;
    public static TetrisGUI tetrisGui;
    private Timer timer;
    private boolean firstActive = true;
    public static LinkedList<TetrisForm> fertigListe;
    public static Graphics2D g2;
    public static int counter;
    public static int removedRows;
    public static Color[][] colorField;
    private HashMap<String, Integer> hmKeys;

    public TetrisGUI(String nickName, JFrame startGUI, HashMap<String, Integer> hmKeys) {

        this.nickName = nickName;
        this.startGUI = startGUI;
        counter = 0;
        fields = new boolean[20][12];
        colorField = new Color[20][12];
        tetrisGui = this;
        timer = new Timer(20, this);
        fertigListe = new LinkedList<>();
        initialConfigs();
        removedRows=0;
        this.hmKeys = hmKeys;

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
            colorField[i][colorField[i].length - 1] = Color.BLACK;
            colorField[i][0] = Color.BLACK;

        }

        for (int i = 0; i < fields[0].length; i++) {

            fields[fields.length - 1][i] = true;
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
                dispose();


            }
        });
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override                                       // Mit switch die KeyCodes mit dem Event vergleichen
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

    public static void printFeld() {
        System.out.println("printFeld: Ausgabe Binärfeld");
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
                aktivForm = new TetrisForm(5, 0, widthOfOneField, heightOfOneField, newForm(), counter++);
            } catch (Exception e1) {
              this.gameOver();
            }
            aktivForm.start();
            firstActive = false;
        }

        if (!TetrisForm.falling) {
            fertigListe.add(aktivForm);
            firstActive = true;
        }
        rend.repaint();
    }


    private void gameOver() {

        JOptionPane.showMessageDialog(tetrisGui, "Gameover");
        try {
            GameOverGUI g = new GameOverGUI();
            g.setVisible(true);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }


        //this.dispose();
    }

    public Forms newForm() {
        System.out.println("newForm: Setzt neue Form");
        Random rand = new Random();

       return Forms.values()[rand.nextInt(Forms.values().length)];
    }

}

