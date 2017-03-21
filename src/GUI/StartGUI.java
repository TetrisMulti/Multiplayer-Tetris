package GUI;

import res.Res;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by Christoph on 13.03.2017.
 */
public class StartGUI extends JFrame {
    protected static StartGUI gui=new StartGUI();

    public StartGUI() {
        initComponents();
        this.setResizable(false);
        this.setSize(500,500);

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
        JPanel panel1 = new JPanel();
        JLabel ueberschrift = new JLabel();
        gesamtPanel.setBackground(Color.lightGray);
        JLabel gesamtLabel = new JLabel();
        gesamtLabel.setSize(width, height);
        gesamtLabel.setOpaque(true);
        gesamtLabel.setBackground(Color.red);
        /*try {
            gesamtLabel.setIcon(new ImageIcon(ImageIO.read(Res.class.getResourceAsStream("tetris-movie.jpg"))));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        gesamtPanel.add(gesamtLabel);


        JButton btStart = new JButton();
        btStart.setText("Start");
        panel1.setBackground(Color.darkGray);
        //btStart.setSize(width/10, height/10);
        //btStart.setLocation(width/10,height/10);
        panel1.add(btStart);



        JButton btHighScore = new JButton();
        btHighScore.setText("HighScore");
        panel1.add(btHighScore);
        JButton btExit = new JButton();
        btExit.setText("Beenden");
        panel1.add(btExit);


        JButton btSettings = new JButton();
        try {
            btSettings.setIcon(new ImageIcon(ImageIO.read(Res.class.getResourceAsStream("Settings.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        gesamtLabel.add(btSettings);
        gesamtLabel.add(ueberschrift);


        gesamtLabel.add(panel1);

        Container cont = this.getContentPane();
        cont.setLayout(new BorderLayout());
        cont.add(gesamtPanel, BorderLayout.CENTER);
        gesamtPanel.setLayout(null);
        gesamtPanel.setSize(width, height);
        panel1.setSize(gesamtPanel.getWidth()/2,gesamtPanel.getHeight()/2);
        panel1.setLocation(gesamtPanel.getWidth()/3-125,gesamtPanel.getHeight()/2-200);
        panel1.setLayout(null);
        btStart.setSize(160,50);
        btStart.setLocation(panel1.getWidth()/4, panel1.getHeight()/5-20);
        btHighScore.setSize(160,50);
        btHighScore.setLocation(panel1.getWidth()/4, panel1.getHeight()/2-25);
        btSettings.setSize(35,35);
        btSettings.setLocation(gesamtPanel.getWidth()/2-btSettings.getWidth(),gesamtPanel.getHeight()+btSettings.getHeight());
        btStart.addActionListener((e) -> onStart());
        ueberschrift.setText("Tetris");
        ueberschrift.setForeground(Color.yellow);
        //ueberschrift.setOpaque(true);
        ueberschrift.setSize(gesamtPanel.getWidth()+100,50);
        //ueberschrift.setLocation(gesamtPanel.getWidth()+100, gesamtPanel.getHeight()/2-150);
        ueberschrift.setFont(ueberschrift.getFont().deriveFont(40f));
        btExit.setSize(160,50);
        btExit.setLocation(panel1.getWidth()/4, panel1.getHeight()/2+50);


        btExit.addActionListener(e -> {
            try {
                onExit();
            } catch (Exception ex) {
                System.out.println("Programm konnte nicht beendet werden");
            }
        });

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

    private void onExit() {
        System.exit(0);
    }


    public static void main(String[] args) {

        gui.setVisible(true);
    }
}
