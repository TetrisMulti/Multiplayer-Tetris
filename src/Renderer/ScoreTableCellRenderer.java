package Renderer;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Created by ganleb13 on 24.04.2017.
 */
public class ScoreTableCellRenderer extends JLabel implements TableCellRenderer {
    private final Border b = BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK),
            BorderFactory.createEmptyBorder(2,2,1,2));
    private final Color alphaZero = new Color(0, true);

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        this.setText(value!=null?value.toString():"");
        this.setHorizontalAlignment(JLabel.CENTER);

        //this.setBackground(alphaZero);
        this.setForeground(Color.WHITE);
        this.setBorder(b);
        this.setOpaque(true);
        return this;
    }
}
