package edu.ib.JSP20201JDBC;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

public class DBUtilUser extends DBUtil {

    private String URL;
    private String name;
    private String password;
    private DataSource dataSource;
    public DBUtilUser(DataSource dataSource) {
        this.dataSource = dataSource;
    }



    public void addDaneLogowania(DaneLogowania daneLogowania) throws Exception {

        Connection conn = null;
        PreparedStatement statement = null;

        try {
            // polaczenie z BD
            conn = dataSource.getConnection();

            // zapytanie INSERT i ustawienie jego parametrow
            String sql = "INSERT INTO dane_logowania(id_uzytkownika, email, haslo) " +
                    "VALUES(?,?,?)";

            statement = conn.prepareStatement(sql);
            statement.setString(1, String.valueOf(daneLogowania.getId_uzytkownika()));
            statement.setString(2, String.valueOf(daneLogowania.getEmail()));
            statement.setString(3, String.valueOf(daneLogowania.getHaslo()));

            // wykonanie zapytania
            statement.execute();


        } finally {

            close(conn, statement, null);

        }
    }
}
