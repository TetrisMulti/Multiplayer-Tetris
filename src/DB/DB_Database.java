package DB;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created by ganleb13 on 15.03.2017.
 * In this class we will connect with the Database
 */
public class DB_Database {
    private String hostname;
    private String driver;
    private String username;
    private String password;
    private String dbname;
    private Connection con;
    private DB_CachedConnection cc;


    public DB_Database() throws ClassNotFoundException, IOException, SQLException {
        Properties props = PropertyLoader.loadProperties();
        hostname = props.getProperty("hostname");
        driver = props.getProperty("driver");
        username = props.getProperty("username");
        password = props.getProperty("password");
        dbname = props.getProperty("dbname");
        System.out.println(driver);
        Class.forName(driver);
        connect();
    }

    private void connect() throws SQLException{
        System.out.println("Trying to connect to the database");
        con = DriverManager.getConnection(String.format("jdbc:postgresql://%s/%s", hostname, dbname), username, password);
        cc = new DB_CachedConnection(con);
        System.out.println("Connection to the database established");
    }

    public void disconnect() throws SQLException {
        if (con != null)
        {
            con.close();
            System.out.println("disconnected");
            con = null;
        }
    }

    public Statement getStatement() throws Exception {
        if (cc == null)
        {
            throw new Exception("Not connected to database");
        }
        return cc.getStatement();

    }

    public void releaseStatement(Statement stat)
    {
        cc.releaseStatement(stat);

    }
    /**
    public static void main(String[] args) {
        try {
            DB_Database db = new DB_Database();
            db.connect();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }
     */
}
