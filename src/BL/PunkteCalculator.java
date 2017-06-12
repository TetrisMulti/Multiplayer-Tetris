package BL;

import Beans.PunkteEnum;

import static Beans.PunkteEnum.MULTIPLICATOR;

/**
 * Created by ganleb13 on 05.05.2017.
 * Class to calculate the Points
 */
public class PunkteCalculator {

    public static int calculateRowPoints(int rows)
    {

        if(rows==1)
        {
            return (int) PunkteEnum.FIXPUNKTE.getPoints();
        }
        else
        return (int) (PunkteEnum.FIXPUNKTE.getPoints() * PunkteEnum.MULTIPLICATOR.getPoints()*rows);
    }


}
