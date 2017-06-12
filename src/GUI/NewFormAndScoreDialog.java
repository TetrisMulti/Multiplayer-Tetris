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
    private Image img;
    private Color[][] colorField;
    private int widthOfOneField;
    private int heightOfOneField;
    private Score score;
    private JLabel lbscorePoints;


    public NewFormAndScoreDialog(int width, int height, Forms form,int widthOfOneField, int heightOfOneField,int location,Score score) {
        this.setAutoRequestFocus(false);
        this.screenWidth = width;
        this.screenHeight = height;
        this.setLocation(location,0);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.form = form;
        this.setResizable(false);
        this.colorField = new Color[10][6];
        this.widthOfOneField=widthOfOneField;
        this.heightOfOneField=heightOfOneField;
        this.score=score;
        System.out.println("1");
        initComponents();
        setForm(form);
        setScore(score);

    }

    private void initField() {
        for(int i=0;i<colorField.length;i++)
        {
            for(int j=0;j<colorField[i].length;j++)
            {
                colorField[i][j]=Color.DARK_GRAY;
            }
        }
    }

    private void initComponents() {
        Container cont = this.getContentPane();
        newFormPanel = new JPanel();
        scorePanel = new JPanel();
        scorePanel.setLayout(new GridLayout(2,1));
        lbscorePoints = new JLabel();
        JLabel lbNickName = new JLabel();

        newFormPanel.setSize(screenWidth,screenHeight/2);
        scorePanel.setSize(screenWidth,screenHeight/2);

        scorePanel.setBackground(Color.DARK_GRAY);
        scorePanel.setOpaque(true);

        lbscorePoints.setForeground(Color.RED);
        lbscorePoints.setText(score.getScore()+"");
        lbscorePoints.setHorizontalAlignment(JLabel.CENTER);
        lbNickName.setForeground(Color.GREEN);
        lbNickName.setText(score.getUser());
        lbNickName.setHorizontalAlignment(JLabel.CENTER);

        System.out.println(score.getUser());
        scorePanel.add(lbNickName);
        scorePanel.add(lbscorePoints);
        lbNickName.setFont(new Font("Comic Sans Ms",Font.PLAIN, (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()/100)));
        lbscorePoints.setFont(new Font("Comic Sans Ms",Font.PLAIN, (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()/100)));
        this.setSize(screenWidth,screenHeight);
        cont.setLayout(new GridLayout(2,1));
        cont.add(newFormPanel);
        cont.add(scorePanel);
        System.out.println("width->"+screenWidth+" height->"+screenHeight);
        this.setVisible(true);


    }

    public void setForm(Forms form) {
        this.form = form;
        Point2D[] pointField = CoordinatesOfForms.getPointCoords(form);
        initField();
        System.out.println("1mal");
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





    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) newFormPanel.getGraphics();
        //System.out.println(Arrays.deepToString(colorField));

        for (int i = 0; i < colorField.length; i++) {
            for (int j = 0; j < colorField[i].length; j++) {
                g2.setColor(colorField[i][j]);
                RoundRectangle2D rr = new RoundRectangle2D.Float(widthOfOneField * j, (heightOfOneField * i) + 2, widthOfOneField - 2, heightOfOneField - 2, 10, 10);
                g2.fill(rr);
            }
        }
    }

    public void setScore(Score score) {
        this.score = score;
        lbscorePoints.setText(score.getScore()+"");

    }


    //public static void main(String[] args) {
      //  new NewFormAndScoreDialog(500,1000, Forms.BLOCK,50,50,1700,new Score("asdf",0));
    //}



}
