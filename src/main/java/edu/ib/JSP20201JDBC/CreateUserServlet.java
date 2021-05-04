package edu.ib.JSP20201JDBC;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

@WebServlet("/CreateUserServlet")
public class CreateUserServlet extends HttpServlet {

    private DBUtilUser dbUtil;
    private final String db_url = "jdbc:mysql://localhost:3306/projektUrlop?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private DataSource dataSource;

    public CreateUserServlet() {

// Obtain our environment naming context
        Context initCtx = null;
        try {
            initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            // Look up our data source
            dataSource = (DataSource)
                    envCtx.lookup("jdbc/urlop_web_app");

        } catch (NamingException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        try {

            dbUtil = new DBUtilUser(dataSource);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            addUrzytkownik(request,response);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/create_user.html");
            dispatcher.forward(request, response);
        } catch (Exception e) {

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/not_created_user.html");
            requestDispatcher.forward(request, response);
            e.printStackTrace();
        }
    }


    private void addUrzytkownik(HttpServletRequest request, HttpServletResponse response) throws Exception {

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
