package Beans;

import BL.ScoreTableModel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

import Renderer.ScoreTableCellRenderer;
import Renderer.ScoreRenderer;

import java.awt.*;

/**
 * Created by ganleb13 on 08.05.2017.
 *
 */
public class ScoreTable extends JTable {

    private ScoreTableModel stm;
    private ScoreTableCellRenderer stcr;
    private DefaultTableColumnModel dtcm;
    private ScoreRenderer sc;
    private Border nullBorder = BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0,0,1,0, Color.BLACK),
            BorderFactory.createEmptyBorder(2,2,1,2));
    private String[] sname = {"Rank", "User", "Score"};

    public ScoreTable() {
        stm = new ScoreTableModel();
        stcr = new ScoreTableCellRenderer();
        dtcm = new DefaultTableColumnModel();
        sc = new ScoreRenderer();
        this.setModel(stm);
        this.setColumnModel(dtcm);
        this.setOpaque(false);
        this.setShowGrid(false);
        this.getTableHeader().setReorderingAllowed(false);
        this.getTableHeader().setOpaque(true);
        this.setColumnSelectionAllowed(false);
        this.setCellSelectionEnabled(false);
        this.setRowHeight(this.getRowHeight()*2);
        stcr.setBackground(new Color(0, true));
        stcr.setForeground(new Color(206, 68, 68));
        this.setBorder(nullBorder);

    }

    public void addScore(Score sc)
    {
        stm.addScore(sc);
    }

    public void initTableColumns(int[] columnwidth)
    {
        int cnt = 0;
        for (String s:sname)
        {
            TableColumn tc = new TableColumn(cnt, columnwidth[cnt++]);
            tc.setHeaderRenderer(stcr);
            tc.setHeaderValue(s);
            tc.setResizable(false);
            tc.setCellRenderer(sc);
            dtcm.addColumn(tc);
        }
        this.setColumnModel(dtcm);
    }

    public ScoreTableCellRenderer getStcr() {
        return stcr;
    }

    public ScoreTableModel getStm() {
        return stm;
    }

    public void setStm(ScoreTableModel stm) {
        this.stm = stm;
    }

    public Border getNullBorder() {
        return nullBorder;
    }

    public void setNullBorder(Border nullBorder) {
        this.nullBorder = nullBorder;
    }

    public DefaultTableColumnModel getDtcm() {
        return dtcm;
    }
}
