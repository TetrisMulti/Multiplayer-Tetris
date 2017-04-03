package GUI;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

/**
 * Created by Hugo on 03.04.2017.
 */
public class HighScoreGUI extends JFrame{


        protected static GUI.HighScoreGUI gui2 = new GUI.HighScoreGUI();

        public HighScoreGUI() {
            initComponents();
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
            ueberschrift.setText("HighScore");
            ueberschrift.setSize(gesamtPanel.getWidth()+100,50);
            ueberschrift.setFont(ueberschrift.getFont().deriveFont(40f));
            gesamtPanel.add(ueberschrift);


            JButton btUebernehmen = new JButton();
            JButton btAbbrechen = new JButton();
            btUebernehmen.setText("Übernehmen");
            btUebernehmen.setSize(gesamtPanel.getWidth()/4,gesamtPanel.getHeight()/10);
            btUebernehmen.setLocation(gesamtPanel.getWidth()/2-btUebernehmen.getWidth()/2, gesamtPanel.getHeight()-2*btUebernehmen.getHeight());
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
            gesamtPanel.add(btAbbrechen);


            JPanel anzeige = new JPanel(new GridLayout(1,1));
            anzeige.setBorder(new TitledBorder("Highscore"));
            anzeige.setBackground(Color.lightGray);
            anzeige.setSize(gesamtPanel.getWidth()/2, gesamtPanel.getHeight()/2+gesamtPanel.getHeight()/10);
            anzeige.setLocation(gesamtPanel.getWidth()/2-anzeige.getWidth()/2, gesamtPanel.getHeight()/2-anzeige.getHeight()/2);


            JTable highscore = new JTable(10,2);
            highscore.setRowHeight(anzeige.getHeight()/11);
            highscore.setOpaque(true);
            highscore.setBorder(new LineBorder(Color.black, 3));



            anzeige.add(highscore);
            gesamtPanel.add(anzeige);
        }

        private void onExit() {
            this.dispose();
        }


        public static void main(String[] args) {

            gui2.setVisible(true);
        }
    }


