package GUI;


import BL.ScoreTableModel;
import org.xml.sax.SAXException;
import res.Res;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import javax.xml.parsers.ParserConfigurationException;
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
    private JScrollPane psPane;
    private int Score;
    private JLabel lbBackground;
    private ScoreTableModel stm = new ScoreTableModel();
    private String[] sname = {"Rank", "User", "Score"};
    private DefaultTableColumnModel dtcm = new DefaultTableColumnModel();

    public GameOverGUI(int score) throws HeadlessException {
        Score = score;
        this.setSize(500,500);
    }

    public GameOverGUI() throws ParserConfigurationException, SAXException, IOException {

        initComponents();
        initTableColumns();
        stm.loadScores();
    }

    public void initComponents()
    {

        Dimension scrnSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle winSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        int taskBarHeight = scrnSize.height - winSize.height;

        int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 4;
        int height = (int)(((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - taskBarHeight) / 1.5);


        this.setSize(width+25, height+25);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);



        Container cont = this.getContentPane();
        btExit = new JButton();
        btBackToMenu = new JButton();
        tbTabelle = new JTable();
        psPane = new JScrollPane(tbTabelle);
        lbBackground = new JLabel();
        cont.setLayout(null);



        //String filename = System.getProperty("user.dir") + File.separator + "src" +
                //File.separator + "res" + File.separator + "Game_Over.jpg";


        Image img = null;
        try {
             img = ImageIO.read(Res.class.getResourceAsStream("Game_Over.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        tbTabelle.setModel(stm);
        //tbTabelle.setDefaultRenderer(Object.class, );
        System.out.println(width);
        System.out.println(height);
        lbBackground.setSize(width, height);
        lbBackground.setLayout(new BorderLayout());
        lbBackground.setOpaque(true);
        Image simg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        System.out.println(simg);
        System.out.println(lbBackground.getWidth());
        lbBackground.setIcon(new ImageIcon(simg));

        //lbBackground.setLocation(-15,-15);
        tbTabelle.setFillsViewportHeight(true);

        psPane.setSize(tbTabelle.getPreferredScrollableViewportSize());
        cont.add(psPane);
        System.out.println(width/2 - psPane.getWidth()/2);
        psPane.setLocation(width/2 - psPane.getWidth()/2, height/3);
        cont.add(lbBackground);

        btExit.setText("Exit");
        btBackToMenu.setText("Back to Menu");

        btExit.setSize(width/3, height/20);
        cont.add(btExit);
        btExit.setLocation(width/2 - btExit.getWidth()/2,height - height/3 + 10);

        btBackToMenu.setSize(width/3, height/20);
        cont.add(btBackToMenu);
        btBackToMenu.setLocation(width/2 - btExit.getWidth()/2,height - height/4 + 10);

        btExit.addActionListener(e -> {
            System.exit(0);
        });

        btBackToMenu.addActionListener(e -> {
            //Back to Menu einfach mit neuem Object und set visible true oderwas?
        });

    }

    public void initTableColumns()
    {
        int cnt = 0;
        int[] spalten = {50, 70, 70};
        for(String s:sname)
        {
            TableColumn tc = new TableColumn(cnt, spalten[cnt++]);
            tc.setHeaderValue(s);
            tc.setResizable(false);
            dtcm.addColumn(tc);
        }
        tbTabelle.setColumnModel(dtcm);
    }

    public static void main(String[] args) {
        GameOverGUI go = null;
        try {
            go = new GameOverGUI();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        go.setVisible(true);
    }

}


