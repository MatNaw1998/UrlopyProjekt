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
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            addUrzytkownik(request, response);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/created_user.html");
            dispatcher.forward(request, response);
        } catch(SQLIntegrityConstraintViolationException sqle){
            //            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/create_user.html");
//            requestDispatcher.forward(request, response);
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");

            out.println("<script type=\"text/javascript\">");
            out.println("alert('uzytkownik o takim id juz istnieje');");
            //out.println("window.location.assign('user_login.html';");
            out.println("window.location = 'create_user.html';");
            out.println("</script>");

            sqle.printStackTrace();

        } catch (SQLException e) {

//            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/create_user.html");
//            requestDispatcher.forward(request, response);
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");

            out.println("<script type=\"text/javascript\">");
            out.println("alert('niepoprawne id, powinna byc liczba calkowita');");
            //out.println("window.location.assign('user_login.html';");
            out.println("window.location = 'create_user.html';");
            out.println("</script>");

            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");

            out.println("<script type=\"text/javascript\">");
            out.println("alert('blad polaczenia');");
            //out.println("window.location.assign('user_login.html';");
            out.println("window.location = 'create_user.html';");
            out.println("</script>");

            e.printStackTrace();

        } catch (Exception e) {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");

            out.println("<script type=\"text/javascript\">");
            out.println("alert('blad polaczenia');");
            //out.println("window.location.assign('user_login.html';");
            out.println("window.location = 'create_user.html';");
            out.println("</script>");

            e.printStackTrace();
        }
    }


    private void addUrzytkownik(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // odczytanie danych z formularza
        String id_uzytkownika = request.getParameter("employeeID");
        String email = request.getParameter("email");
        String haslo = request.getParameter("haslo");

        // utworzenie obiektu klasy Phone
        DaneLogowania daneLogowania = new DaneLogowania(id_uzytkownika, email, haslo);

        // dodanie nowego obiektu do BD
        dbUtil.addDaneLogowania(daneLogowania);

        // powrot do listy


    }





}
