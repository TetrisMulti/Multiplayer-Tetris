package Model;

import Beans.Score;
import DB.DB_Access;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by ganleb13 on 16.03.2017.
 * In this class we define the Model-Class for our Highscorelist
 */
public class HighscoreListModel extends AbstractListModel{

    private LinkedList<Score> liScoreList = new LinkedList<>();
    private DB_Access dba = DB_Access.getTheinstance();

    public HighscoreListModel() throws SQLException, IOException, ClassNotFoundException, Exception {
        liScoreList.addAll(dba.getAllScores());
    }


    public void addScore(Score sc)
    {
        liScoreList.add(sc);
    }




    @Override
    public int getSize() {
        return liScoreList.size();
    }

    @Override
    public Object getElementAt(int index) {
        return liScoreList.get(index);
    }
}
