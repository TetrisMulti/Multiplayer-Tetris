package GUI;

import res.Res;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.IOException;

/**
 * Created by Hugo on 28.04.2017.
 */
public class CreditsGUI extends JDialog{
    public CreditsGUI() {
        initComponents();
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
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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


        //Label für die Überschrift erstelen und dieses dann formatieren
        JLabel ueberschrift = new JLabel();
        ueberschrift.setText("Credits");
        ueberschrift.setSize(gesamtPanel.getWidth(),gesamtPanel.getHeight()/10);
        ueberschrift.setFont(new Font("Arial", Font.BOLD, ueberschrift.getHeight()-ueberschrift.getHeight()/10));
        ueberschrift.setHorizontalAlignment(JLabel.CENTER);
        ueberschrift.setForeground(Color.yellow);
        gesamtPanel.add(ueberschrift);


        //Ein Label über den gesamten Bildschirm machen um ein Bild als Hintergrundbild zu setzen
        JLabel gesamtLabel = new JLabel();
        gesamtLabel.setSize(width, height);
        gesamtLabel.setBackground(Color.black);
        gesamtLabel.setOpaque(true);
        gesamtPanel.add(gesamtLabel);


        //Panel um ein Label in ein Layout zu geben
        JPanel panel1 = new JPanel(new GridLayout(1,1));
        panel1.setSize(gesamtLabel.getWidth(), gesamtLabel.getHeight()/2);
        panel1.setLocation(gesamtLabel.getWidth()-panel1.getWidth(), gesamtLabel.getHeight()/2-panel1.getHeight()/2);
        panel1.setOpaque(true);
        panel1.setBackground(Color.black);
        gesamtLabel.add(panel1);


        //Label das in das Panel hinzugefügt wird
        JLabel lbCredits = new JLabel();
        lbCredits.setText("Tetris made by 'fesche Kinder'");
        lbCredits.setFont(new Font("Arial",Font.BOLD+Font.CENTER_BASELINE,gesamtLabel.getHeight()/10));
        lbCredits.setForeground(Color.white);
        lbCredits.setHorizontalAlignment(JLabel.CENTER);
        panel1.add(lbCredits);


        //Schliessen Button, bei Klicken -> onExit Methode aufrufen und dieses Fenster schliessen
        JButton btSchliessen = new JButton();
        btSchliessen.setText("Schließen");
        btSchliessen.setSize(gesamtPanel.getWidth()/4,gesamtPanel.getHeight()/10);
        btSchliessen.setLocation(gesamtPanel.getWidth()/2+btSchliessen.getWidth()-btSchliessen.getWidth()/3, gesamtPanel.getHeight()-2*btSchliessen.getHeight());
        btSchliessen.addActionListener(e -> {
            try {
                onExit();
            } catch (Exception ex) {
                System.out.println("Programm konnte nicht beendet werden");
            }
        });
        btSchliessen.setFont(new Font("Arial", Font.BOLD, btSchliessen.getHeight()/3));
        gesamtLabel.add(btSchliessen);
    }

    private void onExit() {
        this.dispose();
    }


    public static void main(String[] args) {
        new CreditsGUI().setVisible(true);

    }
}
