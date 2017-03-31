package GUI;


import res.Res;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by ganleb13 on 16.03.2017.
 * In this class we will define the GameOver-Screen
 */
public class GameOverGUI extends JFrame{

    private JTable tbTabelle;
    private JButton btExit;
    private JButton btBackToMenu;
    private JLabel lbImg;
    private JScrollPane psPane;
    private int Score;

    public GameOverGUI(int score) throws HeadlessException {
        Score = score;
        this.setSize(500,500);
    }

    public GameOverGUI(){

        initComponents();
    }

    public void initComponents()
    {

        Dimension scrnSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle winSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        int taskBarHeight = scrnSize.height - winSize.height;

        int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 4;
        int height = (int)(((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - taskBarHeight) / 1.5);


        this.setSize(width, height);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);



        Container cont = this.getContentPane();
        btExit = new JButton();
        btBackToMenu = new JButton();
        lbImg = new JLabel();
        tbTabelle = new JTable();
        psPane = new JScrollPane();
        cont.setLayout(null);



        //String filename = System.getProperty("user.dir") + File.separator + "src" +
                //File.separator + "res" + File.separator + "Game_Over.jpg";


        Image img = null;
        try {
             img = ImageIO.read(Res.class.getResourceAsStream("Game_Over.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(width);
        System.out.println(height);
        lbImg.setSize(width, height/3);
        Image simg = img.getScaledInstance(lbImg.getWidth(), lbImg.getHeight(), Image.SCALE_SMOOTH);

        System.out.println(simg);

        lbImg.setIcon(new ImageIcon(simg));
        cont.add(lbImg);
        lbImg.setLocation(0,0);

        psPane.add(tbTabelle);
        psPane.setSize((int) (width/1.2), height/3);
        cont.add(psPane);
        System.out.println(width/2 - psPane.getWidth()/2);
        psPane.setLocation(width/2 - psPane.getWidth()/2, psPane.getHeight()/2);



    }

    public static void main(String[] args) {
        GameOverGUI go = new GameOverGUI();
        go.setVisible(true);
    }

}


