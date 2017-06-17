package GUI;

import Beans.ScoreTable;
import org.xml.sax.SAXException;
import res.Res;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.JTableHeader;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.IOException;

/**
 * Created by Hugo on 03.04.2017.
 * Class to show all Scores
 */
public class HighScoreGUI extends JDialog {

    /**
     * Constructor
     */
    public HighScoreGUI() {
        initComponents();
        this.setResizable(false);
    }

    /**
     * Method to initialize the Windows
     */
    private void initComponents() {
        Dimension scrnSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle winSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        int taskBarHeight = scrnSize.height - winSize.height;

        int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 4;
        int height = ((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - taskBarHeight) / 3;

        this.setSize(width, height);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JPanel pnFullPanel = new JPanel();
        pnFullPanel.setBackground(Color.white);
        pnFullPanel.setLayout(null);
        pnFullPanel.setSize(width, height);

        Container cont = this.getContentPane();
        cont.setLayout(new BorderLayout());
        cont.add(pnFullPanel, BorderLayout.CENTER);

        JLabel lbTitle = new JLabel();
        lbTitle.setText("HighScore");
        lbTitle.setSize(pnFullPanel.getWidth(), pnFullPanel.getHeight() / 10);
        lbTitle.setFont(new Font("Arial", Font.BOLD, lbTitle.getHeight() - lbTitle.getHeight() / 10));
        lbTitle.setForeground(Color.yellow);
        lbTitle.setHorizontalAlignment(JLabel.CENTER);
        pnFullPanel.add(lbTitle);

        JLabel lbFullLabel = new JLabel();
        lbFullLabel.setSize(width, height);
        lbFullLabel.setOpaque(true);
        Image img = null;
        try {
            img = ImageIO.read(Res.class.getResourceAsStream("Tetris.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(dimg);
        lbFullLabel.setIcon(icon);
        pnFullPanel.add(lbFullLabel);

        JButton btClose = new JButton();
        btClose.setText("Schließen");
        btClose.setSize(pnFullPanel.getWidth() / 4, pnFullPanel.getHeight() / 10);
        btClose.setLocation(pnFullPanel.getWidth() / 2 + btClose.getWidth() - btClose.getWidth() / 3, pnFullPanel.getHeight() - 2 * btClose.getHeight());
        btClose.addActionListener(e -> {
            try {
                onExit();
            } catch (Exception ex) {
                System.out.println("Programm konnte nicht beendet werden");
            }
        });
        btClose.setFont(new Font("Arial", Font.BOLD, btClose.getHeight() / 3));
        lbFullLabel.add(btClose);

        ScoreTable scoreTable = new ScoreTable();
        JScrollPane psPane = new JScrollPane();
        scoreTable.getTableHeader().setBackground(Color.black);
        try {
            scoreTable.getStm().loadScores();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        scoreTable.setSize(new Dimension(width - width / 2, (int) (1.5 * scoreTable.getRowHeight() * 2.5)));
        psPane.setSize(new Dimension(width - width / 2, (int) (1.5 * scoreTable.getRowHeight() * 2.5)));

        int columnwidth = scoreTable.getWidth() / 4;
        scoreTable.initTableColumns(columnwidth);

        scoreTable.getStcr().setBackground(Color.black);
        scoreTable.setBackground(Color.black);

        psPane.setOpaque(false);
        psPane.getViewport().setOpaque(false);

        scoreTable.setFont(new Font("Courier New", Font.BOLD, 14));

        psPane.setBorder(scoreTable.getNullBorder());

        psPane.setLocation(width / 4, height / 9);
        psPane.setViewportView(scoreTable);
        lbFullLabel.add(psPane);
    }

    private void onExit() {
        this.dispose();
    }

}


