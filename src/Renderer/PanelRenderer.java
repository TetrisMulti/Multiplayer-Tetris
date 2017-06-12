package Renderer;

import GUI.TetrisGUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Chris on 14.03.2017.
 */
public class PanelRenderer extends JPanel {
    @Override
    /**
     * renderer for the panel in the TetrisGUI
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        TetrisGUI.tetrisGui.repaint(g);
    }
}
