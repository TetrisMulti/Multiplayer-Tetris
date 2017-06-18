package GUI;

import BL.SettingsLoader;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Hugo on 27.03.2017.
 */

public class SettingsGUI extends JDialog {
    private HashMap<String, Integer> hmNewKeys = new HashMap<>();
    private HashMap<String, Integer> hmKeys = new HashMap<>();
    private SettingsLoader sl;
    private boolean isControllerOn;
    private final String path = ""+System.getProperty("user.dir")+ File.separator+"src"+File.separator+"res"+File.separator;

    /**
     * Constructor
     * Call ReadSettings
     * Save Settings on HashMap
     *
     * @param sl
     */
    public SettingsGUI(SettingsLoader sl) {
        isControllerOn = false;
        this.sl = sl;
        try {
            sl.ReadSettings();
            hmKeys = sl.getHmKeys();

        } catch (IOException e) {
            e.printStackTrace();
        }
        initComponents();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);

    }

    /**
     * Method to initialize the Window
     * Adds the ToggleButton for Controller
     * Adds the keyboard settingfields
     */
    private void initComponents() {
        Dimension scrnSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle winSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        int taskBarHeight = scrnSize.height - winSize.height;

        int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 4;
        int height = ((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - taskBarHeight) / 3;
        this.setSize(width, height);

        this.setLocationRelativeTo(null);

        JPanel pnFullPanel = new JPanel();
        pnFullPanel.setBackground(Color.white);
        pnFullPanel.setLayout(null);
        pnFullPanel.setSize(width, height);

        Container cont = this.getContentPane();
        cont.setLayout(new BorderLayout());
        cont.add(pnFullPanel, BorderLayout.CENTER);

        JLabel lbFullLabel = new JLabel();
        lbFullLabel.setSize(width, height);
        lbFullLabel.setOpaque(true);
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

        JLabel lbTitle = new JLabel();
        lbTitle.setText("Einstellungen");
        lbTitle.setForeground(Color.yellow);
        lbTitle.setSize(pnFullPanel.getWidth(), pnFullPanel.getHeight() / 10);
        lbTitle.setFont(new Font("Arial", Font.BOLD, lbTitle.getHeight() - lbTitle.getHeight() / 10));
        lbFullLabel.add(lbTitle);

        JButton btAccept = new JButton();
        btAccept.setText("Übernehmen");
        btAccept.setSize(pnFullPanel.getWidth() / 4, pnFullPanel.getHeight() / 10);
        btAccept.setLocation(pnFullPanel.getWidth() / 2 - btAccept.getWidth() / 2, pnFullPanel.getHeight() - 2 * btAccept.getHeight());
        btAccept.setFont(new Font("Arial", Font.BOLD, btAccept.getHeight() / 3));
        lbFullLabel.add(btAccept);

        JButton btAbort = new JButton();
        btAbort.setText("Abbrechen");
        btAbort.setSize(pnFullPanel.getWidth() / 4, pnFullPanel.getHeight() / 10);
        btAbort.setLocation(pnFullPanel.getWidth() / 2 + btAbort.getWidth() - btAbort.getWidth() / 3, pnFullPanel.getHeight() - 2 * btAbort.getHeight());
        btAbort.addActionListener(e -> {
            try {
                onExit();
            } catch (Exception ex) {
                System.out.println("Programm konnte nicht beendet werden");
            }
        });
        btAbort.setFont(new Font("Arial", Font.BOLD, btAbort.getHeight() / 3));
        lbFullLabel.add(btAbort);

        JPanel pnControls = new JPanel(new GridLayout(5, 2));
        pnControls.setBackground(Color.black);
        TitledBorder bdTitBord = new TitledBorder("Steuerung");
        bdTitBord.setTitleColor(Color.white);
        pnControls.setBorder(bdTitBord);
        pnControls.setSize(pnFullPanel.getWidth() / 2, pnFullPanel.getHeight() / 2);
        pnControls.setLocation(pnFullPanel.getWidth() / 2 - pnControls.getWidth() + pnControls.getWidth() / 8, pnFullPanel.getHeight() / 2 - pnControls.getHeight() + pnControls.getHeight() / 2);

        JLabel lbLeft = new JLabel();
        lbLeft.setForeground(Color.white);
        lbLeft.setText("Links:");
        JTextField tfGoLeft = new JTextField(KeyEvent.getKeyText(hmKeys.get("left")));
        tfGoLeft.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                String text = tfGoLeft.getText().toUpperCase();
                if (tfGoLeft.getText().length() > 1) {
                    tfGoLeft.getText();
                    tfGoLeft.setText("");
                } else {
                    tfGoLeft.setText(text);
                }
            }
        });
        tfGoLeft.setHorizontalAlignment(tfGoLeft.CENTER);
        tfGoLeft.setFont(new Font("Arial", Font.BOLD, 30));

        JLabel lbRight = new JLabel();
        lbRight.setText("Rechts:");
        lbRight.setForeground(Color.white);
        JTextField tfGoRight = new JTextField(KeyEvent.getKeyText(hmKeys.get("right")));
        tfGoRight.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                String text = tfGoRight.getText().toUpperCase();
                if (tfGoRight.getText().length() > 1) {
                    tfGoRight.getText();
                    tfGoRight.setText("");
                } else {
                    tfGoRight.setText(text);
                }
            }
        });
        tfGoRight.setHorizontalAlignment(tfGoRight.CENTER);
        tfGoRight.setFont(new Font("Arial", Font.BOLD, 30));

        JLabel lbDown = new JLabel();
        lbDown.setText("Hinunter:");
        lbDown.setForeground(Color.white);
        JTextField tfGoDown = new JTextField(KeyEvent.getKeyText(hmKeys.get("down")));
        tfGoDown.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                String text = tfGoDown.getText().toUpperCase();
                if (tfGoDown.getText().length() > 1) {
                    tfGoDown.getText();
                    tfGoDown.setText("");
                } else {
                    tfGoDown.setText(text);
                }

            }
        });
        tfGoDown.setHorizontalAlignment(tfGoDown.CENTER);
        tfGoDown.setFont(new Font("Arial", Font.BOLD, 30));

        JLabel lbRotLeft = new JLabel();
        lbRotLeft.setText("Links drehen:");
        lbRotLeft.setForeground(Color.white);
        JTextField tfRotateLeft = new JTextField(KeyEvent.getKeyText(hmKeys.get("rotateLeft")));
        tfRotateLeft.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                String text = tfRotateLeft.getText().toUpperCase();
                if (tfRotateLeft.getText().length() > 1) {
                    tfRotateLeft.getText();
                    tfRotateLeft.setText("");
                } else {
                    tfRotateLeft.setText(text);
                }
            }
        });
        tfRotateLeft.setHorizontalAlignment(tfRotateLeft.CENTER);
        tfRotateLeft.setFont(new Font("Arial", Font.BOLD, 30));

        JLabel lbRotRight = new JLabel();
        lbRotRight.setText("Rechts drehen:");
        lbRotRight.setForeground(Color.white);
        JTextField tfRotateRight = new JTextField(KeyEvent.getKeyText(hmKeys.get("rotateRight")));
        tfRotateRight.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                String text = tfRotateRight.getText().toUpperCase();
                if (tfRotateRight.getText().length() > 1) {
                    tfRotateRight.getText();
                    tfRotateRight.setText("");
                } else {
                    tfRotateRight.setText(text);
                }
            }
        });
        tfRotateRight.setHorizontalAlignment(tfRotateRight.CENTER);
        tfRotateRight.setFont(new Font("Arial", Font.BOLD, 30));

        JPanel pnController = new JPanel(new GridLayout(1, 1));
        pnController.setSize(width / 3, height / 8);
        pnController.setBackground(Color.black);
        JToggleButton tbtController = new JToggleButton();
        TitledBorder tbControllerBorder = new TitledBorder("Controller");
        tbControllerBorder.setTitleColor(Color.white);
        pnController.setBorder(tbControllerBorder);
        tbtController.setText("Controller J/N");
        pnController.setLocation((int) (width / 3 * 1.7), height / 8);
        tbtController.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tbtController.isSelected()) {
                    pnControls.setEnabled(false);
                    tfGoLeft.setEnabled(false);
                    tfRotateLeft.setEnabled(false);
                    tfGoRight.setEnabled(false);
                    tfRotateRight.setEnabled(false);
                    tfGoDown.setEnabled(false);
                    lbDown.setEnabled(false);
                    lbLeft.setEnabled(false);
                    lbRight.setEnabled(false);
                    lbRotLeft.setEnabled(false);
                    lbRotRight.setEnabled(false);
                    isControllerOn = true;
                } else {
                    pnControls.setEnabled(true);
                    tfGoLeft.setEnabled(true);
                    tfRotateLeft.setEnabled(true);
                    tfGoRight.setEnabled(true);
                    tfRotateRight.setEnabled(true);
                    tfGoDown.setEnabled(true);
                    isControllerOn = false;
                    lbDown.setEnabled(true);
                    lbLeft.setEnabled(true);
                    lbRight.setEnabled(true);
                    lbRotLeft.setEnabled(true);
                    lbRotRight.setEnabled(true);
                }
            }
        });
        pnController.add(tbtController);
        lbFullLabel.add(pnController);

        pnControls.add(lbLeft);
        pnControls.add(tfGoLeft);
        pnControls.add(lbRight);
        pnControls.add(tfGoRight);
        pnControls.add(lbDown);
        pnControls.add(tfGoDown);
        pnControls.add(lbRotLeft);
        pnControls.add(tfRotateLeft);
        pnControls.add(lbRotRight);
        pnControls.add(tfRotateRight);
        lbFullLabel.add(pnControls);

        btAccept.addActionListener(e -> {
            hmNewKeys.clear();
            hmNewKeys.put("left", KeyEvent.getExtendedKeyCodeForChar(tfGoLeft.getText().charAt(0)));
            hmNewKeys.put("right", KeyEvent.getExtendedKeyCodeForChar(tfGoRight.getText().charAt(0)));
            hmNewKeys.put("down", KeyEvent.getExtendedKeyCodeForChar(tfGoDown.getText().charAt(0)));
            hmNewKeys.put("rotateLeft", KeyEvent.getExtendedKeyCodeForChar(tfRotateLeft.getText().charAt(0)));
            hmNewKeys.put("rotateRight", KeyEvent.getExtendedKeyCodeForChar(tfRotateRight.getText().charAt(0)));

            try {
                sl.WriteSettings(hmNewKeys);
                hmKeys.clear();
                sl.ReadSettings();
                hmKeys = sl.getHmKeys();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            this.dispose();
        });
    }

    private void onExit() {
        this.dispose();
    }

    public HashMap<String, Integer> getHmKeys() {
        return hmKeys;
    }

    public boolean isControllerOn() {
        return isControllerOn;
    }
}