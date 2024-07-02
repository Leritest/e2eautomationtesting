package automation.de.dg.database.manipulation.queryManipulation;

import org.firebirdsql.jdbc.FBSQLException;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import static org.testng.AssertJUnit.fail;

/**
 * <b>DATABASE</b> [Queries]: Database Query Manipulation
 * <i>Class functionality:</i><br>
 *  Class is used to manipulate with Database response<br>
 *  and as common class independent of database
 */

public class QueryManipulation {

    /**
     * <b>[Method]</b> - Count Query Rows <br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality count number of Query rows<br>
     * @param rs ResultSet
     * @throws SQLException exception
     */

    public static int countQueryRows(ResultSet rs) throws SQLException {
        int count = 0;
        int max = 1000;
        try {
            ResultSet rsHelp = rs;
            rsHelp.beforeFirst();
            while (rsHelp.next()) {
                if (count < max) {
                    count++;
                } else {
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * <b>[Method]</b> - Return Value<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality returns String value<br>
     * from desired column and row<br>
     *
     * @param rs ResultSet
     * @param column String
     * @param row int
     * @throws SQLException exception
     */

    public static String returnString(ResultSet rs, String column, int row) throws SQLException, NullPointerException {
        ResultSet rsHelp = rs;
        /*if (!rsHelp.next()) {
            rsHelp.beforeFirst();
            if (row == 0 || row == 1) {
                rsHelp.next();
            } else {
                rsHelp.absolute(row + 1);
            }
        } else {
            rsHelp.beforeFirst();
        }*/
        // navigate to desired row
        rsHelp.beforeFirst();
        if (row == 0 || row == 1) {
            rsHelp.next();
        } else {
            rsHelp.absolute(row + 1);
        }
        // take value from column
        String columnValue = null;

        //checkColumnType(rs);
        try {
            columnValue = rsHelp.getString(column);
            if (columnValue != null) {
                columnValue = columnValue.trim();
            }
        } catch (FBSQLException fe) {
            fe.printStackTrace();
            fail(fe.getLocalizedMessage());
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Empty SQL response");
        } catch (NullPointerException ne) {
            ne.printStackTrace();
            fail("Invalid request string");
        }
        return columnValue;
    }

    /**
     * <b>[Method]</b> - Return Value<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality returns String value<br>
     * from desired column and row<br>
     *
     * @param rs ResultSet
     * @param column String
     * @param row int
     * @throws SQLException exception
     */

    /**
     * <b>[Method]</b> - Return Int Value<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality returns Int value<br>
     * from desired column and row<br>
     *
     * @param rs ResultSet
     * @param column String
     * @param row int
     * @throws SQLException exception
     */

    public static int returnInt(ResultSet rs, String column, int row) throws SQLException, NullPointerException {
        ResultSet rsHelp = rs;

        // navigate to desired row
        rsHelp.beforeFirst();
        if (row == 0 || row == 1) {
            rsHelp.next();
        } else {
            rsHelp.absolute(row + 1);
        }
        // take value from column
        int columnValue = 0;

        //checkColumnType(rs);
        try {
            columnValue = rsHelp.getInt(column);
        } catch (FBSQLException fe) {
            fe.printStackTrace();
            fail(fe.getLocalizedMessage());
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Empty SQL response");
        } catch (NullPointerException ne) {
            ne.printStackTrace();
            fail("Invalid request string");
        }
        return columnValue;
    }

    public static String returnStringForUpdate(ResultSet rs, String column, int row) throws SQLException, NullPointerException {
        ResultSet rsHelp = rs;
        rsHelp.beforeFirst();
        if (row == 0) {
            rsHelp.next();
        } else {
            rsHelp.absolute(row + 1);
        }
        // take value from column
        String columnValue = null;

        //checkColumnType(rs);
        try {
            columnValue = rsHelp.getString(column);
            if (columnValue != null) {
                columnValue = columnValue.trim();
            }
        } catch (FBSQLException fe) {
            fe.printStackTrace();
            fail(fe.getLocalizedMessage());
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Empty SQL response");
        } catch (NullPointerException ne) {
            ne.printStackTrace();
            fail("Invalid request string");
        }
        return columnValue;
    }

    private static String checkColumnType(ResultSet rs) {
        ResultSet rsHelp = rs;
        String column_name;
        try {
            //Retrieving the ResultSetMetaData object
            ResultSetMetaData rsmd = rsHelp.getMetaData();

            //getting the column type
            column_name = rsmd.getColumnTypeName(1);

            column_name = rsmd.getColumnTypeName(2);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return column_name;
    }

}
