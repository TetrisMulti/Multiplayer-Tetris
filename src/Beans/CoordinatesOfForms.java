package Beans;

import java.awt.geom.Point2D;

/**
 * Created by Christoph on 16.03.2017.
 */
public class CoordinatesOfForms {

/*    public static int[][] getCords(Forms form)
    {
        switch(form)
        {
            case STICK:return new int[][]{ {0,0},{0,1},{0,2},{0,3}};

            case BLOCK:return new int[][]{ {0,0},{0,1},{1,0},{1,1}};

            case STAIRRIGHT:return new int[][]{ {0,1},{0,2},{1,0},{1,1}};

            case STAIRLEFT:return new int[][]{ {0,0},{0,1},{1,1},{1,2}};

            case LEFTL:return new int[][]{ {0,0},{1,0},{1,1},{1,2}};

            case RIGHTL:return new int[][]{ {0,2},{1,0},{1,1},{1,2}};

            case POTEST:return new int[][]{ {0,1},{1,0},{1,1},{1,2}};

                default:return new int[][]{ {0,0},{0,1},{0,2},{0,3}};
        }
    }*/


    public static Point2D[] getPointCoords(Forms form)
    {
        switch(form)
        {
            case STICK:return new Point2D[]{new Point2D.Double(0,0),new Point2D.Double(0,1),new Point2D.Double(0,2),new Point2D.Double(0,3)};
            case BLOCK:return new Point2D[]{new Point2D.Double(0,0),new Point2D.Double(0,1),new Point2D.Double(1,0),new Point2D.Double(1,1)};
            case STAIRRIGHT:return new Point2D[]{new Point2D.Double(0,1),new Point2D.Double(0,2),new Point2D.Double(1,0),new Point2D.Double(1,1)};
            case STAIRLEFT:return new Point2D[]{new Point2D.Double(0,0),new Point2D.Double(0,1),new Point2D.Double(1,1),new Point2D.Double(1,2)};
            case LEFTL:return new Point2D[]{new Point2D.Double(0,0),new Point2D.Double(1,0),new Point2D.Double(1,1),new Point2D.Double(1,2)};
            case RIGHTL:return new Point2D[]{new Point2D.Double(0,2),new Point2D.Double(1,0),new Point2D.Double(1,1),new Point2D.Double(1,2)};
            case POTEST:return new Point2D[]{new Point2D.Double(0,1),new Point2D.Double(1,0),new Point2D.Double(1,1),new Point2D.Double(1,2)};

            default:return new Point2D[]{new Point2D.Double(0,0),new Point2D.Double(0,1),new Point2D.Double(0,2),new Point2D.Double(0,3)};
        }
    }


    public static Point2D[] rotatetForm(Forms form, int index)
    {
        switch(form) {
            case STICK:return getCoordsofStick(index);
            case BLOCK:return new Point2D[]{new Point2D.Double(0,0),new Point2D.Double(0,1),new Point2D.Double(1,0),new Point2D.Double(1,1)};
            case STAIRRIGHT:return getCoordsofStairRight(index);
            case STAIRLEFT:return getCoordsofStairLeft(index);
            case LEFTL:return getCoordsofLeftL(index);
            case RIGHTL:return getCoordsOfRigthL(index);
            case POTEST:return getCoordsofPotest(index);

        }
        return null;
    }

    private static Point2D[] getCoordsofStairLeft(int index) {
        switch (index)
        {
            case 0:return new Point2D[]{new Point2D.Double(0,0),new Point2D.Double(0,1),new Point2D.Double(1,1),new Point2D.Double(1,2)};
            case 1:return new Point2D[]{new Point2D.Double(0,1),new Point2D.Double(1,0),new Point2D.Double(1,1),new Point2D.Double(2,0)};
            case 2:return new Point2D[]{new Point2D.Double(0,0),new Point2D.Double(0,1),new Point2D.Double(1,1),new Point2D.Double(1,2)};
            case 3:return new Point2D[]{new Point2D.Double(0,1),new Point2D.Double(1,0),new Point2D.Double(1,1),new Point2D.Double(2,0)};
            default:return new Point2D[]{new Point2D.Double(0,0),new Point2D.Double(0,1),new Point2D.Double(1,1),new Point2D.Double(1,2)};
        }
    }

