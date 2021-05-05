package edu.ib.JSP20201JDBC;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Gabriela Wrona i Mateusz Nawrocki
 */
public class DBUtilUser extends DBUtil {

    private String URL;
    private String name;
    private String password;
    private DataSource dataSource;
    /**
     * Konstruktor, wykorzysywany w laczeniu sie z baza
     */
    public DBUtilUser(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    /**
     * Pobranie listy urlopow dla danego pracownika
     * @param email email pracownika ktorego urlopow szukamy
     * @return zwraca liste wszystkich urlopwo pracownika
     * @throws Exception
     */
    public List<Urlopy> getUrlopy(String email) throws Exception {
        List<Urlopy> urlopies = new ArrayList<>();

        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {

            // polaczenie z BD
            conn = dataSource.getConnection();

            // zapytanie SELECT

            String sql = "SELECT * FROM daneUrlopu WHERE email = '" + email +"'";

            statement = conn.prepareStatement(sql);
            //statement.setString(1, email);

            statement = conn.prepareStatement(sql);
            resultSet = statement.executeQuery(sql);
            // przetworzenie wyniku zapytania
            while (resultSet.next()) {

                // pobranie danych z rzedu
                int id = resultSet.getInt("id");
                String email1 = resultSet.getString("email");
                String od = resultSet.getString("od");
                String doU = resultSet.getString("doU");
                Long iloscDni = Long.parseLong(resultSet.getString("iloscDni"));
                String statusU = resultSet.getString("statusU");


                // dodanie do listy nowego obiektu
                urlopies.add(new Urlopy(id,email1,od,doU,iloscDni.intValue(),statusU));

            }

        } finally {

            // zamkniecie obiektow JDBC
            close(conn, statement, resultSet);
        }


        return urlopies;
    }

    /**
     * metoda wykorzysywana do dodawania urlopow przez pracownika
     * @param urlopy obiekt z formularza, nowy urlop
     * @throws Exception
     */
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
            statement.setString(1, String.valueOf(urlopy.getEmail()));
            statement.setString(2, String.valueOf(urlopy.getOd()));
            statement.setString(3, String.valueOf(urlopy.getDoU()));
            statement.setLong(4, urlopy.getIloscDni());

            // wykonanie zapytania
            statement.execute();

        } finally {
            close(conn, statement, null);
        }
    }

    /**
     * podczas rejestracji tworzony jest obiekt z danymi o pracowniku
     * @param daneLogowania dane nowego pracownika
     * @throws Exception
     */
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


    /**
     * metoda wykorzystywana do szukania danych logowania w zaleznosci od loginu
     * @param login podany w formularz login
     * @return
     */
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

    /**
     * Metoda wykorzystywana przy aktualizowaniu przez pracownika urlopu
     * @param urlopy dane pobrane z formularza i servletu
     * @throws Exception
     */
    public void updateUrlop(Urlopy urlopy) throws Exception {

        Connection conn = null;
        PreparedStatement statement = null;

        try {

            // polaczenie z BD
            conn = dataSource.getConnection();

            // zapytanie UPDATE
            String sql = "UPDATE daneUrlopu SET id=?, email=?, od=?," +
                    "doU=?, iloscDni=?, statusU=? " +
                    "WHERE id =?";

            statement = conn.prepareStatement(sql);
            statement.setInt(1, urlopy.getId());
            statement.setString(2, urlopy.getEmail());
            statement.setString(3, urlopy.getOd());
            statement.setString(4, urlopy.getDoU());
            statement.setLong(5, urlopy.getIloscDni());
            statement.setString(6, urlopy.getStatusU());
            statement.setInt(7, urlopy.getId());

            // wykonanie zapytania
            statement.execute();

        } finally {

            // zamkniecie obiektow JDBC
            close(conn, statement, null);


        }

    }


    /**
     * metoda do szukania urlopu w zaleznosci od jego id
     * @param id id szukanego urlopu
     * @return
     */
    public Urlopy getUrlopById(int id){
        Urlopy urlop = null;
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            // polaczenie z BD
            conn = dataSource.getConnection();

            // zapytanie SELECT
            String sql = "SELECT * FROM daneurlopu WHERE id =?";

            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            System.out.println(statement.toString());
            // wykonanie zapytania
            resultSet = statement.executeQuery();
            System.out.println(resultSet.toString());
            // przetworzenie wyniku zapytania

            if (resultSet.next()) {

                String email = resultSet.getString("email");
                String od = resultSet.getString("od");
                String doU = resultSet.getString("doU");
                Long iloscDni = resultSet.getLong("iloscDni");
                String statusU = resultSet.getString("statusU");


                // utworzenie obiektu
                urlop = new Urlopy(id, email, od, doU, iloscDni.intValue(), statusU);

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
        return urlop;

    }





}
