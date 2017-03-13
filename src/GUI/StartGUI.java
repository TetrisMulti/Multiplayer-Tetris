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

        JButton btStart = new JButton();
        btStart.setText("Start");
        Container cont = this.getContentPane();
        cont.setLayout(new BorderLayout());
        btStart.addActionListener((e) -> onStart());
        cont.add(btStart, BorderLayout.CENTER);

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
