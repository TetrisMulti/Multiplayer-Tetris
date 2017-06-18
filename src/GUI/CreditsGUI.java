package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Hugo on 28.04.2017.
 * The Credits GUI only shows a small Sentence
 */
public class CreditsGUI extends JDialog{

    public CreditsGUI() {
        initComponents();
        this.setResizable(false);
    }

    /**
     * This Method initializes everything
      */
    private void initComponents() {
        Dimension scrnSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle winSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        int taskBarHeight = scrnSize.height - winSize.height;


        int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 4;
        int height = ((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - taskBarHeight) / 3;


        this.setSize(width, height);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);


        JPanel pnFullPanel = new JPanel();
        pnFullPanel.setBackground(Color.white);
        pnFullPanel.setLayout(null);
        pnFullPanel.setSize(width, height);


        Container cont = this.getContentPane();
        cont.setLayout(new BorderLayout());
        cont.add(pnFullPanel, BorderLayout.CENTER);


        JLabel lbTitle = new JLabel();
        lbTitle.setText("Credits");
        lbTitle.setSize(pnFullPanel.getWidth(),pnFullPanel.getHeight()/10);
        lbTitle.setFont(new Font("Arial", Font.BOLD, lbTitle.getHeight()-lbTitle.getHeight()/10));
        lbTitle.setHorizontalAlignment(JLabel.CENTER);
        lbTitle.setForeground(Color.yellow);
        pnFullPanel.add(lbTitle);


        JLabel lbFullLabel = new JLabel();
        lbFullLabel.setSize(width, height);
        lbFullLabel.setBackground(Color.black);
        lbFullLabel.setOpaque(true);
        pnFullPanel.add(lbFullLabel);


        JPanel pnCrPanel = new JPanel(new GridLayout(1,1));
        pnCrPanel.setSize(lbFullLabel.getWidth(), lbFullLabel.getHeight()/2);
        pnCrPanel.setLocation(lbFullLabel.getWidth()-pnCrPanel.getWidth(), lbFullLabel.getHeight()/2-pnCrPanel.getHeight()/2);
        pnCrPanel.setOpaque(true);
        pnCrPanel.setBackground(Color.black);
        lbFullLabel.add(pnCrPanel);


        JLabel lbCredits = new JLabel();
        lbCredits.setText("Tetris made by Pommer, Hirzer, Gangl");
        lbCredits.setFont(new Font("Arial",Font.BOLD+Font.CENTER_BASELINE,lbFullLabel.getHeight()/11));
        lbCredits.setForeground(Color.white);
        lbCredits.setHorizontalAlignment(JLabel.CENTER);
        pnCrPanel.add(lbCredits);


        JButton btClose = new JButton();
        btClose.setText("Schließen");
        btClose.setSize(pnFullPanel.getWidth()/4,pnFullPanel.getHeight()/10);
        btClose.setLocation(pnFullPanel.getWidth()/2+btClose.getWidth()-btClose.getWidth()/3, pnFullPanel.getHeight()-2*btClose.getHeight());
        btClose.addActionListener(e -> {
            try {
                onExit();
            } catch (Exception ex) {
                System.out.println("Programm konnte nicht beendet werden");
            }
        });
        btClose.setFont(new Font("Arial", Font.BOLD, btClose.getHeight()/3));
        lbFullLabel.add(btClose);
    }

    private void onExit() {
        this.dispose();
    }
}
