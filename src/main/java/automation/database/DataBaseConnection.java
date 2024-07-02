package automation.database;

import automation.enumaration.DatabaseNames;
import automation.listeners.ExtentListeners;
import automation.properties.PropertyManager;
import com.aventstack.extentreports.Status;

import java.sql.*;

import static org.testng.AssertJUnit.fail;

/**
 * <b>DATABASE</b> [Database]: Establish Database Connection
 */

public class DataBaseConnection {

    private static String dbHostUrl;
    private static String dbUser;
    private static String dbPass;
    private static DatabaseNames database;

    /**
     * <b>[Method]</b> - Get DB Query Response<br>
     * <br>
     * <i>Method functionality:</i><br>
     * Establishing DB connection, execute query and returns response<br>
     *
     * @param dbName Database Name
     * @param query  Database Query
     * @return returning query response
     * @throws ClassNotFoundException exception
     * @throws SQLException           exception
     */
    public static ResultSet getQueryResponse(DatabaseNames dbName, String query) throws Exception {
        ResultSet rs = null;
        // define connection value
        setDatabaseHostname(dbName);
        try {
            // establish connection
            Connection con = setConnection(dbName);

            // create statement for connection
            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            // execute query
            rs = st.executeQuery(query);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("Database driver is not found");
            ExtentListeners.test.log(Status.FAIL, "Database driver is not found");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("Could not establish connection to the database " + dbName);
            ExtentListeners.test.log(Status.FAIL, "Connection to the database " + dbName + " cannot be established");
        }
        // return statement
        return rs;
    }

    /**
     * <b>[Method]</b> - Get DB Query Response<br>
     * <br>
     * <i>Method functionality:</i><br>
     * Establishing DB connection, execute query and returns response<br>
     *
     * @param dbName Database Name
     * @param query  Database Query
     * @return returning query response
     * @throws ClassNotFoundException exception
     * @throws SQLException           exception
     */

    public static int updateQuery(DatabaseNames dbName, String query) throws ClassNotFoundException, SQLException {
        int updateCount = 0;
        // define connection value
        setDatabaseHostname(dbName);
        try {
            System.out.println("Step updating test_value");
            // establish connection
            try (Connection con = setConnection(dbName)) {
                con.setAutoCommit(false);
                // create statement for connection
                Statement statement = con.createStatement();

                // update database
                updateCount = statement.executeUpdate(query);
                if (updateCount == 1) {
                    System.out.println("Updated test_value successfully : " + updateCount);
                    con.commit();
                } else if (updateCount == 0) {
                    con.commit();
                } else {
                    con.rollback();
                }
            }
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("Database driver is not found");
            ExtentListeners.test.log(Status.FAIL, "Database driver is not found");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("Could not establish connection to the database " + dbName);
            ExtentListeners.test.log(Status.FAIL, "Connection to the database " + dbName + " cannot be established");
        }

        return updateCount;
    }


    /**
     * <b>[Method]</b> - Setting Database Data<br>
     * <br>
     * <i>Method functionality:</i><br>
     * Setting values for database connection<br>
     * Host URL (host:port:sid), Username, Password<br>
     *
     * @param dbName Database Name
     */
    public static void setDatabaseHostname(DatabaseNames dbName) {
        String host = null;
        String user = null;
        String pass = null;

        switch (dbName) {
            case AC:
                //host = PropertyManager.dbHostnameAc + ":" + PropertyManager.dbPortAc + "/" + PropertyManager.dbNameAc;
                host = PropertyManager.dbHostnameAc + ":" + PropertyManager.dbPortAc + ";databaseName="  + PropertyManager.dbNameAc  +
                        ";encrypt=true;trustServerCertificate=true";
                user = PropertyManager.dbUsernameAc;
                pass = PropertyManager.dbPasswordAc;
                break;
            case CAMEL:
                host = PropertyManager.dbHostnameCamelNis + ":" + PropertyManager.dbPortCamelNis + ";databaseName="  + PropertyManager.dbNameCamelNis  +
                        ";encrypt=true;trustServerCertificate=true";
                user = PropertyManager.dbUsernameCamelNis;
                pass = PropertyManager.dbPasswordCamelNis;
                break;
            case DGH:
                host = PropertyManager.dbHostnameDgh + ":" + PropertyManager.dbPortDgh + "/" + PropertyManager.dbNameDgh;
                user = PropertyManager.dbUsernameDgh;
                pass = PropertyManager.dbPasswordDgh;
                break;
            case NEW:
                host = PropertyManager.dbHostnameNew + ":" + PropertyManager.dbPortNew + "/" + PropertyManager.dbNameNew;
                user = PropertyManager.dbUsernameNew;
                pass = PropertyManager.dbPasswordNew;
                break;
            case PLM:
                host = PropertyManager.dbHostnamePlm + ":" + PropertyManager.dbPortPlm + "/" + PropertyManager.dbNamePlm;
                user = PropertyManager.dbUsernamePlm;
                pass = PropertyManager.dbPasswordPlm;
                break;
            case PORTFOLIO:
                host = PropertyManager.dbHostnamePortfolio + ":" + PropertyManager.dbPortPortfolio + "/" +  PropertyManager.dbNamePortfolio;
                user = PropertyManager.dbUsernamePortfolio;
                pass = PropertyManager.dbPasswordPortfolio;
                break;
            case PRODUCT:
                host = PropertyManager.dbHostnameProduct + ":" + PropertyManager.dbPortProduct + "/portfolio";
                user = PropertyManager.dbUsernameProduct;
                pass = PropertyManager.dbPasswordProduct;
                break;
            default:
                host = PropertyManager.dbHostname + ":" + PropertyManager.dbPort;
                user = PropertyManager.dbUsername;
                pass = PropertyManager.dbPassword;
                break;
        }

        setDbHostUrl(host);
        setDbUsername(user);
        setDbPassword(pass);
    }

