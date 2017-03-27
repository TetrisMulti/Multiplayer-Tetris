package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Hugo on 27.03.2017.
 */

public class SettingsGUI extends JFrame{
    protected static SettingsGUI gui1 = new SettingsGUI();

    public SettingsGUI() {
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

        JButton btExit = new JButton();
        btExit.setText("X");
        btExit.setBackground(Color.red);
        btExit.setForeground(Color.white);
        btExit.setSize(gesamtPanel.getWidth()/12, gesamtPanel.getHeight()/14);
        btExit.setLocation(gesamtPanel.getWidth()-btExit.getWidth(), gesamtPanel.getHeight()+btExit.getHeight()-gesamtPanel.getHeight()-btExit.getHeight());


        btExit.addActionListener(e -> {
            try {
                onExit();
            } catch (Exception ex) {
                System.out.println("Programm konnte nicht beendet werden");
            }
        });


        gesamtPanel.add(btExit);
    }

    private void onExit() {
        this.dispose();
    }


    public static void main(String[] args) {

        gui1.setVisible(true);
    }
}
