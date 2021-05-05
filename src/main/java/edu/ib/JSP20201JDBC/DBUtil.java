package edu.ib.JSP20201JDBC;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class DBUtil {


//     abstract List<Urlopy> getUrlopy() throws Exception;

    /**
     * Odpowiada za komunikację z bazą danych
     */
    protected static void close(Connection conn, Statement statement, ResultSet resultSet) {

        try {

            if (resultSet != null)
                resultSet.close();

            if (statement != null)
                statement.close();

            if (conn != null)
                conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}