    /**
     * <b>[Method]</b> - Setting Connection<br>
     * <br>
     * <i>Method functionality:</i><br>
     * Creating Connection<br>
     * based on database type, initialize class and create connection<br>
     *
     * @param dbName Database Name
     * @return con Connection
     * @throws ClassNotFoundException
     * @throws SQLException
     */

    public static Connection setConnection(DatabaseNames dbName) throws ClassNotFoundException, SQLException {
        Connection con = null;
        try {
            switch (dbName.option.toUpperCase()) {
                case "FIREBIRD": {
                    Class.forName("org.firebirdsql.jdbc.FBDriver");
                    con = DriverManager. getConnection("jdbc:firebirdsql://" + getDbHostUrl(), getDbUsername(), getDbPassword());
                    break;
                }
                case "MYSQL": {
                    //Class.forName("com.mysql.jdbc.Driver");
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con = DriverManager. getConnection("jdbc:mysql://" + getDbHostUrl(), getDbUsername(), getDbPassword());
                    break;
                }
                case "ORACLE": {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    con = DriverManager.getConnection("jdbc:oracle:thin:@" + getDbHostUrl(), getDbUsername(), getDbPassword());
                    break;
                }
                case "MICROSOFTSQL": {
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    con = DriverManager.getConnection("jdbc:sqlserver://" + getDbHostUrl(), getDbUsername(), getDbPassword());
                    break;
                }
                default:
                    con = null;
            }
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("Database driver is not found");
            ExtentListeners.test.log(Status.FAIL, "Database driver is not found");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("Could not establish connection to the database " + dbName);
            ExtentListeners.test.log(Status.FAIL, "Connection to the database " + dbName + " cannot be established");
        }
        return con;
    }

    /**
     * <b>[Method]</b> - Getting Database Host URL<br>
     *
     * @return Database Host URL
     */
    private static String getDbHostUrl() {
        return dbHostUrl;
    }

    /**
     * <b>[Method]</b> - Setting Database Host URL<br>
     *
     * @param host Database Host URL
     */

    private static void setDbHostUrl(String host) {
        dbHostUrl = host;
    }

    /**
     * <b>[Method]</b> - Getting Database Host URL<br>
     *
     * @return Database Username
     */
    private static String getDbUsername() {
        return dbUser;
    }

    /**
     * <b>[Method]</b> - Setting Database Username<br>
     *
     * @param user Database Username
     */

    private static void setDbUsername(String user) {
        dbUser = user;
    }

    /**
     * <b>[Method]</b> - Getting Database Host URL<br>
     *
     * @return Database Password
     */
    private static String getDbPassword() {
        return dbPass;
    }

    /**
     * <b>[Method]</b> - Setting Database Password<br>
     *
     * @param pass Database Password
     */

    private static void setDbPassword(String pass) {
        dbPass = pass;
    }

    /**
     * <b>[Method]</b> - Getting Database Name<br>
     *
     * @return Database Password
     */

    public static DatabaseNames getDatabaseName() {
        return database;
    }

    /**
     * <b>[Method]</b> - Setting Database Name<br>
     *
     * @param db DatabaseNames
     */

    public static void setDatabaseName(DatabaseNames db) {
        database = db;
    }

}
