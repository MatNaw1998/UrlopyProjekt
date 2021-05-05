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



    public boolean validate(String name, String pass) {
        //TODO sprawdz czy w  BAZIE DANYCH ADMINI isnieje uzytkownik o pass "pass", emal "name"
        //to pod spodem sprawdza tylko czy mysql ma takiego uzytkownika

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
