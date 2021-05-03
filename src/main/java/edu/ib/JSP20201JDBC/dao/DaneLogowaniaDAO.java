package edu.ib.JSP20201JDBC.dao;

import edu.ib.JSP20201JDBC.Login;
import edu.ib.JSP20201JDBC.dbutils.DBUtil;
import edu.ib.JSP20201JDBC.model.DaneLogowania;

import java.sql.*;


public class DaneLogowaniaDAO extends DBUtil {

    private Connection connection;
    private PreparedStatement statement;
    private String db_url, db_name, db_password;
    private String name, password;
    private ResultSet resultSet;
    private DaneLogowania daneLogowania;

    public DaneLogowaniaDAO() {
    }

    public DaneLogowaniaDAO(String URL, String name, String password) {
        this.db_url = URL;
        this.name = name;
        this.password = password;
        this.db_name = "root";
        this.db_password = "root";
    }

    public DaneLogowania getDaneLogowania(String login, String password) throws SQLException {

        if (validate_db(db_name, db_password)){
        selectUser(connection, login, password);
         return  selectUser(connection, login, password);
        }
        else
        return new DaneLogowania(null, null, null);
    }



    private DaneLogowania selectUser(Connection connection, String login, String password) throws SQLException {
        statement = null;

        try {
            // polaczenie z BD
            // wykonane wczesniej i przekazane jako parametr

            // zapytanie
            String sql = "SELECT * FROM dane_logowania WHERE login=?";

            statement = connection.prepareStatement(sql);
            statement.setString(1, login);

            // wykonanie wyrazenia SQL
            resultSet = statement.executeQuery(sql);

            // przetworzenie wyniku zapytania
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String pass = resultSet.getString("pass");
                daneLogowania = new DaneLogowania(id, login, pass);
            }

        } finally {

            // zamkniecie obiektow JDBC
            close(connection, statement, null);

        }
        return daneLogowania;
    }

    public boolean validateLogin(String login) throws SQLException {
        boolean b = false;
        statement = null;

        try {
            // polaczenie z BD
            // wykonane wczesniej i przekazane jako parametr

            // zapytanie
            String sql = "SELECT * FROM dane_logowania WHERE login=?";

            statement = connection.prepareStatement(sql);
            statement.setString(1, login);

            // wykonanie wyrazenia SQL
            resultSet = statement.executeQuery(sql);

            // przetworzenie wyniku zapytania
            while (resultSet.next()) {
                b = true;
            }

        } finally {

            // zamkniecie obiektow JDBC
            close(connection, statement, null);

        }
        return  b;
    }

    public boolean validatePassword(String login, String password) throws SQLException {
        boolean b = false;
        statement = null;

        try {
            // polaczenie z BD
            // wykonane wczesniej i przekazane jako parametr

            // zapytanie
            String sql = "SELECT * FROM dane_logowania WHERE login=? and password=?";

            statement = connection.prepareStatement(sql);
            statement.setString(1, login);

            // wykonanie wyrazenia SQL
            resultSet = statement.executeQuery(sql);

            // przetworzenie wyniku zapytania
            while (resultSet.next()) {
                b = true;
            }

        } finally {

            // zamkniecie obiektow JDBC
            close(connection, statement, null);

        }
        return  b;
    }


        private boolean validate_db(String db_name, String db_pass) {
            boolean status = false;

            try {

                Class.forName("com.mysql.cj.jdbc.Driver");

            } catch (ClassNotFoundException e) {
                e.printStackTrace();

            }

            Connection conn = null;

            try {

                conn = DriverManager.getConnection(db_url, db_name, db_pass);
                status = true;
                connection = conn;


            } catch (Exception e) {
                e.printStackTrace();
            }

            return status;
        }

}