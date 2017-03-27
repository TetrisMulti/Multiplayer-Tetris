package GUI;

import res.Res;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by Hugo & Christoph on 13.03.2017.
 */
public class StartGUI extends JFrame {
    protected static StartGUI gui=new StartGUI();

    public StartGUI() {
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
        JPanel panel1 = new JPanel();
        JLabel ueberschrift = new JLabel();
        gesamtPanel.setBackground(Color.lightGray);
        JLabel gesamtLabel = new JLabel();
        gesamtLabel.setSize(width, height);
        gesamtLabel.setOpaque(true);
        gesamtLabel.setBackground(Color.red);
        System.out.println(width+"  "+height);
        Image img = null;
        try {
            img = ImageIO.read(Res.class.getResourceAsStream("Tetris.jpg"));
           //gesamtLabel.setIcon(new ImageIcon(ImageIO.read(Res.class.getResourceAsStream("Tetris.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(dimg);
        gesamtLabel.setIcon(icon);
        gesamtPanel.add(gesamtLabel);


        JButton btStart = new JButton();
        btStart.setText("Start");
        panel1.setBackground(Color.black);
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
        panel1.setLocation(gesamtPanel.getWidth()/2-panel1.getWidth()/2,gesamtPanel.getHeight()/2-panel1.getHeight()/2);
        panel1.setLayout(null);
        btStart.setSize(160,50);
        btStart.setLocation(panel1.getWidth()/2-btStart.getWidth()/2, panel1.getHeight()/5-btStart.getHeight()/2);
        btHighScore.setSize(160,50);
        btHighScore.setLocation(panel1.getWidth()/2-btHighScore.getWidth()/2, panel1.getHeight()/2-btHighScore.getHeight()/2);
        btSettings.setSize(35,35);
        btSettings.setLocation((int) (gesamtPanel.getWidth()-btSettings.getWidth()*1.5),gesamtPanel.getHeight()-btSettings.getHeight()*2);
        btStart.addActionListener((e) -> onStart());
        ueberschrift.setText("Tetris");
        ueberschrift.setForeground(Color.yellow);
        //ueberschrift.setOpaque(true);
        ueberschrift.setSize(gesamtPanel.getWidth()+100,50);
        //ueberschrift.setLocation(gesamtPanel.getWidth()+100, gesamtPanel.getHeight()/2-150);
        ueberschrift.setFont(ueberschrift.getFont().deriveFont(40f));
        btExit.setSize(160,50);
        btExit.setLocation(panel1.getWidth()/2-btExit.getWidth()/2, panel1.getHeight()/2+btExit.getHeight());


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
