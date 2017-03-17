package BL;

import Beans.Score;

import javax.swing.table.AbstractTableModel;
import java.util.LinkedList;

/**
 * Created by ganleb13 on 17.03.2017.
 * In this class we will define the table model for our HighscoreTable
 */
public class ScoreTableModel extends AbstractTableModel {

    private LinkedList<Score> scList = new LinkedList<>();




    @Override
    public int getRowCount() {
        return scList.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex)
        {
            case 0: return scList.get(rowIndex).getUser();
            case 1: return scList.get(rowIndex).getUser();
            default: return null;
        }
    }
}
