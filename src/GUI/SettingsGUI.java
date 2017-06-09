package GUI;

import javafx.beans.property.adapter.JavaBeanObjectProperty;
import res.Res;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

/**
 * Created by Hugo on 27.03.2017.
 */

public class SettingsGUI extends JDialog{
    protected static SettingsGUI gui1 = new SettingsGUI();

    public SettingsGUI() {
        initComponents();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
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
        ueberschrift.setSize(gesamtPanel.getWidth(),gesamtPanel.getHeight()/10);
        ueberschrift.setFont(new Font("Arial", Font.BOLD, ueberschrift.getHeight()-ueberschrift.getHeight()/10));
        gesamtLabel.add(ueberschrift);


        //Button um die Settings zu übernehmen
        JButton btUebernehmen = new JButton();
        btUebernehmen.setText("Übernehmen");
        btUebernehmen.setSize(gesamtPanel.getWidth()/4,gesamtPanel.getHeight()/10);
        btUebernehmen.setLocation(gesamtPanel.getWidth()/2-btUebernehmen.getWidth()/2, gesamtPanel.getHeight()-2*btUebernehmen.getHeight());
        btUebernehmen.setFont(new Font("Arial", Font.BOLD, btUebernehmen.getHeight()/3));
        gesamtLabel.add(btUebernehmen);


        //Button um dieses Fenster zu schliessen
        JButton btAbbrechen = new JButton();
        btAbbrechen.setText("Abbrechen");
        btAbbrechen.setSize(gesamtPanel.getWidth()/4,gesamtPanel.getHeight()/10);
        btAbbrechen.setLocation(gesamtPanel.getWidth()/2+btAbbrechen.getWidth()-btAbbrechen.getWidth()/3, gesamtPanel.getHeight()-2*btAbbrechen.getHeight());
        btAbbrechen.addActionListener(e -> {
            try {
                onExit();
            } catch (Exception ex) {
                System.out.println("Programm konnte nicht beendet werden");
            }
        });
        btAbbrechen.setFont(new Font("Arial", Font.BOLD, btAbbrechen.getHeight()/3));
        gesamtLabel.add(btAbbrechen);


        //Panel um 5 Labels und 5 Textfelder hinzuzufügen in dem man Änderungen vornehmen kann
        JPanel steuerung = new JPanel(new GridLayout(5,2));
        steuerung.setBackground(Color.lightGray);
        steuerung.setBorder(new TitledBorder("Steuerung"));
        steuerung.setSize(gesamtPanel.getWidth()/2, gesamtPanel.getHeight()/2);
        steuerung.setLocation(gesamtPanel.getWidth()/2-steuerung.getWidth()+steuerung.getWidth()/8, gesamtPanel.getHeight()/2-steuerung.getHeight()+steuerung.getHeight()/2);


        //Label Links hinzufügen
        JLabel links = new JLabel();
        links.setText("Links:");
        JTextField tflinks = new JTextField("A");
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
                if(tflinks.getText().length() > 1)
                {
                    tflinks.getText();
                    tflinks.setText("");
                }
                else
                {
                    tflinks.setText(text);
                }
            }});
        tflinks.setHorizontalAlignment(tflinks.CENTER);
        tflinks.setFont(new Font("Arial",Font.BOLD, 30));



        //Label Rechts hinzufügen
        JLabel rechts = new JLabel();
        rechts.setText("Rechts:");
        JTextField tfrechts = new JTextField("D");
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
                if(tfrechts.getText().length() > 1)
                {
                    tfrechts.getText();
                    tfrechts.setText("");
                }
                else
                {
                    tfrechts.setText(text);
                }
            }});
        tfrechts.setHorizontalAlignment(tfrechts.CENTER);
        tfrechts.setFont(new Font("Arial",Font.BOLD, 30));



        //Label Runter hinzufügen
        JLabel runter = new JLabel();
        runter.setText("Hinunter:");
        JTextField tfrunter = new JTextField("S");
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
                if(tfrunter.getText().length() > 1)
                {
                    tfrunter.getText();
                    tfrunter.setText("");
                }
                else
                {
                    tfrunter.setText(text);
                }

            }});
        tfrunter.setHorizontalAlignment(tfrunter.CENTER);
        tfrunter.setFont(new Font("Arial",Font.BOLD, 30));



        //Label Links Drehen hinzufügen
        JLabel linksdrehen = new JLabel();
        linksdrehen.setText("Links drehen:");
        JTextField tflinksdrehen = new JTextField("W");
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
                if(tflinksdrehen.getText().length() > 1)
                {
                    tflinksdrehen.getText();
                    tflinksdrehen.setText("");
                }
                else
                {
                    tflinksdrehen.setText(text);
                }
            }});
        tflinksdrehen.setHorizontalAlignment(tflinksdrehen.CENTER);
        tflinksdrehen.setFont(new Font("Arial",Font.BOLD, 30));



        //Label Rechts Drehen hinzufügen
        JLabel rechtsdrehen = new JLabel();
        rechtsdrehen.setText("Rechts drehen:");
        JTextField tfrechtsdrehen = new JTextField("E");
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
                if(tfrechtsdrehen.getText().length() > 1)
                {
                    tfrechtsdrehen.getText();
                    tfrechtsdrehen.setText("");
                }
                else
                {
                    tfrechtsdrehen.setText(text);
                }
            }});
        tfrechtsdrehen.setHorizontalAlignment(tfrechtsdrehen.CENTER);
        tfrechtsdrehen.setFont(new Font("Arial",Font.BOLD, 30));



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
            try {
                String slinks = tflinks.getText();
                String srechts = tfrechts.getText();
                String srunter = tfrunter.getText();
                String slinksdrehen = tflinksdrehen.getText();
                String srechtsdrehen = tfrechtsdrehen.getText();

                if(slinks.length()<2&&srechts.length()<2&&srunter.length()<2&&slinksdrehen.length()<2&&srechtsdrehen.length()>0&&slinks.length()>0&&srechts.length()>0&&srunter.length()>0&&slinksdrehen.length()>0&&srechtsdrehen.length()>0)
                {

                    this.dispose();
                    System.out.println("ok");
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Man muss genau einen Buchstaben in ein Feld schreiben!");
                }
                /*String[] steuerungfeld = new String[5];
                Boolean isok=true;
                steuerungfeld[0] = slinks;
                steuerungfeld[1] = srechts;
                steuerungfeld[2] = srunter;
                steuerungfeld[3] = slinksdrehen;
                steuerungfeld[4] = srechtsdrehen;
                for(int i = 0; i < steuerungfeld.length-1; i++)
                {
                    if(steuerungfeld[i].equals(steuerungfeld[i+1]))
                    {
                        isok = false;
                        JOptionPane.showMessageDialog(this, "Zwei Werte sind gleich!");
                    }
                    else if(isok == true)
                    {
                        System.out.println("GUI");
                        this.dispose();
                    }
                }
                */
            } catch (Exception ex) {
                System.out.println("Programm konnte nicht beendet werden");
            }


        });
    }

    private void onExit() {
        this.dispose();
    }


    public static void main(String[] args) {

        gui1.setVisible(true);
    }
}
