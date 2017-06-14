/**
 * Created by ganleb13 on 13.06.2017.
 */

import com.ivan.xinput.*;
import com.ivan.xinput.exceptions.XInputNotLoadedException;

public class HelloWorld2 {

    public static void main(String[] args) {
        // check if XInput 1.4 is available
        if (XInputDevice14.isAvailable()) {
            System.out.println("XInput 1.4 is available on this platform.");
            try {
                XInputDevice14[] xi = XInputDevice14.getAllDevices();

                XInputDevice14 dev = XInputDevice14.getDeviceFor(0);
                if (dev.poll())
                {
                    XInputComponents comps = dev.getComponents();
                    XInputButtons buttons = comps.getButtons();
                    XInputAxes axes = comps.getAxes();

                    int leftMotor = 25000;
                    int rightMotor = 25000;
                    dev.setVibration(leftMotor, rightMotor);
                }
            } catch (XInputNotLoadedException e) {
                e.printStackTrace();
            }

        }
    }
}
