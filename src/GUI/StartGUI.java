package GUI;

import res.Res;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by Hugo & Christoph on 13.03.2017.
 */
public class StartGUI extends JFrame {
    protected static StartGUI gui=new StartGUI();

    public StartGUI() {
        initComponents();
        this.setResizable(false);
    }

    private void initComponents() {
        Dimension scrnSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle winSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        int taskBarHeight = scrnSize.height - winSize.height;

        int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 3;
        int height = ((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - taskBarHeight) / 2;

        this.setSize(width, height);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JPanel gesamtPanel = new JPanel();

        Container cont = this.getContentPane();
        cont.setLayout(new BorderLayout());
        cont.add(gesamtPanel, BorderLayout.CENTER);
        gesamtPanel.setLayout(null);
        gesamtPanel.setSize(width, height);

        JPanel panel1 = new JPanel();
        JLabel ueberschrift = new JLabel();
        gesamtPanel.setBackground(Color.lightGray);
        JLabel gesamtLabel = new JLabel();
        gesamtLabel.setSize(width, height);
        gesamtLabel.setOpaque(true);
        gesamtLabel.setBackground(Color.red);
        System.out.println(width+"  "+height);
        Image img = null;
        try {
            img = ImageIO.read(Res.class.getResourceAsStream("Tetris.jpg"));
           //gesamtLabel.setIcon(new ImageIcon(ImageIO.read(Res.class.getResourceAsStream("Tetris.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(dimg);
        gesamtLabel.setIcon(icon);
        gesamtPanel.add(gesamtLabel);


        JButton btStart = new JButton();
        btStart.setText("Start");
        panel1.setBackground(Color.black);
        //btStart.setSize(width/10, height/10);
        //btStart.setLocation(width/10,height/10);
        panel1.add(btStart);



        JButton btHighScore = new JButton();
        btHighScore.setText("HighScore");
        panel1.add(btHighScore);
        JButton btSettings = new JButton();
        JButton btExit = new JButton();
        btExit.setText("Beenden");
        panel1.add(btExit);
        panel1.setBackground(Color.black);
        panel1.setOpaque(false);

        panel1.setSize(gesamtPanel.getWidth()/2,gesamtPanel.getHeight()/2);
        panel1.setLocation(gesamtPanel.getWidth()/2-panel1.getWidth()/2,gesamtPanel.getHeight()/2-panel1.getHeight()/2);
        panel1.setLayout(null);

        btStart.setSize(panel1.getWidth()/2,panel1.getHeight()/6);
        btStart.setLocation(panel1.getWidth()/2-btStart.getWidth()/2, panel1.getHeight()/2-btStart.getHeight()-btStart.getHeight());
        btStart.addActionListener((e) -> onStart());
        btStart.setFont(new Font("Arial", Font.BOLD, btStart.getHeight()/2));

        btHighScore.setSize(panel1.getWidth()/2,panel1.getHeight()/6);
        btHighScore.setLocation(panel1.getWidth()/2-btHighScore.getWidth()/2, panel1.getHeight()/2-btHighScore.getHeight()/2);
        btHighScore.addActionListener(e -> {
            try
            {
                HighScoreGUI gui = new HighScoreGUI();
                gui.setVisible(true);
            }
            catch(Exception ex)
            {
                System.out.println("HighScoreGUI konnte nicht aufgerufen werden");
            }
        });
        btHighScore.setFont(new Font("Arial", Font.BOLD, btHighScore.getHeight()/2));

        btSettings.setSize(gesamtPanel.getWidth()/18,gesamtPanel.getHeight()/15);
        btSettings.addActionListener(e -> {
            try
            {
                SettingsGUI gui1 = new SettingsGUI();
                gui1.setVisible(true);
            }
            catch(Exception ex)
            {
                System.out.println("SettingsGUI konnte nicht aufgerufen werden");
            }
        });
        btSettings.setLocation(panel1.getWidth()/2,panel1.getHeight()/2);

        ueberschrift.setText("Tetris");
        ueberschrift.setForeground(Color.yellow);
        ueberschrift.setSize(gesamtPanel.getWidth(),gesamtPanel.getHeight()/10);
        ueberschrift.setFont(new Font("Arial", Font.BOLD, ueberschrift.getHeight()));
        //ueberschrift.setOpaque(true);
        //ueberschrift.setBackground(Color.red);
        ueberschrift.setHorizontalAlignment(JLabel.CENTER);

        btExit.setSize(panel1.getWidth()/2,panel1.getHeight()/6);
        btExit.setLocation(panel1.getWidth()/2-btExit.getWidth()/2, panel1.getHeight()/2+btExit.getHeight());
        btExit.addActionListener(e -> {
            try {
                onExit();
            } catch (Exception ex) {
                System.out.println("Programm konnte nicht beendet werden");
            }
        });
        btExit.setFont(new Font("Arial", Font.BOLD, btExit.getHeight()/2));

        Image img1 = null;
        try {
            img1 = ImageIO.read(Res.class.getResourceAsStream("Settings.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg1 = img1.getScaledInstance(btSettings.getWidth(), btSettings.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon icon1 = new ImageIcon(dimg1);
        btSettings.setIcon(icon1);



        JButton btCredits = new JButton();
        btCredits.setSize(panel1.getWidth()/4,panel1.getHeight()/10);
        btCredits.setLocation(gesamtLabel.getWidth()-btCredits.getWidth(), 0);
        btCredits.setText("Credits");
        btCredits.setFont(new Font("Arial", Font.BOLD, btHighScore.getHeight()/4));
        btCredits.addActionListener(e -> {
            try
            {
                CreditsGUI gui1 = new CreditsGUI();
                gui1.setVisible(true);
            }
            catch(Exception ex)
            {
                System.out.println("CreditsGUI konnte nicht aufgerufen werden");
            }
        });



        gesamtLabel.add(btCredits);
        gesamtLabel.add(btSettings);
        gesamtLabel.add(ueberschrift);
        gesamtLabel.add(panel1);

        //cont.add(btStart);
        //cont.setLayout(null);

        System.out.println(width+" "+height);
    }

    private void onStart() {
        String nickname=JOptionPane.showInputDialog(this,"Bitte Nicknamen eingeben!");
        TetrisGUI tGui = new TetrisGUI(nickname,gui);
        tGui.setVisible(true);
        this.setVisible(false);
    }

    private void onExit() {
        System.exit(0);
    }


    public static void main(String[] args) {

        gui.setVisible(true);
    }
}
