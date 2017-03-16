package BL;

import java.awt.*;

/**
 * Created by Christoph on 14.03.2017.
 * Enum Klasse für die einzelnen Tetris Formen
 */
public enum Forms {

    STICK(Color.GREEN),
    BLOCK(Color.BLUE),
    STAIRRIGHT(Color.BLUE.brighter()),
    STAIRLEFT(Color.gray),
    LEFTL(Color.orange),
    RIGHTL(Color.red),
    POTEST(Color.yellow)
    ;


    private Color c;


    Forms(Color c) {
        this.c = c;

    }

    public Color getC() {
        return c;
    }


}
