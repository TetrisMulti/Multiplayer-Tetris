package GUI;

import javafx.beans.property.adapter.JavaBeanObjectProperty;
import res.Res;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.IOException;

/**
 * Created by Hugo on 27.03.2017.
 */

public class SettingsGUI extends JFrame{
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
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
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
        JTextField tflinks = new JTextField();
        tflinks.setHorizontalAlignment(tflinks.CENTER);
        tflinks.setFont(new Font("Arial",Font.BOLD, 30));
        tflinks.setText("A");


        //Label Rechts hinzufügen
        JLabel rechts = new JLabel();
        rechts.setText("Rechts:");
        JTextField tfrechts = new JTextField();
        tfrechts.setHorizontalAlignment(tfrechts.CENTER);
        tfrechts.setFont(new Font("Arial",Font.BOLD, 30));
        tfrechts.setText("D");


        //Label Runter hinzufügen
        JLabel runter = new JLabel();
        runter.setText("Hinunter:");
        JTextField tfrunter = new JTextField();
        tfrunter.setHorizontalAlignment(tfrunter.CENTER);
        tfrunter.setFont(new Font("Arial",Font.BOLD, 30));
        tfrunter.setText("S");


        //Label Links Drehen hinzufügen
        JLabel linksdrehen = new JLabel();
        linksdrehen.setText("Links drehen:");
        JTextField tflinksdrehen = new JTextField();
        tflinksdrehen.setHorizontalAlignment(tflinksdrehen.CENTER);
        tflinksdrehen.setFont(new Font("Arial",Font.BOLD, 30));
        tflinksdrehen.setText("Q");


        //Label Rechts Drehen hinzufügen
        JLabel rechtsdrehen = new JLabel();
        rechtsdrehen.setText("Rechts drehen:");
        JTextField tfrechtsdrehen = new JTextField();
        tfrechtsdrehen.setHorizontalAlignment(tfrechtsdrehen.CENTER);
        tfrechtsdrehen.setFont(new Font("Arial",Font.BOLD, 30));
        tfrechtsdrehen.setText("E");


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
                String[] steuerungfeld = new String[5];
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
