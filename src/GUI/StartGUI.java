package GUI;

import BL.SettingsLoader;
import ch.aplu.xboxcontroller.XboxController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Hugo & Christoph on 13.03.2017.
 * In this class we define the StartGUI
 */
public class StartGUI extends JFrame {
    protected static StartGUI gui;
    private SettingsLoader sl = new SettingsLoader();
    private HashMap<String, Integer> hmKeys = sl.getHmKeys();
    private boolean isControllerOn = false;
    private SettingsGUI setGUI;
    private XboxController controller = null;
    private final String path = ""+System.getProperty("user.dir")+ File.separator+"src"+File.separator+"res"+File.separator;

    /**
     * Contructor
     */
    public StartGUI() {
        initComponents();
        this.setResizable(false);
        this.setGUI = new SettingsGUI(sl);
    }

    /**
     * Method to initialize to Window
     * Adds the buttons to settings, the game, highscore, credits and a button to close the program
     */
    private void initComponents() {
        Dimension scrnSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle winSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        int taskBarHeight = scrnSize.height - winSize.height;

        int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 3;
        int height = ((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - taskBarHeight) / 2;

        this.setSize(width, height);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JPanel pnFullPanel = new JPanel();
        pnFullPanel.setLayout(null);
        pnFullPanel.setSize(width, height);
        pnFullPanel.setBackground(Color.lightGray);

        Container cont = this.getContentPane();
        cont.setLayout(new BorderLayout());
        cont.add(pnFullPanel, BorderLayout.CENTER);

        JLabel lbFullLabel = new JLabel();
        lbFullLabel.setSize(width, height);
        lbFullLabel.setOpaque(true);
        lbFullLabel.setBackground(Color.red);

        Image img = null;
        try {
            img = ImageIO.read(new File(path+"Tetris.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(dimg);
        lbFullLabel.setIcon(icon);
        pnFullPanel.add(lbFullLabel);

        JPanel pnButtonsPanel = new JPanel();
        pnButtonsPanel.setBackground(Color.black);
        pnButtonsPanel.setOpaque(false);
        pnButtonsPanel.setSize(pnFullPanel.getWidth() / 2, pnFullPanel.getHeight() / 2);
        pnButtonsPanel.setLocation(pnFullPanel.getWidth() / 2 - pnButtonsPanel.getWidth() / 2, pnFullPanel.getHeight() / 2 - pnButtonsPanel.getHeight() / 2);
        pnButtonsPanel.setLayout(null);

        JButton btStart = new JButton();
        btStart.setText("Start");
        pnButtonsPanel.add(btStart);
        btStart.setSize(pnButtonsPanel.getWidth() / 2, pnButtonsPanel.getHeight() / 6);
        btStart.setLocation(pnButtonsPanel.getWidth() / 2 - btStart.getWidth() / 2, pnButtonsPanel.getHeight() / 2 - btStart.getHeight() - btStart.getHeight());
        btStart.addActionListener((e) -> onStart());
        btStart.setFont(new Font("Arial", Font.BOLD, btStart.getHeight() / 2));

        JButton btHighScore = new JButton();
        btHighScore.setText("HighScore");
        pnButtonsPanel.add(btHighScore);
        btHighScore.setSize(pnButtonsPanel.getWidth() / 2, pnButtonsPanel.getHeight() / 6);
        btHighScore.setLocation(pnButtonsPanel.getWidth() / 2 - btHighScore.getWidth() / 2, pnButtonsPanel.getHeight() / 2 - btHighScore.getHeight() / 2);
        btHighScore.addActionListener(e -> {
            try {
                HighScoreGUI gui = new HighScoreGUI();
                gui.setVisible(true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        btHighScore.setFont(new Font("Arial", Font.BOLD, btHighScore.getHeight() / 2));

        JButton btExit = new JButton();
        btExit.setText("Beenden");
        pnButtonsPanel.add(btExit);
        btExit.setSize(pnButtonsPanel.getWidth() / 2, pnButtonsPanel.getHeight() / 6);
        btExit.setLocation(pnButtonsPanel.getWidth() / 2 - btExit.getWidth() / 2, pnButtonsPanel.getHeight() / 2 + btExit.getHeight());
        btExit.addActionListener(e -> {
            try {
                onExit();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        btExit.setFont(new Font("Arial", Font.BOLD, btExit.getHeight() / 2));

        JButton btSettings = new JButton();
        btSettings.setSize(pnFullPanel.getWidth() / 18, pnFullPanel.getHeight() / 15);
        btSettings.addActionListener(e -> {
            try {
                setGUI.setVisible(true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        btSettings.setLocation(pnButtonsPanel.getWidth() / 2, pnButtonsPanel.getHeight() / 2);

        Image img1 = null;
        try {
            img1 = ImageIO.read(new File(path+"Settings.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg1 = img1.getScaledInstance(btSettings.getWidth(), btSettings.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon icon1 = new ImageIcon(dimg1);
        btSettings.setIcon(icon1);
        lbFullLabel.add(btSettings);

        JButton btCredits = new JButton();
        btCredits.setSize(pnButtonsPanel.getWidth() / 4, pnButtonsPanel.getHeight() / 10);
        btCredits.setLocation(lbFullLabel.getWidth() - btCredits.getWidth(), 0);
        btCredits.setText("Credits");
        btCredits.setFont(new Font("Arial", Font.BOLD, btHighScore.getHeight() / 4));
        btCredits.addActionListener(e -> {
            try {
                CreditsGUI gui1 = new CreditsGUI();
                gui1.setVisible(true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        lbFullLabel.add(btCredits);

        JLabel lbTitle = new JLabel();
        lbTitle.setText("Tetris");
        lbTitle.setForeground(Color.yellow);
        lbTitle.setSize(pnFullPanel.getWidth(), pnFullPanel.getHeight() / 10);
        lbTitle.setFont(new Font("Arial", Font.BOLD, lbTitle.getHeight()));
        lbTitle.setHorizontalAlignment(JLabel.CENTER);
        lbFullLabel.add(lbTitle);
        lbFullLabel.add(pnButtonsPanel);
    }


    /**
     * Method to read and check the nickname
     * After checking --> Forward to TetrisGUI
     */
    private void onStart() {
        String nickname = JOptionPane.showInputDialog(this, "Bitte Nicknamen eingeben!");

        if (nickname == null) {
        } else if (nickname.trim().length() >= 8 || nickname.trim().length() == 0) {
            JOptionPane.showMessageDialog(this, "Zur Info: der eingegebene Name darf max. 8 Buchstaben, aber mindestens 1 Buchstaben enthalten");
        } else {
            if (setGUI != null) {
                isControllerOn = setGUI.isControllerOn();
            }
            if (isControllerOn)
            {
                controller = new XboxController();
                if (!controller.isConnected())
                {
                    JOptionPane.showMessageDialog(this, "Bitte schließen Sie Ihren Controller an und starten Sie das Spiel neu");

                }
            }
            if ((isControllerOn && controller.isConnected())||!isControllerOn) {
                TetrisGUI tGui = new TetrisGUI(nickname, gui, hmKeys, controller);
                tGui.setVisible(true);
                this.setVisible(false);
            }



        }
    }

    private void onExit() {

        System.exit(0);
    }


    public static void main(String[] args) {
        gui = new StartGUI();
        gui.setVisible(true);
    }
}
