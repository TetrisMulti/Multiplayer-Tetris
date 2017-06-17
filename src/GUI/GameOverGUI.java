package GUI;



import Beans.ScoreTable;
import org.xml.sax.SAXException;
import res.Res;
import Beans.Score;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.IOException;


/**
 * Created by ganleb13 on 16.03.2017.
 * In this class we will define the GameOver-Screen
 */
public class GameOverGUI extends JDialog{

    private JButton btExit;
    private JButton btBackToMenu;
    private JScrollPane psPane;
    private ScoreTable tbScTable;
    private JLabel lbBackground;
    private JPanel pnTablePanel;
    private int score;
    private StartGUI sgui;
    private JLabel lbPlayersScore;

    /**
     * Call Method initComponents
     * Call Method addScore
     * Set StartGUI
     * @param score
     * @param sgui
     * @throws HeadlessException
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    public GameOverGUI(Score score, JFrame sgui) throws HeadlessException, IOException, SAXException, ParserConfigurationException {
        initComponents();
        tbScTable.addScore(score);
        this.score = score.getScore();
        this.sgui = (StartGUI) sgui;
    }

    /**
     * Method to initialize the Window
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
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
        tbScTable = new ScoreTable();
        psPane = new JScrollPane(tbScTable);
        lbBackground = new JLabel();
        pnTablePanel = new JPanel();
        cont.setLayout(null);
        JPanel pnPSPanel = new JPanel();
        lbPlayersScore = new JLabel();

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

        pnTablePanel.setSize(new Dimension(width , (int) (3* tbScTable.getRowHeight()*2.5)));
        int columnwidth = width/4;
        tbScTable.initTableColumns(columnwidth);
        tbScTable.getStm().loadScores();
        tbScTable.getTableHeader().setBackground(new Color(48,54,44));

        psPane.setOpaque(false);
        psPane.getViewport().setOpaque(false);

        tbScTable.setFont(new Font("Courier New", Font.BOLD, 14));

        pnTablePanel.setOpaque(false);
        pnTablePanel.setBorder(tbScTable.getNullBorder());
        psPane.setBorder(tbScTable.getNullBorder());
        psPane.setViewportView(tbScTable);
        psPane.setPreferredSize(new Dimension(width/4*3 , (int) (3* tbScTable.getRowHeight()*2.5)));
        psPane.revalidate();
        pnTablePanel.add(psPane);

        pnTablePanel.setLocation(width/2 - pnTablePanel.getWidth()/2, (int) (height/3.5));
        cont.add(pnTablePanel);

        btExit.setText("Exit");
        btBackToMenu.setText("Back to Menu");

        btExit.setSize(width/3, height/20);
        cont.add(btExit);
        btExit.setLocation(width/2 - btExit.getWidth()/2,height - height/3 + 10);

        btBackToMenu.setSize(width/3, height/20);
        cont.add(btBackToMenu);
        btBackToMenu.setLocation(width/2 - btExit.getWidth()/2,height - height/4 + 10);

        lbPlayersScore.setOpaque(false);
        lbPlayersScore.setText("Dein Score: "+score);
        lbPlayersScore.setFont(new Font("Courier New", Font.BOLD, 28));
        lbPlayersScore.setBackground(new Color(48,54,44));
        lbPlayersScore.setForeground(Color.white);

        pnPSPanel.setOpaque(false);
        pnPSPanel.setSize(width,100);
        pnPSPanel.add(lbPlayersScore);
        pnPSPanel.setLocation(0, height/5);
        cont.add(pnPSPanel);


        btExit.addActionListener(e -> {
            System.exit(0);
        });
        cont.add(lbBackground);


        btBackToMenu.addActionListener(e -> {
            sgui.setVisible(true);
            this.dispose();

        });

    }
}


