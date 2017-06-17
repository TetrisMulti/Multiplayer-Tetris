package BL;

import Beans.PunkteEnum;

/**
 * Created by ganleb13 on 05.05.2017.
 * Class to calculate the Points
 */
public class PunkteCalculator {

    /**
     * In this class we easily calculate the Points:
     *  - One Row
     *      + 25 Points
     *  - More Rows
     *      + 25 * Multiplicator*rows
     * @param rows --> The number of rows finished
     * @return --> the points the player will get
     */
    public static int calculateRowPoints(int rows)
    {
        if(rows==1) {
            return (int) PunkteEnum.FIXPUNKTE.getPoints();
        }
        else
        return (int) (PunkteEnum.FIXPUNKTE.getPoints() * PunkteEnum.MULTIPLICATOR.getPoints()*rows);
    }


}
