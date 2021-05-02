package edu.ib.JSP20201JDBC.db;

import edu.ib.JSP20201JDBC.model.DaneLogowania;

import javax.sql.rowset.CachedRowSet;
import java.sql.*;
import java.util.List;

/** Laczenie z baza danych, wykonywanie zapytan**/

public class DBUtil {

    private String userName;
    private String userPassword;
    //private TextArea consoleTextArea;

    private Connection conn = null;

    /** przygotowanie nowego polaczenia
     *
     * @param userName  - nazwa uzytkownika bazy danych
     * @param userPassword - haslo uzytkownika bazy danych
     */
    public DBUtil(String userName, String userPassword){ /*, TextArea consoleTextArea) { */
        this.userName = userName;
        this.userPassword = userPassword;
       // this.consoleTextArea = consoleTextArea;
    }
/** polaczenie z baza danych **/
    public void dbConnect() throws SQLException, ClassNotFoundException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
           // consoleTextArea.appendText("No MySQL JDBC Driver found." + "\n");
            e.printStackTrace();
            throw e;
        }

        try {
            conn = DriverManager.getConnection(createURL());//("jdbc:mysql://localhost/?user=root&password=root");//
        } catch (SQLException e) {
           // consoleTextArea.appendText("Connection Failed! Try again." + "\n");
            e.printStackTrace();
            throw e;
        }

    }
/** rozlaczenie z baza danych **/
    public void dbDisconnect() throws SQLException {

        try {

            if (conn != null && !conn.isClosed()) {

                conn.close();
              //  consoleTextArea.appendText("Connection closed. Bye!" + "\n");

            }
        } catch (Exception e) {
            throw e;
        }
    }
/** przygotowanie url do polaczenia **/
    private String createURL() {

        StringBuilder urlSB = new StringBuilder("jdbc:mysql://");
        urlSB.append("localhost:3306/");
        urlSB.append("dbprojetk?");
        urlSB.append("useUnicode=true&characterEncoding=utf-8");
        urlSB.append("&user=");
        urlSB.append(userName);
        urlSB.append("&password=");
        urlSB.append(userPassword);
        urlSB.append("&serverTimezone=CET"); //+ TimeZone.getDefault().getID());

        System.out.println(urlSB);
        return urlSB.toString();
    }
/** wykonanie zapytania **/
    public ResultSet dbExecuteQuery(String queryStmt) throws SQLException, ClassNotFoundException {

        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        CachedRowSet crs;

        try {

            dbConnect();

            stmt = conn.prepareStatement(queryStmt);

            resultSet = stmt.executeQuery(queryStmt);

            crs = new CachedRowSetWrapper();

            crs.populate(resultSet);
        } catch (SQLException e) {
         //   consoleTextArea.appendText("Problem occurred at executeQuery operation. \n");
            throw e;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            dbDisconnect();
        }

        return crs;
    }
/** wykonanie update na bazie danych **/
    public  void dbExecuteUpdate(String sqlStmt) throws SQLException, ClassNotFoundException {

        Statement stmt = null;
        try {
            dbConnect();
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlStmt);

        } catch (SQLException e) {
          //  consoleTextArea.appendText("Problem occurred at executeUpdate operation. \n");
            throw e;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            dbDisconnect();
        }
    }

    public List<DaneLogowania> getPhones() {
    }

    public void setName(String name) {
    }

    public void setPassword(String password) {
    }

    public void deleteDaneLogowania(String id) {
    }

    public void updateDaneLogowania(DaneLogowania daneLogowania) {
    }

    public DaneLogowania getDaneLogowania(String id) {
    }

    public void addDaneLogowania(DaneLogowania daneLogowania) {
    }
}
