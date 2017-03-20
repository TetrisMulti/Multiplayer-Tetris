package BL;

import java.awt.geom.Point2D;

/**
 * Created by Christoph on 16.03.2017.
 */
public class CoordinatesOfForms {

    public static int[][] getCords(Forms form)
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
    }


    public static Point2D[] getPointCoords(Forms form)
    {
        switch(form)
        {
            case STICK:return new Point2D[]{new Point2D.Double(0,0),new Point2D.Double(0,1),new Point2D.Double(0,2),new Point2D.Double(0,3)};
            case BLOCK:return new Point2D[]{new Point2D.Double(0,0),new Point2D.Double(0,1),new Point2D.Double(1,0),new Point2D.Double(1,1)};
            case STAIRRIGHT:return new Point2D[]{new Point2D.Double(0,0),new Point2D.Double(0,2),new Point2D.Double(1,0),new Point2D.Double(1,1)};
            case STAIRLEFT:return new Point2D[]{new Point2D.Double(0,0),new Point2D.Double(0,1),new Point2D.Double(1,1),new Point2D.Double(1,2)};
            case LEFTL:return new Point2D[]{new Point2D.Double(0,0),new Point2D.Double(1,0),new Point2D.Double(1,1),new Point2D.Double(1,2)};
            case RIGHTL:return new Point2D[]{new Point2D.Double(0,2),new Point2D.Double(1,0),new Point2D.Double(1,1),new Point2D.Double(1,2)};
            case POTEST:return new Point2D[]{new Point2D.Double(0,1),new Point2D.Double(1,0),new Point2D.Double(1,1),new Point2D.Double(1,2)};

            default:return new Point2D[]{new Point2D.Double(0,0),new Point2D.Double(0,1),new Point2D.Double(0,2),new Point2D.Double(0,3)};
        }
    }
}
