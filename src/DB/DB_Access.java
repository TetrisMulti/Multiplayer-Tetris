package DB;

import Beans.Score;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

/**
 * Created by ganleb13 on 16.03.2017.
 * In this class we will execute our Statements, that we get from our enum
 */
public class DB_Access {

    private static DB_Access theinstance;
    private DB_Database db;



    public DB_Access() throws SQLException, IOException, ClassNotFoundException {
        db = new DB_Database();
    }

    public LinkedList<Score> getAllScores() throws Exception {
        LinkedList<Score> liScores = new LinkedList<>();
        String statement = DBEnum_Statements.ALLSCORES.getStatement();
        Statement stat = db.getStatement();
        ResultSet rs = stat.executeQuery(statement);

        while(rs.next())
        {
            String user = rs.getString("username");
            int score = rs.getInt("score");
            liScores.add(new Score(user, score));
        }

        return liScores;
    }


    public static DB_Access getTheinstance() throws SQLException, IOException, ClassNotFoundException {
        if (theinstance == null)
        {
            theinstance = new DB_Access();
        }
        return theinstance;
    }

    public void disconnect() throws SQLException {
        db.disconnect();
    }
}
