package Renderer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Created by ganleb13 on 01.04.2017.
 */
public class ScoreRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        int col = table.convertColumnIndexToModel(column);

        if (col % 2 == 0) {
            comp.setBackground(new Color(195, 198, 199));
        }
        return comp;
    }
}
