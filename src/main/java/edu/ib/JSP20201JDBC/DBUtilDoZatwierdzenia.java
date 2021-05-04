package edu.ib.JSP20201JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBUtilDoZatwierdzenia extends DBUtil {


    private String URL;
    private String name;
    private String password;

    public DBUtilDoZatwierdzenia(String URL) {
        this.URL = URL;
    }

    //@Override
    public List<UrlopyDoZatwierdzenia> getDoZatwierdzenia() throws Exception {
        List<UrlopyDoZatwierdzenia> urlopyDoZatwierdzenias = new ArrayList<>();

        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            // polaczenie z BD
            conn = DriverManager.getConnection(URL, name, password);

            // zapytanie SELECT
            String sql = "SELECT * FROM zapytania_o_urlopy";
            statement = conn.createStatement();

            // wykonanie zapytania SQL
            resultSet = statement.executeQuery(sql);

            // przetworzenie wyniku zapytania
            while (resultSet.next()) {

                // pobranie danych z rzedu
                int id_pracownika = resultSet.getInt("id_uzytkowika");
                String poczatek = resultSet.getString("poczatek");
                String koniec = resultSet.getString("koniec");


                // dodanie do listy nowego obiektu
                urlopyDoZatwierdzenias.add(new UrlopyDoZatwierdzenia(id_pracownika, poczatek, koniec));

            }

        } finally {

            // zamkniecie obiektow JDBC
            close(conn, statement, resultSet);
        }

        return urlopyDoZatwierdzenias;
    }
    public boolean validate(String name, String pass) {
        boolean status = false;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }

        Connection conn = null;

        try {

            conn = DriverManager.getConnection(URL, name, pass);


            status = true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
