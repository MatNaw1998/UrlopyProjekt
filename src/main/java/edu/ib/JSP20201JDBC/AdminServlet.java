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

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {

    private DBUtilAdmin dbUtil;
    private final String db_url = "jdbc:mysql://localhost:3306/projektUrlop?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        try {

            dbUtil = new DBUtilAdmin(db_url);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {

        response.setContentType("text/html");

        String name = request.getParameter("loginInput");
        String password = request.getParameter("passwordInput");

        dbUtil.setName(name);
        dbUtil.setPassword(password);

        if (validate(name, password)) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/admin_view.jsp");

            List<Urlopy> urlopyList = null;

            try {

                urlopyList = dbUtil.getUrlopy();

            } catch (Exception e) {
                e.printStackTrace();
            }

            // dodanie listy do obiektu zadania
            request.setAttribute("URLOPY_LIST", urlopyList);

            dispatcher.forward(request, response);
        } else {

            RequestDispatcher dispatcher = request.getRequestDispatcher("/index.html");
            dispatcher.include(request, response);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {


        try {

            // odczytanie zadania
            String command = request.getParameter("command");

            if (command == null)
                command = "LIST";

            switch (command) {

                case "LIST":
                    listUrolps(request, response);
                    break;

                case "ADD":
                    addResort(request, response);
                    break;

                case "ZATWIERDZ":
                    updateZatwierdz(request, response);
                    break;
                case "ODRZUC":
                    updateUdrzuc(request, response);
                    break;
                case "USUN":
                    deleteUrlop(request, response);
                    break;
                default:
                    listUrolps(request, response);
            }

        } catch (Exception e) {
            throw new ServletException(e);
        }

    }


    private void addResort(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // odczytanie danych z formularza
        String imieNazwisko = request.getParameter("imieNazwisko");
        String od = request.getParameter("od");
        String doU = request.getParameter("doU");
        LocalDate odD = LocalDate.parse(od);
        LocalDate doD = LocalDate.parse(doU);
        Long ilosc = DAYS.between(odD, doD);


        // utworzenie obiektu klasy Phone
        Urlopy urlopy = new Urlopy(imieNazwisko,od,doU,ilosc,"do akceptacji");

        // dodanie nowego obiektu do BD
        dbUtil.addUrlop(urlopy);

        // powrot do listy
        listUrolps(request, response);

    }

    private void listUrolps(HttpServletRequest request, HttpServletResponse response) throws Exception {

        List<Urlopy> urlopyList = dbUtil.getUrlopy();

        // dodanie listy do obiektu zadania
        request.setAttribute("URLOPY_LIST", urlopyList);

        // dodanie request dispatcher
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin_view.jsp");

        // przekazanie do JSP
        dispatcher.forward(request, response);

    }

    private void deleteUrlop(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // odczytanie danych z formularza
        int id = Integer.parseInt(request.getParameter("id"));

        // usuniecie telefonu z BD
        dbUtil.deleteUrlop(id);

        // wyslanie danych do strony z lista telefonow
        listUrolps(request, response);

    }

    private void updateZatwierdz(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // odczytanie danych z formularza
        int id = Integer.parseInt(request.getParameter("id"));

        // uaktualnienie danych w BD
        dbUtil.updateZatwierdz(id);

        // wyslanie danych do strony z lista telefonow
        listUrolps(request, response);

    }

    private void updateUdrzuc(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // odczytanie danych z formularza
        int id = Integer.parseInt(request.getParameter("id"));

        // uaktualnienie danych w BD
        dbUtil.updateOdrzuc(id);

        // wyslanie danych do strony z lista telefonow
        listUrolps(request, response);

    }


    private boolean validate(String name, String pass) {
        boolean status = false;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }

        Connection conn = null;

        try {

            conn = DriverManager.getConnection(db_url, name, pass);
            status = true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

}
