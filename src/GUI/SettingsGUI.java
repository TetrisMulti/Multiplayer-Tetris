package GUI;

import javafx.beans.property.adapter.JavaBeanObjectProperty;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

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

        int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 4;
        int height = ((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - taskBarHeight) / 3;
        this.setSize(width, height);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);


        JPanel gesamtPanel = new JPanel();
        gesamtPanel.setBackground(Color.white);
        Container cont = this.getContentPane();
        cont.setLayout(new BorderLayout());
        cont.add(gesamtPanel, BorderLayout.CENTER);
        gesamtPanel.setLayout(null);
        gesamtPanel.setSize(width, height);


        JLabel ueberschrift = new JLabel();
        ueberschrift.setText("Einstellungen");
        ueberschrift.setSize(gesamtPanel.getWidth(),gesamtPanel.getHeight()/10);
        ueberschrift.setFont(new Font("Arial", Font.BOLD, ueberschrift.getHeight()-ueberschrift.getHeight()/10));
        gesamtPanel.add(ueberschrift);
        /*
        JButton btExit = new JButton();
        btExit.setText("X");
        btExit.setBackground(Color.red);
        btExit.setForeground(Color.white);
        btExit.setSize(gesamtPanel.getWidth()/12, gesamtPanel.getHeight()/14);
        btExit.setLocation(gesamtPanel.getWidth()-btExit.getWidth(), gesamtPanel.getHeight()+btExit.getHeight()-gesamtPanel.getHeight()-btExit.getHeight());
        gesamtPanel.add(btExit);
        */







        JButton btUebernehmen = new JButton();
        JButton btAbbrechen = new JButton();
        btUebernehmen.setText("Übernehmen");
        btUebernehmen.setSize(gesamtPanel.getWidth()/4,gesamtPanel.getHeight()/10);
        btUebernehmen.setLocation(gesamtPanel.getWidth()/2-btUebernehmen.getWidth()/2, gesamtPanel.getHeight()-2*btUebernehmen.getHeight());
        btUebernehmen.setFont(new Font("Arial", Font.BOLD, btUebernehmen.getHeight()/3));
        gesamtPanel.add(btUebernehmen);

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
        gesamtPanel.add(btAbbrechen);


        JPanel steuerung = new JPanel(new GridLayout(5,2));
        steuerung.setBackground(Color.lightGray);
        steuerung.setBorder(new TitledBorder("Steuerung"));
        steuerung.setSize(gesamtPanel.getWidth()/2, gesamtPanel.getHeight()/2);
        steuerung.setLocation(gesamtPanel.getWidth()/2-steuerung.getWidth()+steuerung.getWidth()/8, gesamtPanel.getHeight()/2-steuerung.getHeight()+steuerung.getHeight()/2);
        JLabel links = new JLabel();
        links.setText("Links:");
        JLabel rechts = new JLabel();
        rechts.setText("Rechts:");
        JLabel runter = new JLabel();
        runter.setText("Hinunter:");
        JLabel linksdrehen = new JLabel();
        linksdrehen.setText("Links drehen:");
        JLabel rechtsdrehen = new JLabel();
        rechtsdrehen.setText("Rechts drehen:");

        JTextField tflinks = new JTextField();
        tflinks.setHorizontalAlignment(tflinks.CENTER);
        tflinks.setFont(new Font("Arial",Font.BOLD, 30));
        tflinks.setText("A");
        JTextField tfrechts = new JTextField();
        tfrechts.setHorizontalAlignment(tfrechts.CENTER);
        tfrechts.setFont(new Font("Arial",Font.BOLD, 30));
        tfrechts.setText("D");
        JTextField tfrunter = new JTextField();
        tfrunter.setHorizontalAlignment(tfrunter.CENTER);
        tfrunter.setFont(new Font("Arial",Font.BOLD, 30));
        tfrunter.setText("S");
        JTextField tflinksdrehen = new JTextField();
        tflinksdrehen.setHorizontalAlignment(tflinksdrehen.CENTER);
        tflinksdrehen.setFont(new Font("Arial",Font.BOLD, 30));
        tflinksdrehen.setText("Q");
        JTextField tfrechtsdrehen = new JTextField();
        tfrechtsdrehen.setHorizontalAlignment(tfrechtsdrehen.CENTER);
        tfrechtsdrehen.setFont(new Font("Arial",Font.BOLD, 30));
        tfrechtsdrehen.setText("E");

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

        gesamtPanel.add(steuerung);


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
