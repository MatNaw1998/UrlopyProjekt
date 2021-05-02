package edu.ib.JSP20201JDBC;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DBUtilAdmin extends DBUtil {

    private String URL;
    private String name;
    private String password;

    public DBUtilAdmin(String URL) {
        this.URL = URL;
    }

    @Override
    public List<Urlopy> getUrlopy() throws Exception {
        List<Urlopy> urlopies = new ArrayList<>();

        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            // polaczenie z BD
            conn = DriverManager.getConnection(URL, name, password);

            // zapytanie SELECT
            String sql = "SELECT * FROM daneUrlopu";
            statement = conn.createStatement();

            // wykonanie zapytania SQL
            resultSet = statement.executeQuery(sql);

            // przetworzenie wyniku zapytania
            while (resultSet.next()) {

                // pobranie danych z rzedu
                int id = resultSet.getInt("id");
                String imieNazwisko = resultSet.getString("imieNazwisko");
                String od = resultSet.getString("od");
                String doU = resultSet.getString("doU");
                Long iloscDni = Long.parseLong(resultSet.getString("iloscDni"));
                String statusU = resultSet.getString("statusU");


                // dodanie do listy nowego obiektu
                urlopies.add(new Urlopy(id,imieNazwisko,od,doU,iloscDni,statusU));

            }

        } finally {

            // zamkniecie obiektow JDBC
            close(conn, statement, resultSet);
        }


        return urlopies;
    }

    public void addUrlop(Urlopy urlopy) throws Exception {

        Connection conn = null;
        PreparedStatement statement = null;

        try {
            // polaczenie z BD
            conn = DriverManager.getConnection(URL, name, password);

            // zapytanie INSERT i ustawienie jego parametrow
            String sql = "INSERT INTO daneUrlopu(imieNazwisko,od,doU,iloscDni,statusU) " +
                    "VALUES(?,?,?,?,'Do akceptacji')";

            statement = conn.prepareStatement(sql);
            statement.setString(1, String.valueOf(urlopy.getImieNazwisko()));
            statement.setString(2, String.valueOf(urlopy.getOd()));
            statement.setString(3, String.valueOf(urlopy.getDoU()));
            statement.setLong(4, urlopy.getIloscDni());


            // wykonanie zapytania
            statement.execute();


        } finally {

            close(conn, statement, null);

        }

    }


    public void updateZatwierdz(int id) throws Exception {

        Connection conn = null;
        PreparedStatement statement = null;

        try {

            // polaczenie z BD
            conn = DriverManager.getConnection(URL, name, password);

            // zapytanie UPDATE
            String sql = "UPDATE daneUrlopu SET statusU='Zatwierdzone'" +
                    "WHERE id =?";
            statement = conn.prepareStatement(sql);

            statement.setInt(1, id);
            // wykonanie zapytania
            statement.execute();

        } finally {
            // zamkniecie obiektow JDBC
            close(conn, statement, null);
        }

    }

    public void updateOdrzuc( int id ) throws Exception {

        Connection conn = null;
        PreparedStatement statement = null;

        try {

            // polaczenie z BD
            conn = DriverManager.getConnection(URL, name, password);

            // zapytanie UPDATE
            String sql = "UPDATE daneUrlopu SET statusU='Odrzucone'" +
                    "WHERE id =?";
            statement = conn.prepareStatement(sql);

            statement.setInt(1, id);
            // wykonanie zapytania
            statement.execute();

        } finally {
            // zamkniecie obiektow JDBC
            close(conn, statement, null);
        }

    }




    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
