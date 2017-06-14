package Beans;

import java.awt.*;

/**
 * Created by Christoph on 14.03.2017.
 * Enum Klasse für die einzelnen Tetris Formen mit Farben
 */
public enum Forms {

    STICK(new Color(0,240,240)),
    BLOCK(new Color(249,240,0)),
    STAIRRIGHT(new Color(0,240,0)),
    STAIRLEFT(new Color(240,0,0)),
    LEFTL(new Color(0,0,240)),
    RIGHTL(new Color(240,160,0)),
    POTEST(new Color(160,0,240))
    ;


    private Color c;


    Forms(Color c) {
        this.c = c;

    }

    public Color getC() {
        return c;
    }

//
}