    private static Point2D[] getCoordsOfRigthL(int index) {
        switch (index)
        {
            case 0:return new Point2D[]{new Point2D.Double(0,2),new Point2D.Double(1,0),new Point2D.Double(1,1),new Point2D.Double(1,2)};
            case 1:return new Point2D[]{new Point2D.Double(0,0),new Point2D.Double(1,0),new Point2D.Double(2,0),new Point2D.Double(2,1)};
            case 2:return new Point2D[]{new Point2D.Double(0,0),new Point2D.Double(0,1),new Point2D.Double(0,2),new Point2D.Double(1,0)};
            case 3:return new Point2D[]{new Point2D.Double(0,0),new Point2D.Double(0,1),new Point2D.Double(1,1),new Point2D.Double(2,1)};
            default:return new Point2D[]{new Point2D.Double(0,2),new Point2D.Double(1,0),new Point2D.Double(1,1),new Point2D.Double(1,2)};
        }
    }

    private static Point2D[] getCoordsofLeftL(int index) {
        switch (index)
        {
            case 0:return new Point2D[]{new Point2D.Double(0,0),new Point2D.Double(1,0),new Point2D.Double(1,1),new Point2D.Double(1,2)};
            case 1:return new Point2D[]{new Point2D.Double(0,0),new Point2D.Double(0,1),new Point2D.Double(1,0),new Point2D.Double(2,0)};
            case 2:return new Point2D[]{new Point2D.Double(0,0),new Point2D.Double(0,1),new Point2D.Double(0,2),new Point2D.Double(1,2)};
            case 3:return new Point2D[]{new Point2D.Double(2,0),new Point2D.Double(0,1),new Point2D.Double(1,1),new Point2D.Double(2,1)};
            default:return new Point2D[]{new Point2D.Double(0,0),new Point2D.Double(1,0),new Point2D.Double(1,1),new Point2D.Double(1,2)};
        }
    }

    private static Point2D[] getCoordsofStairRight(int index) {
        switch (index)
        {
            case 0:return new Point2D[]{new Point2D.Double(0,1),new Point2D.Double(0,2),new Point2D.Double(1,0),new Point2D.Double(1,1)};
            case 1:return new Point2D[]{new Point2D.Double(0,0),new Point2D.Double(1,0),new Point2D.Double(1,1),new Point2D.Double(2,1)};
            case 2:return new Point2D[]{new Point2D.Double(0,1),new Point2D.Double(0,2),new Point2D.Double(1,0),new Point2D.Double(1,1)};
            case 3:return new Point2D[]{new Point2D.Double(0,0),new Point2D.Double(1,0),new Point2D.Double(1,1),new Point2D.Double(2,1)};
            default:return new Point2D[]{new Point2D.Double(0,1),new Point2D.Double(0,2),new Point2D.Double(1,0),new Point2D.Double(1,1)};
        }
    }

    private static Point2D[] getCoordsofStick(int index) {
        //new Point2D[]{new Point2D.Double(0,0),new Point2D.Double(0,1),new Point2D.Double(0,2),new Point2D.Double(0,3)};

        switch (index)
        {
            case 0:return new Point2D[]{new Point2D.Double(0,0),new Point2D.Double(0,1),new Point2D.Double(0,2),new Point2D.Double(0,3)};
            case 1:return new Point2D[]{new Point2D.Double(0,0),new Point2D.Double(1,0),new Point2D.Double(2,0),new Point2D.Double(3,0)};
            case 2:return new Point2D[]{new Point2D.Double(0,0),new Point2D.Double(0,1),new Point2D.Double(0,2),new Point2D.Double(0,3)};
            case 3:return new Point2D[]{new Point2D.Double(0,0),new Point2D.Double(1,0),new Point2D.Double(2,0),new Point2D.Double(3,0)};
                default:return new Point2D[]{new Point2D.Double(0,0),new Point2D.Double(0,1),new Point2D.Double(0,2),new Point2D.Double(0,3)};
        }
    }

    private static Point2D[] getCoordsofPotest(int index) {
        switch (index)
        {
            case 0:return new Point2D[]{new Point2D.Double(0,1),new Point2D.Double(1,0),new Point2D.Double(1,1),new Point2D.Double(1,2)};
            case 1:return new Point2D[]{new Point2D.Double(0,0),new Point2D.Double(1,0),new Point2D.Double(1,1),new Point2D.Double(2,0)};
            case 2:return new Point2D[]{new Point2D.Double(1,0),new Point2D.Double(0,1),new Point2D.Double(1,1),new Point2D.Double(2,1)};
            case 3:return new Point2D[]{new Point2D.Double(0,0),new Point2D.Double(0,1),new Point2D.Double(0,2),new Point2D.Double(1,1)};
            default:return new Point2D[]{new Point2D.Double(0,1),new Point2D.Double(1,0),new Point2D.Double(1,1),new Point2D.Double(1,2)};
        }
    }
}
