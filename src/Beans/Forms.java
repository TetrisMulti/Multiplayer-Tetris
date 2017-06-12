package Beans;

import java.awt.*;

/**
 * Created by Christoph on 14.03.2017.
 * Enum Klasse für die einzelnen Tetris Formen mit Farben
 */
public enum Forms {

    STICK(new Color(30,255,1)),
    BLOCK(new Color(1,0,254)),
    STAIRRIGHT(new Color(0,199,254)),
    STAIRLEFT(Color.gray),
    LEFTL(new Color(255,149,10)),
    RIGHTL(new Color(254,0,2)),
    POTEST(new Color(255,199,0))
    ;


    private Color c;


    Forms(Color c) {
        this.c = c;

    }

    public Color getC() {
        return c;
    }


}
