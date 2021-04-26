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
                LocalDate od = LocalDate.parse(resultSet.getString("od"));
                LocalDate doU = LocalDate.parse(resultSet.getString("doU"));
                Long iloscDni = Long.parseLong(resultSet.getString("iloscDni"));
                String status = resultSet.getString("status");


                // dodanie do listy nowego obiektu
                urlopies.add(new Urlopy(imieNazwisko,od,doU,iloscDni,status));

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
            String sql = "INSERT INTO daneUrlopu(imieNazwisko,od,doU,iloscDni,status) " +
                    "VALUES(?,?,?,?,?)";

            statement = conn.prepareStatement(sql);
            statement.setString(1, String.valueOf(urlopy.getImieNazwisko()));
            statement.setString(2, String.valueOf(urlopy.getOd()));
            statement.setString(3, String.valueOf(urlopy.getDoU()));
            statement.setLong(4, urlopy.getIloscDni());
            statement.setString(5, urlopy.getStatus());


            // wykonanie zapytania
            statement.execute();


        } finally {

            close(conn, statement, null);

        }

    }

    public void deleteUrlop(String resort_name) throws Exception {

        Connection conn = null;
        PreparedStatement statement = null;

        try {

            // polaczenie z BD
            conn = DriverManager.getConnection(URL, name, password);

            // zapytanie DELETE
            String sql = "DELETE FROM daneUrlopu WHERE id =?";

            statement = conn.prepareStatement(sql);
            statement.setString(1, resort_name);

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
