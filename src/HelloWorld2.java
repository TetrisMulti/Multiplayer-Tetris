/**
 * Created by Leonhard on 15.06.2017.
 */



import ch.aplu.xboxcontroller.XboxController;
import ch.aplu.xboxcontroller.XboxControllerAdapter;

import javax.swing.*;

public class HelloWorld2 extends JFrame {

    private XboxController xc;

    public HelloWorld2()
    {
        initComponents();
    }


    private void initComponents()
    {
        xc = new XboxController();
        if (!xc.isConnected())
        {
            System.out.println("NOT CONNECTED");
            xc.release();
            return;
        }

        xc.setRightThumbDeadZone(0.3);

        xc.addXboxControllerListener(new XboxControllerAdapter(){
            @Override
            public void leftTrigger(double value) {
                xc.vibrate(50000, 0);
            }

            @Override
            public void rightTrigger(double value) {
                xc.vibrate(0, 50000);
            }

            @Override
            public void start(boolean pressed) {
                System.out.println("Hallo");
            }

            @Override
            public void rightThumbDirection(double direction) {
                System.out.println("Value: "+direction);
            }
        });
    }

    public static void main(String[] args) {

        HelloWorld2 hw = new HelloWorld2();
        hw.setVisible(true);

    }


}
