package GUI;

import Beans.CoordinatesOfForms;
import Beans.Forms;
import Beans.Score;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.geom.Point2D;
import java.util.Arrays;

/**
 * Created by Christoph on 01.06.2017.
 */
public class NewFormAndScoreDialog extends JFrame {
    private int screenWidth;
    private int screenHeight;
    private JPanel newFormPanel;
    private JPanel scorePanel;
    private Forms form;
    private Color[][] colorField;
    private int widthOfOneField;
    private int heightOfOneField;
    private Score score;
    private JLabel lbscorePoints;
    private JLabel lbLevel;


    public NewFormAndScoreDialog(int width, int height, Forms form,int widthOfOneField, int heightOfOneField,int location,Score score) {
        this.setAutoRequestFocus(false);
        this.screenWidth = width;
        this.screenHeight = height;
        this.setLocation(location,0);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.form = form;
        this.setResizable(false);
        this.colorField = new Color[10][6];
        this.widthOfOneField=widthOfOneField;
        this.heightOfOneField=heightOfOneField;
        this.score=score;
        initComponents();
        setForm(form);
        setScore(score);

    }

    /**
     * initialize Colorfield
     */
    private void initField() {
        for(int i=0;i<colorField.length;i++)
        {
            for(int j=0;j<colorField[i].length;j++)
            {
                colorField[i][j]=Color.DARK_GRAY;
            }
        }
    }

    /**
     * adds newFormPanel and scorepanel to the dialog
     * scorepanel contains nickname label and score label
     */
    private void initComponents() {
        Container cont = this.getContentPane();
        newFormPanel = new JPanel();
        scorePanel = new JPanel();
        lbLevel=new JLabel();
        scorePanel.setLayout(new GridLayout(5,1));
        lbscorePoints = new JLabel();
        JLabel lbNickName = new JLabel();
        JLabel lbLevelHeader = new JLabel();
        JLabel lbScoreHeader = new JLabel();
       // JLabel lbFillerLabel = new JLabel();

        newFormPanel.setSize(screenWidth,screenHeight/2);
        scorePanel.setSize(screenWidth,screenHeight/2);

        scorePanel.setBackground(Color.DARK_GRAY);
        scorePanel.setOpaque(true);

        lbLevelHeader.setForeground(Color.YELLOW);
        lbLevelHeader.setText(score.getScore()+"");
        lbLevelHeader.setHorizontalAlignment(JLabel.CENTER);
        lbLevelHeader.setText("Level:");

        lbScoreHeader.setForeground(Color.RED);
        lbScoreHeader.setText(score.getScore()+"");
        lbScoreHeader.setHorizontalAlignment(JLabel.CENTER);
        lbScoreHeader.setText("Score:");

        lbscorePoints.setForeground(Color.RED);
        lbscorePoints.setText(score.getScore()+"");
        lbscorePoints.setHorizontalAlignment(JLabel.CENTER);

        lbNickName.setForeground(Color.GREEN);
        lbNickName.setText(score.getUser());
        lbNickName.setHorizontalAlignment(JLabel.CENTER);

        lbLevel.setForeground(Color.YELLOW);
        lbLevel.setText(score.getScore()+"");
        lbLevel.setHorizontalAlignment(JLabel.CENTER);

        lbLevel.setText("1");
       // scorePanel.add(lbFillerLabel);
        scorePanel.add(lbNickName);
        scorePanel.add(lbLevelHeader);
        scorePanel.add(lbLevel);
        scorePanel.add(lbScoreHeader);
        scorePanel.add(lbscorePoints);
        lbNickName.setFont(new Font("Comic Sans Ms",Font.PLAIN, (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()/100)));
        lbscorePoints.setFont(new Font("Comic Sans Ms",Font.PLAIN, (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()/100)));
        lbLevel.setFont(new Font("Comic Sans Ms",Font.PLAIN, (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()/100)));
        lbLevelHeader.setFont(new Font("Comic Sans Ms",Font.PLAIN, (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()/100)));
        lbScoreHeader.setFont(new Font("Comic Sans Ms",Font.PLAIN, (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()/100)));
        this.setSize(screenWidth,screenHeight);
        cont.setLayout(new GridLayout(2,1));
        cont.add(newFormPanel);
        cont.add(scorePanel);
        this.setVisible(true);
    }

    /**
     * update colorfield with the next form
     * @param form -> form thats set
     */
    public void setForm(Forms form) {
        this.form = form;
        Point2D[] pointField = CoordinatesOfForms.getPointCoords(form);
        initField();
        for(int i=0;i<pointField.length;i++)
        {
            if(form == Forms.STICK)
            {
                colorField[(int)(4+pointField[i].getX())][(int)(1+pointField[i].getY())] = form.getC();
            }
            else
            {
                colorField[(int)(4+pointField[i].getX())][(int)(2+pointField[i].getY())] = form.getC();
            }
        }

        repaint();
    }


    /**
     * paintmethod
     * paints the colorfield to the newFormPanel
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) newFormPanel.getGraphics();

        for (int i = 0; i < colorField.length; i++) {
            for (int j = 0; j < colorField[i].length; j++) {
                g2.setColor(colorField[i][j]);
                RoundRectangle2D rr = new RoundRectangle2D.Float(widthOfOneField * j, (heightOfOneField * i) + 2, widthOfOneField - 2, heightOfOneField - 2, 10, 10);
                g2.fill(rr);
            }
        }
    }

    /**
     * sets score on the scorelabel
     * @param score
     */
    public void setScore(Score score) {
        this.score = score;
        lbscorePoints.setText(score.getScore()+"");

    }
    public void setLevel(int level)
    {
        lbLevel.setText(level+"");
    }
}
