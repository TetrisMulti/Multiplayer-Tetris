package Beans;

/**
 * Created by ganleb13 on 05.05.2017.
 * Enum for the Points given when you finish a row
 * and it's multiplicator when you finish more than 1 row.
 */
public enum PunkteEnum {
    FIXPUNKTE(25.0),
    MULTIPLICATOR(1.5);

    private double points;

    PunkteEnum(double points) {
        this.points = points;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }
}
