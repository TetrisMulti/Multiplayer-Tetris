package GUI;


import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Created by ganleb13 on 16.03.2017.
 * In this class we will define the GameOver-Screen
 */
public class GameOverGUI extends JFrame{

    private JPanel paImgList;
    private JPanel paButtons;
    private JTable tbTabelle;
    private JButton btExit;
    private JButton btBackToMenu;
    private JLabel lbImg;

    public void initComponents()
    {

        Dimension scrnSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle winSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        int taskBarHeight = scrnSize.height - winSize.height;
        int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 3;
        int height = ((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - taskBarHeight) / 2;

        this.setSize(width, height);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);


        Container cont = this.getContentPane();
        paImgList = new JPanel();
        paButtons = new JPanel();
        btExit = new JButton();
        btBackToMenu = new JButton();
        lbImg = new JLabel();


        paImgList.setLayout(new GridLayout(2, 1));
        paButtons.setLayout(new GridLayout(1, 2));

        String filename = System.getProperty("user.dir") + File.separator + "src" +
                File.separator + "res" + File.separator + "Game_Over.jpg";
        lbImg.setIcon(new ImageIcon(filename));
        paImgList.add(lbImg);

        btExit.setText("Exit");
        btExit.setSize(200, 500);
        btBackToMenu.setText("Back to Menu");
        btBackToMenu.setSize(200, 50);

        paButtons.add(btExit);
        paButtons.add(btBackToMenu);
        paButtons.setSize(width, 1000);

        cont.setLayout(new BorderLayout());
        cont.add(paImgList, BorderLayout.CENTER);
        cont.add(paButtons, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        GameOverGUI gui = new GameOverGUI();
        gui.initComponents();
        gui.setVisible(true);
    }
}


