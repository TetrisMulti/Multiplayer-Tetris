package DB;

import java.sql.Statement;
import java.sql.Connection;
import java.util.LinkedList;

/**
 * Created by ganleb13 on 15.03.2017.
 * In this class we release and offer the Statements
 */
public class DB_CachedConnection{

    private final LinkedList<Statement> queue = new LinkedList<>();
    private Connection con;

    public DB_CachedConnection(Connection con) {
        this.con = con;
    }

    public Statement getStatement() throws Exception
    {
        if (con == null)
        {
            throw new Exception("no database connected");
        }
        if (!queue.isEmpty())
        {
            return queue.poll();
        }
        return con.createStatement();
    }

    public void releaseStatement(Statement stat)
    {
        queue.offer(stat);
    }
}
