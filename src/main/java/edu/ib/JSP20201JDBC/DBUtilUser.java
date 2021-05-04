package edu.ib.JSP20201JDBC;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUtilUser extends DBUtil {

    private String URL;
    private String name;
    private String password;
    private DataSource dataSource;
    public DBUtilUser(DataSource dataSource) {
        this.dataSource = dataSource;
    }



    public List<Urlopy> getUrlopy(String email) throws Exception {
        List<Urlopy> urlopies = new ArrayList<>();

        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {

            // polaczenie z BD
            conn = dataSource.getConnection();

            // zapytanie SELECT
            String sql = "SELECT * FROM daneUrlopu WHERE imieNazwisko = "+ email ;
            statement = conn.prepareStatement(sql);
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

    public DaneLogowania getKontoByLogin(String login){

        DaneLogowania daneLogowania = null;
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try{

            conn = dataSource.getConnection();

            String sql = "SELECT * FROM dane_logowania WHERE email like ?";

            statement = conn.prepareStatement(sql);
            statement.setString(1,login);

            resultSet = statement.executeQuery();

            if (resultSet.next()){
                String email = resultSet.getString("email");
                String haslo = resultSet.getString("haslo");
                int id_uzytkownika = resultSet.getInt("id_uzytkownika");

                daneLogowania = new DaneLogowania(String.valueOf(id_uzytkownika),email,haslo);
        /*    }else {
                throw new Exception("Could not find account with login :  " + login); */
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            close(conn,statement,resultSet);

    }
    return daneLogowania;
    }




}
