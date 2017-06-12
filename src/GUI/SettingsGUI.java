package GUI;

import BL.SettingsLoader;
import javafx.beans.property.adapter.JavaBeanObjectProperty;
import res.Res;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Hugo on 27.03.2017.
 */

public class SettingsGUI extends JDialog {
    private HashMap<String, Integer> hmNewKeys = new HashMap<>();
    private HashMap<String, Integer> hmKeys = new HashMap<>();
    private SettingsLoader sl;



    public SettingsGUI(SettingsLoader sl) {
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
    public SettingsGUI() {
    }

    private void initComponents() {
        Dimension scrnSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle winSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        int taskBarHeight = scrnSize.height - winSize.height;


        //Gesamtbreite und Gesamthöhe bestimmen
        int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 4;
        int height = ((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - taskBarHeight) / 3;


        //Größe, Location und Methode zum Schliessen setzen
        this.setSize(width, height);
        //this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);


        //Ein Panel für den ganzen Bildschirm erstellen (mit Layout, Background-Farbe und Größe
        JPanel gesamtPanel = new JPanel();
        gesamtPanel.setBackground(Color.white);
        gesamtPanel.setLayout(null);
        gesamtPanel.setSize(width, height);


        //Das Gesamtpanel in einen Container hinzufügen
        Container cont = this.getContentPane();
        cont.setLayout(new BorderLayout());
        cont.add(gesamtPanel, BorderLayout.CENTER);


        //Label um das Hintergrundbild hinzuzufügen
        JLabel gesamtLabel = new JLabel();
        gesamtLabel.setSize(width, height);
        gesamtLabel.setOpaque(true);
        Image img = null;
        try {
            img = ImageIO.read(Res.class.getResourceAsStream("Tetris.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(dimg);
        gesamtLabel.setIcon(icon);
        gesamtPanel.add(gesamtLabel);


        //Label für die Überschrift erstelen und dieses dann formatieren
        JLabel ueberschrift = new JLabel();
        ueberschrift.setText("Einstellungen");
        ueberschrift.setForeground(Color.yellow);
        ueberschrift.setSize(gesamtPanel.getWidth(), gesamtPanel.getHeight() / 10);
        ueberschrift.setFont(new Font("Arial", Font.BOLD, ueberschrift.getHeight() - ueberschrift.getHeight() / 10));
        gesamtLabel.add(ueberschrift);


        //Button um die Settings zu übernehmen
        JButton btUebernehmen = new JButton();
        btUebernehmen.setText("Übernehmen");
        btUebernehmen.setSize(gesamtPanel.getWidth() / 4, gesamtPanel.getHeight() / 10);
        btUebernehmen.setLocation(gesamtPanel.getWidth() / 2 - btUebernehmen.getWidth() / 2, gesamtPanel.getHeight() - 2 * btUebernehmen.getHeight());
        btUebernehmen.setFont(new Font("Arial", Font.BOLD, btUebernehmen.getHeight() / 3));
        gesamtLabel.add(btUebernehmen);


        //Button um dieses Fenster zu schliessen
        JButton btAbbrechen = new JButton();
        btAbbrechen.setText("Abbrechen");
        btAbbrechen.setSize(gesamtPanel.getWidth() / 4, gesamtPanel.getHeight() / 10);
        btAbbrechen.setLocation(gesamtPanel.getWidth() / 2 + btAbbrechen.getWidth() - btAbbrechen.getWidth() / 3, gesamtPanel.getHeight() - 2 * btAbbrechen.getHeight());
        btAbbrechen.addActionListener(e -> {
            try {
                onExit();
            } catch (Exception ex) {
                System.out.println("Programm konnte nicht beendet werden");
            }
        });
        btAbbrechen.setFont(new Font("Arial", Font.BOLD, btAbbrechen.getHeight() / 3));
        gesamtLabel.add(btAbbrechen);


        //Panel um 5 Labels und 5 Textfelder hinzuzufügen in dem man Änderungen vornehmen kann
        JPanel steuerung = new JPanel(new GridLayout(5, 2));
        steuerung.setBackground(Color.black);
        TitledBorder tb = new TitledBorder("Steuerung");
        tb.setTitleColor(Color.white);
        steuerung.setBorder(tb);
        steuerung.setSize(gesamtPanel.getWidth() / 2, gesamtPanel.getHeight() / 2);
        steuerung.setLocation(gesamtPanel.getWidth() / 2 - steuerung.getWidth() + steuerung.getWidth() / 8, gesamtPanel.getHeight() / 2 - steuerung.getHeight() + steuerung.getHeight() / 2);


        //Label Links hinzufügen
        JLabel links = new JLabel();
        links.setForeground(Color.white);
        links.setText("Links:");
        JTextField tflinks = new JTextField(KeyEvent.getKeyText(hmKeys.get("left")));
        tflinks.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                String text = tflinks.getText().toUpperCase();
                if (tflinks.getText().length() > 1) {
                    tflinks.getText();
                    tflinks.setText("");
                } else {
                    tflinks.setText(text);
                }
            }
        });
        tflinks.setHorizontalAlignment(tflinks.CENTER);
        tflinks.setFont(new Font("Arial", Font.BOLD, 30));


        //Label Rechts hinzufügen
        JLabel rechts = new JLabel();
        rechts.setText("Rechts:");
        rechts.setForeground(Color.white);
        JTextField tfrechts = new JTextField(KeyEvent.getKeyText(hmKeys.get("right")));
        tfrechts.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                String text = tfrechts.getText().toUpperCase();
                if (tfrechts.getText().length() > 1) {
                    tfrechts.getText();
                    tfrechts.setText("");
                } else {
                    tfrechts.setText(text);
                }
            }
        });
        tfrechts.setHorizontalAlignment(tfrechts.CENTER);
        tfrechts.setFont(new Font("Arial", Font.BOLD, 30));


