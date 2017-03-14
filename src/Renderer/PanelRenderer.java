package Renderer;

import GUI.TetrisGUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Chris on 14.03.2017.
 */
public class PanelRenderer extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        TetrisGUI.tetrisGui.repaint(g);
    }
}
