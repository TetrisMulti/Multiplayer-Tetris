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
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
            ueberschrift.setSize(gesamtPanel.getWidth(),gesamtPanel.getHeight()/10);
            ueberschrift.setFont(new Font("Arial", Font.BOLD, ueberschrift.getHeight()-ueberschrift.getHeight()/10));
            gesamtPanel.add(ueberschrift);



            JButton btSchliessen = new JButton();
            btSchliessen.setText("Abbrechen");
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
            gesamtPanel.add(btSchliessen);


            JPanel anzeige = new JPanel(new GridLayout(1,1));
            anzeige.setBorder(new TitledBorder("Highscore"));
            anzeige.setBackground(Color.lightGray);
            anzeige.setSize(gesamtPanel.getWidth()/2, gesamtPanel.getHeight()/2+gesamtPanel.getHeight()/10);
            anzeige.setLocation(gesamtPanel.getWidth()/2-anzeige.getWidth()/2, gesamtPanel.getHeight()/2-gesamtPanel.getHeight()/3);


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
            new HighScoreGUI().setVisible(true);

        }
    }


