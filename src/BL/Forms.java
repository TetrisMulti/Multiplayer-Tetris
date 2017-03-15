package BL;

import java.awt.*;

/**
 * Created by Christoph on 14.03.2017.
 * Enum Klasse für die einzelnen Tetris Formen
 */
public enum Forms {

    STICK(Color.GREEN,"0,0#0,1#0,2#0,3"),
    BLOCK(Color.BLUE,"0,0#0,1#1,0#1,1"),
    STAIRRIGHT(Color.BLUE.brighter(),"0,1#0,2#1,0#1,1"),
    STAIRLEFT(Color.gray,"0,0#0,1#1,1#1,2"),
    LEFTL(Color.orange,"0,0#1,0#1,1#1,2#"),
    RIGHTL(Color.red,"0,2#1,0#1,1#1,2"),
    POTEST(Color.yellow,"0,1#1,0#1,1#1,2")
    ;


    private Color c;
    private String blocks;

    Forms(Color c, String blocks) {
        this.c = c;
        this.blocks = blocks;
    }

    public Color getC() {
        return c;
    }

    public String getBlocks() {
        return blocks;
    }
}
