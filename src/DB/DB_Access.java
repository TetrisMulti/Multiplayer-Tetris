package DB;

import java.io.IOException;
import java.sql.SQLException;

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