        //Label Runter hinzufügen
        JLabel runter = new JLabel();
        runter.setText("Hinunter:");
        runter.setForeground(Color.white);
        JTextField tfrunter = new JTextField(KeyEvent.getKeyText(hmKeys.get("down")));
        tfrunter.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                String text = tfrunter.getText().toUpperCase();
                if (tfrunter.getText().length() > 1) {
                    tfrunter.getText();
                    tfrunter.setText("");
                } else {
                    tfrunter.setText(text);
                }

            }
        });
        tfrunter.setHorizontalAlignment(tfrunter.CENTER);
        tfrunter.setFont(new Font("Arial", Font.BOLD, 30));


        //Label Links Drehen hinzufügen
        JLabel linksdrehen = new JLabel();
        linksdrehen.setText("Links drehen:");
        linksdrehen.setForeground(Color.white);
        JTextField tflinksdrehen = new JTextField(KeyEvent.getKeyText(hmKeys.get("rotateLeft")));
        tflinksdrehen.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                String text = tflinksdrehen.getText().toUpperCase();
                if (tflinksdrehen.getText().length() > 1) {
                    tflinksdrehen.getText();
                    tflinksdrehen.setText("");
                } else {
                    tflinksdrehen.setText(text);
                }
            }
        });
        tflinksdrehen.setHorizontalAlignment(tflinksdrehen.CENTER);
        tflinksdrehen.setFont(new Font("Arial", Font.BOLD, 30));


        //Label Rechts Drehen hinzufügen
        JLabel rechtsdrehen = new JLabel();
        rechtsdrehen.setText("Rechts drehen:");
        rechtsdrehen.setForeground(Color.white);
        JTextField tfrechtsdrehen = new JTextField(KeyEvent.getKeyText(hmKeys.get("rotateRight")));
        tfrechtsdrehen.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                String text = tfrechtsdrehen.getText().toUpperCase();
                if (tfrechtsdrehen.getText().length() > 1) {
                    tfrechtsdrehen.getText();
                    tfrechtsdrehen.setText("");
                } else {
                    tfrechtsdrehen.setText(text);
                }
            }
        });
        tfrechtsdrehen.setHorizontalAlignment(tfrechtsdrehen.CENTER);
        tfrechtsdrehen.setFont(new Font("Arial", Font.BOLD, 30));


        //Labels und Textfelder in das Panel hinzufügen
        steuerung.add(links);
        steuerung.add(tflinks);
        steuerung.add(rechts);
        steuerung.add(tfrechts);
        steuerung.add(runter);
        steuerung.add(tfrunter);
        steuerung.add(linksdrehen);
        steuerung.add(tflinksdrehen);
        steuerung.add(rechtsdrehen);
        steuerung.add(tfrechtsdrehen);
        gesamtLabel.add(steuerung);


        //Methode zum Überprüfen der eingegebenen Sachen
        btUebernehmen.addActionListener(e -> {
            hmNewKeys.clear();
            hmNewKeys.put("left", KeyEvent.getExtendedKeyCodeForChar(tflinks.getText().charAt(0)));
            hmNewKeys.put("right", KeyEvent.getExtendedKeyCodeForChar(tfrechts.getText().charAt(0)));
            hmNewKeys.put("down", KeyEvent.getExtendedKeyCodeForChar(tfrunter.getText().charAt(0)));
            hmNewKeys.put("rotateLeft", KeyEvent.getExtendedKeyCodeForChar(tflinksdrehen.getText().charAt(0)));
            hmNewKeys.put("rotateRight", KeyEvent.getExtendedKeyCodeForChar(tfrechtsdrehen.getText().charAt(0)));

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


    public static void main(String[] args) {
        SettingsGUI gui = new SettingsGUI();
        gui.setVisible(true);
    }


    public HashMap<String, Integer> getHmKeys() {
        return hmKeys;
    }
}