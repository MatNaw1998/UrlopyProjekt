package edu.ib.JSP20201JDBC;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Gabriela Wrona i Mateusz Nawrocki
 */
public class DBUtilPracwnikInfo extends DBUtil {

    private String URL;
    private String name;
    private String password;
    private DataSource dataSource;
    /**
     * konstruktor do tworzenia obiektu DanychLogowania, wykorzysywany w laczeniu sie z baza
     */
    public DBUtilPracwnikInfo(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    /**
     * Metoda do dodawania informacji o pracowniku, ilosci dni wolych, email itd.
     * @param pracownikInfo pobiera obiekt klasy PracownikInfo azeby wyslac go do bazy danych
     * @throws Exception
     */
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

    /**
     * metoda do aktualizacji danych o pracowniku
     * @param pracownikInfo obiekt z pobranymi danymi do aktualizacji
     * @throws Exception
     */
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

    /**
     * Metoda do pobierania informacji o pracowniku w zaleznosci od jego id
     * @param id szukne id
     * @return zwraca szukany obiekt
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

    /**
     * Metoda do pobierania informacji o pracowniku w zaleznosci od jego email'a
     * @param email szukany email
     * @return zwraca obiekt z szukanym emailem
     */
    public PracownikInfo getByEmail(String email){
        PracownikInfo pracownikInfo = null;
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            // polaczenie z BD
            conn = dataSource.getConnection();


            System.out.println("conn");
            // zapytanie SELECT
            String sql = "SELECT * FROM pracownikinfo WHERE email =?";

            statement = conn.prepareStatement(sql);
            statement.setString(1, email);
            System.out.println(statement.toString());
            // wykonanie zapytania
            resultSet = statement.executeQuery();
            System.out.println(resultSet.toString());
            // przetworzenie wyniku zapytania

            if (resultSet.next()) {
////id, email, latapracy,mcepracy,dataZ,wyksztalcenie,iloscDni
                int id  = resultSet.getInt("id");
                String latapracy = resultSet.getString("latapracy");
                String mcepracy = resultSet.getString("mcepracy");
                String dataZ = resultSet.getString("dataZ");
                String wyksztalcenie = resultSet.getString("wyksztalcenie");
                int iloscDni = resultSet.getInt("iloscDni");


                // utworzenie obiektu
                pracownikInfo = new PracownikInfo(id, email, latapracy, mcepracy, dataZ, wyksztalcenie, iloscDni);

            } else {
                throw new Exception("Could not find employee with id " + email);
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







}