package BL;

import Beans.Score;
import XML.XMLWriter;
import org.xml.sax.SAXException;

import javax.swing.table.AbstractTableModel;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * Created by ganleb13 on 17.03.2017.
 * In this class we will define the table model for our ScoreTable
 */
public class ScoreTableModel extends AbstractTableModel {

    private LinkedList<Score> scList = new LinkedList<>();

    /**
     * Method to add a Score into the list
     * Call saveScores --> Save all Scores in a XML File
     * @param sc --> Score to add into the list
     */
    public void addScore(Score sc)
    {
        scList.add(sc);
        sortAndRankList();
        try {
            saveScores();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to load Scores from the XML File into the list
     * Call XMLLoad to load all Scores
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    public void loadScores() throws IOException, SAXException, ParserConfigurationException {
        scList = (LinkedList<Score>) XMLWriter.XMLLoad().clone();
        sortAndRankList();

    }

    /**
     * Call XMLSave to sav all Scores into a XML File
     * @throws TransformerException
     * @throws ParserConfigurationException
     */
    public void saveScores() throws TransformerException, ParserConfigurationException {
        XMLWriter.XMLSave((LinkedList<Score>) scList.clone());
    }

    /**
     * Method to sort the Scores comparing the Points
     * The Method also ranks the Scores
     */
    public void sortAndRankList()
    {
        scList.sort(Comparator.comparing(Score::getScore).reversed());
        int counter = 1;
        for (Score sc:scList) {
            sc.setRank(counter++);
        }
    }



    @Override
    public int getRowCount() {

        return scList.size();
    }

    @Override
    public int getColumnCount() {

        return 2;
    }

    /**
     * Method to tell the table what to show in the specific column
     * @param rowIndex
     * @param columnIndex
     * @return
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex)
        {
            case 0: return scList.get(rowIndex).getRank();
            case 1: return scList.get(rowIndex).getUser();
            case 2: return scList.get(rowIndex).getScore();
            default: return null;
        }
    }

    public LinkedList<Score> getScList()
    {
        return scList;
    }

}
