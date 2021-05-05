package edu.ib.JSP20201JDBC;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUtilUrlopy extends DBUtil {

    private String URL;
    private String name;
    private String password;
    private DataSource dataSource;

    public DBUtilUrlopy(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Urlopy> getAll() throws Exception {
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


    public Urlopy getById(int id) throws Exception {
        Urlopy urlop = null;

        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            // polaczenie z BD
            conn = dataSource.getConnection();

            // zapytanie SELECT
            String sql = "SELECT * FROM daneUrlopu where id=?";
            statement = conn.prepareStatement(sql);

            System.out.println( statement);
            statement.setInt(1, id);

            System.out.println( statement);
            // wykonanie zapytania
            resultSet = statement.executeQuery();
            System.out.println("executed");
            // przetworzenie wyniku zapytania
            if (resultSet.next()) {

                System.out.println("if");
                // pobranie danych z rzedu

                String imieNazwisko = resultSet.getString("email");
                String od = resultSet.getString("od");
                String doU = resultSet.getString("doU");
                int iloscDni = Integer.parseInt(resultSet.getString("iloscDni"));
                String statusU = resultSet.getString("statusU");


                // dodanie do listy nowego obiektu
                urlop = new Urlopy(id,imieNazwisko,od,doU,iloscDni,statusU);

            }

        } finally {

            // zamkniecie obiektow JDBC
            close(conn, statement, resultSet);
        }


        return urlop;
    }


    public void add(Urlopy urlopy) throws Exception {

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


    public void delete(int id) throws Exception {

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
/*
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
*/

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
