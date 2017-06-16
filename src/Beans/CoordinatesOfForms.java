package Beans;

import java.awt.geom.Point2D;

/**
 * Created by Christoph on 16.03.2017.
 */
public class CoordinatesOfForms {


    /**
     * Methods to get start Coordinates of Forms and the rotated
     *
     * @param form
     * @return start Coordinates of Forms
     */
    public static Point2D[] getPointCoords(Forms form) {
        switch (form) {
            case STICK:
                return new Point2D[]{new Point2D.Double(0, 1), new Point2D.Double(0, 0), new Point2D.Double(0, 2), new Point2D.Double(0, 3)};
            case BLOCK:
                return new Point2D[]{new Point2D.Double(0, 0), new Point2D.Double(0, 1), new Point2D.Double(1, 0), new Point2D.Double(1, 1)};
            case STAIRRIGHT:
                return new Point2D[]{new Point2D.Double(1, 1), new Point2D.Double(0, 1), new Point2D.Double(1, 0), new Point2D.Double(2, 0)};
            case STAIRLEFT:
                return new Point2D[]{new Point2D.Double(1, 1), new Point2D.Double(0, 0), new Point2D.Double(0, 1), new Point2D.Double(1, 2)};
            case LEFTL:
                return new Point2D[]{new Point2D.Double(0, 1), new Point2D.Double(0, 0), new Point2D.Double(0, 2), new Point2D.Double(1, 2)};
            case RIGHTL:
                return new Point2D[]{new Point2D.Double(1, 1), new Point2D.Double(0, 2), new Point2D.Double(1, 0), new Point2D.Double(1, 2)};
            case POTEST:
                return new Point2D[]{new Point2D.Double(1, 1), new Point2D.Double(0, 1), new Point2D.Double(1, 0), new Point2D.Double(2, 1)};

            default:
                return new Point2D[]{new Point2D.Double(0, 0), new Point2D.Double(0, 1), new Point2D.Double(0, 2), new Point2D.Double(0, 3)};
        }
    }

    /**
     * Rotation Algorythm
     * Made with a Rotation Matrix
     * vr -> gets the Vector from Pivot Block to current block
     * vt -> rotate block in relation to Pivot Block
     * vs -> get coordinates from blocks back by adding the pivot block coordinates
     *
     * second for -> tests if some block is at a negative coordinate and if it is true it moves the Tetronium at positive coordinates
      * @param aktPoint -> Coordinates of form that should be rotated
     * @param direction -> direction of rotation
     * @return
     */

    public static Point2D[] rotateForm(Point2D[] aktPoint, int direction) {

        Point2D[] tempField;
        tempField = new Point2D[4];
        tempField[0] = aktPoint[0];
        for (int i = 1; i < aktPoint.length; i++) {
            Point2D vr = new Point2D.Double(aktPoint[i].getX() - aktPoint[0].getX(), aktPoint[i].getY() - aktPoint[0].getY());
            Point2D vt = new Point2D.Double((-1 * direction) * vr.getY(), (1 * direction) * vr.getX());
            Point2D vs = new Point2D.Double(aktPoint[0].getX() + vt.getX(), aktPoint[0].getY() + vt.getY());
            tempField[i] = vs;
        }

        int isOkx = 0;
        int isOky = 0;
        for (int i = 0; i < tempField.length; i++) {
            if (tempField[i].getX() < 0) {
                isOkx = 1;
                for (int j = 0; j < tempField.length; j++) {
                    tempField[j].setLocation(tempField[j].getX() + isOkx, tempField[j].getY());
                }
            } else if (tempField[i].getY() < 0) {
                isOky = 1;
                for (int j = 0; j < tempField.length; j++) {
                    tempField[j].setLocation(tempField[j].getX(), tempField[j].getY() + isOky);
                }
            }
        }
        return tempField;

    }
}
