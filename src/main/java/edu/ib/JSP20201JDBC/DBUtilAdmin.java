package edu.ib.JSP20201JDBC;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DBUtilAdmin extends DBUtil {

    private DataSource dataSource;

    public DBUtilAdmin(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Urlopy> getUrlopy() throws Exception {
        List<Urlopy> urlopies = new ArrayList<>();

        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            // polaczenie z BD
            conn = dataSource.getConnection();

            // zapytanie SELECT
            String sql = "SELECT * FROM daneUrlopu";
            statement = conn.createStatement();

            // wykonanie zapytania SQL
            resultSet = statement.executeQuery(sql);

            // przetworzenie wyniku zapytania
            while (resultSet.next()) {

                // pobranie danych z rzedu
                int id = resultSet.getInt("id");
                String imieNazwisko = resultSet.getString("email");
                String od = resultSet.getString("od");
                String doU = resultSet.getString("doU");
                Long iloscDni = Long.parseLong(resultSet.getString("iloscDni"));
                String statusU = resultSet.getString("statusU");


                // dodanie do listy nowego obiektu
                urlopies.add(new Urlopy(id,imieNazwisko,od,doU,iloscDni.intValue(),statusU));

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
            conn = dataSource.getConnection();

            // zapytanie INSERT i ustawienie jego parametrow
            String sql = "INSERT INTO daneUrlopu(email,od,doU,iloscDni,statusU) " +
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
            conn = dataSource.getConnection();

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
            conn = dataSource.getConnection();

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


    public void deleteUrlop(int id) throws Exception {

        Connection conn = null;
        PreparedStatement statement = null;

        try {

            // polaczenie z BD
            conn = dataSource.getConnection();

            // zapytanie DELETE
            String sql = "DELETE FROM daneUrlopu WHERE id =?";

            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);

            // wykonanie zapytania
            statement.execute();

        } finally {

            // zamkniecie obiektow JDBC
            close(conn, statement, null);

        }

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

            conn = dataSource.getConnection();


            status = true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

}
