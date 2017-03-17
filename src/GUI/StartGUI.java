package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Christoph on 13.03.2017.
 */
public class StartGUI extends JFrame {
    protected static StartGUI gui=new StartGUI();

    public StartGUI() {
        initComponents();


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
        gesamtPanel.setBackground(Color.red);
        JPanel panel1 = new JPanel();


        JButton btStart = new JButton();
        btStart.setText("Start");
        panel1.setBackground(Color.darkGray);
        //btStart.setSize(width/10, height/10);
        //btStart.setLocation(width/10,height/10);
        panel1.add(btStart);



        JButton btHighScore = new JButton();
        btHighScore.setText("HighScore");
        panel1.add(btHighScore);



        JButton btSettings = new JButton();
        btSettings.setText("Settings");
        panel1.add(btSettings);


        gesamtPanel.add(panel1);

        Container cont = this.getContentPane();
        cont.setLayout(new BorderLayout());
        cont.add(gesamtPanel, BorderLayout.CENTER);
        gesamtPanel.setLayout(null);
        gesamtPanel.setSize(width, height);
        panel1.setSize(gesamtPanel.getWidth()/5,gesamtPanel.getHeight()/3);
        panel1.setLocation(gesamtPanel.getWidth()/2-gesamtPanel.getWidth()/8,gesamtPanel.getHeight()/2-gesamtPanel.getHeight()/3);
        btStart.setSize(30,80);
        btStart.addActionListener((e) -> onStart());


        //cont.add(btStart);
        //cont.setLayout(null);

        System.out.println(width+" "+height);


        System.out.println("Thomas is gay");
    }

    private void onStart() {
        String nickname=JOptionPane.showInputDialog(this,"Bitte Nicknamen eingeben!");
        TetrisGUI tGui = new TetrisGUI(nickname,gui);
        tGui.setVisible(true);
        this.setVisible(false);
    }


    public static void main(String[] args) {

        gui.setVisible(true);
    }
}
