package edu.ib.JSP20201JDBC;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Gabriela Wrona i Mateusz Nawrocki
 */
public class DBUtilUrlopy extends DBUtil {

    private String URL;
    private String name;
    private String password;
    private DataSource dataSource;
    /**
     * konstruktor do tworzenia obiektu DanychLogowania, wykorzysywany w laczeniu sie z baza
     */
    public DBUtilUrlopy(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * metoda do pobierania wszystkich urlopow w tabeli
     * @return zwraca liste urlopow
     * @throws Exception
     */

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
                String email = resultSet.getString("email");
                String od = resultSet.getString("od");
                String doU = resultSet.getString("doU");
                Long iloscDni = Long.parseLong(resultSet.getString("iloscDni"));
                String statusU = resultSet.getString("statusU");


                // dodanie do listy nowego obiektu
                urlopies.add(new Urlopy(id,email,od,doU,iloscDni.intValue(),statusU));

            }

        } finally {

            // zamkniecie obiektow JDBC
            close(conn, statement, resultSet);
        }


        return urlopies;
    }

    /**
     * metoda do zwracania urlopu w zaleznosci od jego id
     * @param id szukane id
     * @return zwraca obiekt z szukanym id
     * @throws Exception
     */

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


    /**
     * metoda wykorzystywana do aktualizowania statusu urolpu na zatwierdzony
     * @param id id aktualizowanego urlopu
     * @throws Exception
     */
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
    /**
     * metoda wykorzystywana do aktualizowania statusu urolpu na odrzucony
     * @param id id aktualizowanego urlopu
     * @throws Exception
     */
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
    /**
     * metoda wykorzystywana do aktualizowania statusu urolpu na do usuniecia
     * @param id id aktualizowanego urlopu
     * @throws Exception
     */
    public void updatePoprosOUsuniecie( int id ) throws Exception {
        Connection conn = null;
        PreparedStatement statement = null;

        try {

            // polaczenie z BD
            conn = dataSource.getConnection();

            // zapytanie UPDATE
            String sql = "UPDATE daneUrlopu SET statusU='Do usuniecia'" +
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

    /**
     *metoda wykorzysywana do usuwania urlopu o danym id
     * @param id id usuwanego urlopu
     * @throws Exception
     */

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

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
