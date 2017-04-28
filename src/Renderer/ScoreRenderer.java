package Renderer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Created by ganleb13 on 01.04.2017.
 */
public class ScoreRenderer extends DefaultTableCellRenderer {

    public ScoreRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        //comp.setBackground(row%2==0?new Color(195, 198, 199):Color.white);
        comp.setBackground(new Color(0, 0, 0, 0));
        //comp.setForeground(row%2==0?new Color(246, 255, 0):new Color(0, 221, 255));
        comp.setForeground(Color.WHITE);



        return comp;
    }
}
