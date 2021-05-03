package edu.ib.JSP20201JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

public class DBUtilAddUser extends DBUtil {

    private String URL;
    private String name;
    private String password;

    public DBUtilAddUser(String URL) {
        this.URL = URL;
    }

    public void addDaneLogowania(DaneLogowania daneLogowania) throws Exception {

        Connection conn = null;
        PreparedStatement statement = null;

        try {
            // polaczenie z BD
            conn = DriverManager.getConnection(URL, name, password);

            // zapytanie INSERT i ustawienie jego parametrow
            String sql = "INSERT INTO dane_logowani(id_uzytkownika, email, haslo) " +
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
