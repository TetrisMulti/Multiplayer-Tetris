package DB;

/**
 * Created by ganleb13 on 16.03.2017.
 * In this enum we will define our SQL statemenst we need for our TetrisDB
 */
public enum DBEnum_Statements {

    ALLSCORES("SELECT *\nFROM score;");



    private String statement;

    DBEnum_Statements(String statement) {
        this.statement = statement;
    }

    public String getStatement() {
        return statement;
    }
}
