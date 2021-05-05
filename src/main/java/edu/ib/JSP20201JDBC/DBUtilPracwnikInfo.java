package edu.ib.JSP20201JDBC;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBUtilPracwnikInfo extends DBUtil {

    private String URL;
    private String name;
    private String password;
    private DataSource dataSource;
    public DBUtilPracwnikInfo(DataSource dataSource) {
        this.dataSource = dataSource;
    }




    public void add(PracownikInfo pracownikInfo) throws Exception {

        Connection conn = null;
        PreparedStatement statement = null;

        try {
            // polaczenie z BD
            conn = dataSource.getConnection();

            // zapytanie INSERT i ustawienie jego parametrow
            String sql = "INSERT INTO pracownikinfo(id, email, latapracy,mcepracy,dataZ,wyksztalcenie,iloscDni) " +
                    "VALUES(?,?,?,?,?,?,?)";

            statement = conn.prepareStatement(sql);
            statement.setString(1, String.valueOf(pracownikInfo.getId()));
            statement.setString(2, String.valueOf(pracownikInfo.getEmail()));
            statement.setString(3, String.valueOf(pracownikInfo.getLatapracy()));
            statement.setString(4, String.valueOf(pracownikInfo.getMcepracy()));
            statement.setString(5, String.valueOf(pracownikInfo.getDateZ()));
            statement.setString(6, String.valueOf(pracownikInfo.getWyksztalcenie()));
            statement.setString(7, String.valueOf(pracownikInfo.getIloscDni()));

            // wykonanie zapytania
            statement.execute();


        } finally {

            close(conn, statement, null);

        }
    }



    public void delete(int id) throws Exception {

        Connection conn = null;
        PreparedStatement statement = null;

        try {

            // polaczenie z BD
            conn = dataSource.getConnection();

            // zapytanie UPDATE
            String sql = "DELETE from pracownikinfo WHERE id =?";

            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            // wykonanie zapytania
            statement.execute();
            System.out.println(statement.toString());
        } finally {
            // zamkniecie obiektow JDBC
            close(conn, statement, null);
        }

    }

    public void update(PracownikInfo pracownikInfo) throws Exception {

        Connection conn = null;
        PreparedStatement statement = null;

        try {

            // polaczenie z BD
            System.out.println("przygotowanie poloczenia");
            conn = dataSource.getConnection();
            System.out.println("utworzono polaczenie");
            //id, email, latapracy,mcepracy,dataZ,wyksztalcenie,iloscDni
            // zapytanie UPDATE
            String sql = "UPDATE pracownikinfo SET email=?, latapracy=?, " +
                    "mcepracy=?, dataZ=?, wyksztalcenie=?, iloscDni = ? " +
                    "WHERE id =?";

            statement = conn.prepareStatement(sql);

            System.out.println("przygotowano statement");
            System.out.println(statement.toString());

            statement.setString(1, pracownikInfo.getEmail());
            statement.setString(2, pracownikInfo.getLatapracy());
            statement.setString(3, pracownikInfo.getMcepracy());
            statement.setString(4, pracownikInfo.getDateZ());
            statement.setString(5, pracownikInfo.getWyksztalcenie());
            statement.setInt(6, pracownikInfo.getIloscDni());
            statement.setInt(7, pracownikInfo.getId());
            System.out.println(statement.toString());
            // wykonanie zapytania
            statement.execute();

        } catch(Exception e){
            System.out.println("Update Pracownik info nie powiodlo sie");
        }


        finally {

            // zamkniecie obiektow JDBC
            close(conn, statement, null);


        }

    }

    public void updateIloscDni(int ilosc, int id) throws SQLException {

        Connection conn = null;
        PreparedStatement statement = null;

        try {

            // polaczenie z BD
            conn = dataSource.getConnection();

            // zapytanie UPDATE
            String sql = "UPDATE pracownikinfo SET iloscDni=?" +
                    "WHERE id =?";

            statement = conn.prepareStatement(sql);
            statement.setInt(1, ilosc);
            statement.setInt(2, id);

            // wykonanie zapytania
            statement.execute();


        } finally {

            // zamkniecie obiektow JDBC
            close(conn, statement, null);


        }

    }
/*
    public void updateIloscDni(PracownikInfo pracownikInfo) throws SQLException {

        Connection conn = null;
        PreparedStatement statement = null;

        try {

            // polaczenie z BD
            conn = dataSource.getConnection();

            // zapytanie UPDATE
            String sql = "UPDATE pracownikinfo SET iloscDni=?" +
                    "WHERE id =?";

            statement = conn.prepareStatement(sql);
            statement.setInt(1, pracownikInfo.getIloscDni());
            statement.setInt(2, pracownikInfo.getId());

            // wykonanie zapytania
            statement.execute();


        } finally {

            // zamkniecie obiektow JDBC
            close(conn, statement, null);


        }

    }

*/
    public PracownikInfo getById(int id){
        PracownikInfo pracownikInfo = null;
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            // polaczenie z BD
            conn = dataSource.getConnection();

            // zapytanie SELECT
            String sql = "SELECT * FROM pracownikinfo WHERE id =?";

            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            System.out.println(statement.toString());
            // wykonanie zapytania
            resultSet = statement.executeQuery();
            System.out.println(resultSet.toString());
            // przetworzenie wyniku zapytania

            if (resultSet.next()) {
////id, email, latapracy,mcepracy,dataZ,wyksztalcenie,iloscDni
                String email = resultSet.getString("email");
                String latapracy = resultSet.getString("latapracy");
                String mcepracy = resultSet.getString("mcepracy");
                String dataZ = resultSet.getString("dataZ");
                String wyksztalcenie = resultSet.getString("wyksztalcenie");
                int iloscDni = resultSet.getInt("iloscDni");


                // utworzenie obiektu
                pracownikInfo = new PracownikInfo(id, email, latapracy, mcepracy, dataZ, wyksztalcenie, iloscDni);

            } else {
                throw new Exception("Could not find employee with id " + id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            // zamkniecie obiektow JDBC
            close(conn, statement, resultSet);

        }
        return pracownikInfo;

    }


/*
    public PracownikInfo getPinfById(int id){

        PracownikInfo pracownikInfo = null;
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            // polaczenie z BD
            conn = dataSource.getConnection();

            // zapytanie SELECT
            String sql = "SELECT * FROM pracownikInfo WHERE id =?";

            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            System.out.println(statement.toString());
            // wykonanie zapytania
            resultSet = statement.executeQuery();
            System.out.println(resultSet.toString());
            // przetworzenie wyniku zapytania

            if (resultSet.next()) {
                String email = resultSet.getString("email");
                String latapracy = resultSet.getString("latapracy");
                String mcepracy = resultSet.getString("mcepracy");
                String dataZ = resultSet.getString("dataZ");
                String wyksztalcenie = resultSet.getString("wyksztalcenie");
                int iloscD = resultSet.getInt("iloscDni");


                // utworzenie obiektu
                pracownikInfo = new PracownikInfo(id, email, latapracy, mcepracy, dataZ, wyksztalcenie,iloscD);

            } else {
                throw new Exception("Could not find phone with id " + id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            // zamkniecie obiektow JDBC
            close(conn, statement, resultSet);

        }
        return pracownikInfo;

    }
*/





}
