package edu.ib.JSP20201JDBC;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Gabriela Wrona i Mateusz Nawrocki
 */
public class DBUtilAdmin extends DBUtil {

    private DataSource dataSource;

    /**
     * Konstruktor, wykorzysywany w laczeniu sie z baza
     */
    public DBUtilAdmin(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Metoda zwracajaca obiekt DaneLogowania na bazie loginu, wykorzysywana przy sprawdzaniu poprawnosci danych.
     * @return obiekt z danymi do logowania
     */
    public DaneLogowania getAdminByLogin(String login){
        DaneLogowania daneLogowania = null;
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try{
            conn = dataSource.getConnection();
            String sql = "SELECT * FROM dane_logowania_admin WHERE email like ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1,login);
            resultSet = statement.executeQuery();

            if (resultSet.next()){
                String email = resultSet.getString("email");
                String haslo = resultSet.getString("haslo");
                int id_amin = resultSet.getInt("id_admina");

                daneLogowania = new DaneLogowania(String.valueOf(id_amin),email,haslo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(conn,statement,resultSet);
        }
        return daneLogowania;
    }


}
