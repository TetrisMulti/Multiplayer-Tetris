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
 * In this class we will define the table model for our HighscoreTable
 */
public class ScoreTableModel extends AbstractTableModel {

    private LinkedList<Score> scList = new LinkedList<>();
    private String[] sname = {"Rank", "User", "Score"};

    public void addScore(Score sc)
    {
        scList.add(sc);
        sortAndRankList();
    }

    public void loadScores() throws IOException, SAXException, ParserConfigurationException {
        scList = (LinkedList<Score>) XMLWriter.XMLLoad().clone();
        sortAndRankList();

    }
    public void saveScores() throws TransformerException, ParserConfigurationException {
        XMLWriter.XMLSave((LinkedList<Score>) scList.clone());
    }

    public void sortAndRankList()
    {
        scList.sort(Comparator.comparing(Score::getScore));
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
