package edu.ib.JSP20201JDBC;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

@WebServlet("/CreateUserServlet")
public class CreateUserServlet extends HttpServlet {

    private DBUtilAddUser dbUtil;
    private final String db_url = "jdbc:mysql://localhost:3306/projektUrlop?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

//TODO zadanko- polaczyc mi create user html z servletem, nie potrafie znalezc bledu
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        try {

            dbUtil = new DBUtilAddUser(db_url);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void addResort(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // odczytanie danych z formularza
        String id_uzytkownika = request.getParameter("employeeID");
        String email = request.getParameter("password");
        String haslo = request.getParameter("email");

        // utworzenie obiektu klasy Phone
        DaneLogowania daneLogowania = new DaneLogowania(id_uzytkownika, email, haslo);

        // dodanie nowego obiektu do BD
        dbUtil.addDaneLogowania(daneLogowania);

        // powrot do listy


    }





}
