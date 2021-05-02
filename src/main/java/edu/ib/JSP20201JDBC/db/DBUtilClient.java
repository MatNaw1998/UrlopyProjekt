package edu.ib.JSP20201JDBC.db;

import edu.ib.JSP20201JDBC.model.DaneLogowania;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBUtilLogin /*extends DBUtil*/ {

    private DataSource dataSource;

    public DBUtilLogin(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<DaneLogowania> getDaneLogowania() throws Exception {

        List<DaneLogowania> phones = new ArrayList<>();

        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            // polaczenie z BD
            conn = dataSource.getConnection();

            // wyrazenie SQL
            String sql = "SELECT * FROM dane_logowania";
            statement = conn.createStatement();

            // wykonanie wyrazenia SQL
            resultSet = statement.executeQuery(sql);

            // przetworzenie wyniku zapytania
            while (resultSet.next()) {
/*
                // pobranie danych z rzedu
                int id = resultSet.getInt("id");
                String model = resultSet.getString("model");
                String make = resultSet.getString("make");
                double price = resultSet.getDouble("price");
                double diagonal = resultSet.getDouble("diagonal");
                int storage = resultSet.getInt("storage");
                String OStype = resultSet.getString("OStype");

                // dodanie do listy nowego obiektu
                phones.add(new Phone(id, model, make, price, diagonal, storage, OStype));
*/
            }

        } finally {

            // zamkniecie obiektow JDBC
            close(conn, statement, resultSet);
        }


        return phones;

    }
}
