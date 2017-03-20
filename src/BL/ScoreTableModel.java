package BL;

import Beans.Score;
import XML.XMLWriter;
import org.xml.sax.SAXException;
import sun.awt.image.ImageWatched;

import javax.swing.table.AbstractTableModel;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by ganleb13 on 17.03.2017.
 * In this class we will define the table model for our HighscoreTable
 */
public class ScoreTableModel extends AbstractTableModel {

    private LinkedList<Score> scList = new LinkedList<>();

    public void addScore(Score sc)
    {
        scList.add(sc);
    }

    public void loadScores() throws IOException, SAXException, ParserConfigurationException {
        scList = (LinkedList<Score>) XMLWriter.XMLLoad().clone();
    }
    public void saveScores() throws TransformerException, ParserConfigurationException {
        XMLWriter.XMLSave((LinkedList<Score>) scList.clone());
    }



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

    public void outAllScores()
    {
        for (Score sc:
             scList) {
            System.out.println(sc.toString());
        }
    }

    public LinkedList<Score> getScList()
    {
        return scList;
    }

    public static void main(String[] args) {
        ScoreTableModel stm = new ScoreTableModel();
        try {
            stm.loadScores();
            stm.outAllScores();
            stm.saveScores();
            stm.loadScores();
            stm.outAllScores();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }
}
