package GUI;


import BL.ScoreTableModel;
import Beans.ScoreTable;
import org.xml.sax.SAXException;
import res.Res;
import Renderer.ScoreRenderer;
import Beans.Score;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.IOException;
import Renderer.ScoreTableCellRenderer;

/**
 * Created by ganleb13 on 16.03.2017.
 * In this class we will define the GameOver-Screen
 */
public class GameOverGUI extends JDialog{

    private JButton btExit;
    private JButton btBackToMenu;
    private JScrollPane psPane;
    private ScoreTable tbTabelle;
    private int Score;
    private JLabel lbBackground;
    private JPanel pnTable;
    private StartGUI sgui;

    public GameOverGUI(Score score, StartGUI sgui) throws HeadlessException {
        tbTabelle.addScore(score);
        this.sgui = sgui;
    }



    public GameOverGUI() throws ParserConfigurationException, SAXException, IOException {
        initComponents();
    }


    public void initComponents() throws ParserConfigurationException, SAXException, IOException {
        Dimension scrnSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle winSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        int taskBarHeight = scrnSize.height - winSize.height;

        int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 4;
        int height = (int)(((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - taskBarHeight) / 1.5);


        this.setSize(width, height);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Game Over");

        Container cont = this.getContentPane();
        btExit = new JButton();
        btBackToMenu = new JButton();
        tbTabelle = new ScoreTable();
        psPane = new JScrollPane(tbTabelle);
        lbBackground = new JLabel();
        pnTable = new JPanel();
        cont.setLayout(null);

        //String filename = System.getProperty("user.dir") + File.separator + "src" +
                //File.separator + "res" + File.separator + "Game_Over.jpg";

        Image img = null;
        try {
             img = ImageIO.read(Res.class.getResourceAsStream("Game_Over.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("PanelWidth: "+width);
        System.out.println("Panelheight: "+height);
        lbBackground.setSize(width, height);
        lbBackground.setLayout(new BorderLayout());
        lbBackground.setOpaque(true);
        Image simg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        lbBackground.setIcon(new ImageIcon(simg));

        tbTabelle.initTableColumns(new int[]{50,70,70});
        tbTabelle.getStm().loadScores();


        /*Skalierung klappt noch nicht ganz */
        tbTabelle.setSize(new Dimension(width-width/2, (int) (tbTabelle.getStm().getRowCount()*tbTabelle.getRowHeight()*2.5)));
        pnTable.setSize(new Dimension(width-width/2, (int) (tbTabelle.getStm().getRowCount()*tbTabelle.getRowHeight()*2.5)));
        psPane.setSize(new Dimension(width-width/2, (int) (tbTabelle.getStm().getRowCount()*tbTabelle.getRowHeight()*2.5)));


        System.out.println("TableWidth: "+tbTabelle.getWidth());
        System.out.println("TableHeight: "+tbTabelle.getHeight());
        System.out.println("PsPaneWidth: "+psPane.getWidth());
        System.out.println("PsPaneHeight: "+psPane.getHeight());

        //((DefaultTableCellRenderer)tbTabelle.getDefaultRenderer(Object.class)).setOpaque(false);
        psPane.setOpaque(false);
        psPane.getViewport().setOpaque(false);

        tbTabelle.setFont(new Font("Courier New", Font.BOLD, 14));                                              //BITTE SKALIEREN
        pnTable.add(psPane);
        pnTable.setOpaque(false);
        pnTable.setBorder(tbTabelle.getNullBorder());
        psPane.setBorder(tbTabelle.getNullBorder());

        pnTable.setLocation(width/2 - pnTable.getWidth()/2, height/3);
        cont.add(pnTable);

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
        cont.add(lbBackground);


        btBackToMenu.addActionListener(e -> {
            //Back to Menu einfach mit neuem Object und set visible true oderwas?

        });

    }

    /*
    public void initTableColumns()
    {
        int cnt = 0;
        int[] spalten = {90, 70, 70};
        for(String s:sname)
        {
            TableColumn tc = new TableColumn(cnt, spalten[cnt++]);
            tc.setHeaderRenderer(dtcr);

            tc.setHeaderValue(s);
            tc.setResizable(false);
            tc.setCellRenderer(sc);

            dtcm.addColumn(tc);


        }
        dtcr.setFont(new Font("Courier New", Font.BOLD, 14));                                                    //BITTE SKALIEREN
        dtcr.setBackground(new Color(0, true));
        dtcr.setForeground(new Color(206, 68, 68));
        tbTabelle.setColumnModel(dtcm);
    }

    */

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


