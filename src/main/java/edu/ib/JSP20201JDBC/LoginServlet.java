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
import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private DBUtilUser dbUtil;
    private DataSource dataSource;
    private String emial;

    public LoginServlet() {

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
        if (request.getParameter("dodajurlop") !=null){
            try {
                addUrlop(request, response);
            } catch (Exception e) {
            /*RequestDispatcher requestDispatcher = request.getRequestDispatcher("/not_created_user.html");
            requestDispatcher.forward(request, response);*/
                PrintWriter out = response.getWriter();
                response.setContentType("text/html");
                out.println("<script charset=\"utf-8\" type=\"text/javascript\">");
                out.println("alert('NIEPOPRAWNY LOGIN');");
                //  out.println("window.location.assign('user_login.html';");
                out.println("window.location = 'user_login.html';");
                out.println("</script>");
            }
        }
        else if (request.getParameter("loginuser")!=null){
            loginUser(request, response);
        }
        else {
            try {

                // odczytanie zadania
                String command = request.getParameter("command");

                if (command == null)
                    command = "LIST";

                switch (command) {

                    case "LIST":
                        listUrolps(request, response);
                        break;
                    case "USUN":
                        updateUsun(request, response);
                        break;
                    default:
                        listUrolps(request, response);
                }

            } catch (Exception e) {
                throw new ServletException(e);
            }
        }

    }

    private void addUrlop(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // odczytanie danych z formularza
        String od = request.getParameter("od");
        String doU = request.getParameter("doU");
        LocalDate odD = LocalDate.parse(od);
        LocalDate doD = LocalDate.parse(doU);
        Long ilosc = DAYS.between(odD, doD);


        // utworzenie obiektu klasy Phone
        Urlopy urlopy = new Urlopy(emial,od,doU,ilosc,"do akceptacji");

        // dodanie nowego obiektu do BD
        dbUtil.addUrlop(urlopy);

        // powrot do listy
        listUrolps(request, response);

    }

    private void loginUser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String login = request.getParameter("loginInput");
        String haslo = request.getParameter("passwordInput");
        List<Urlopy> urlopyList = null;
        String em = null;
        try {
            DaneLogowania daneLogowania = dbUtil.getKontoByLogin(login);
            urlopyList = dbUtil.getUrlopy(login);
            em = login;
            if (daneLogowania.getHaslo().equals(haslo)) {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/user_view.jsp");


                request.setAttribute("URLOPY_LIST", urlopyList);
                request.setAttribute("EMAIL", em);
                requestDispatcher.forward(request, response);
                emial = login;

            } else {
                PrintWriter out = response.getWriter();
                response.setContentType("text/html");

                out.println("<script type=\"text/javascript\">");
                out.println("alert('Wrong password');");
                //out.println("window.location.assign('user_login.html';");
                out.println("window.location = 'user_login.html';");
                out.println("</script>");
            }


        }catch (NullPointerException e){
            /*RequestDispatcher requestDispatcher = request.getRequestDispatcher("/not_created_user.html");
            requestDispatcher.forward(request, response);*/
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            out.println("<script charset=\"utf-8\" type=\"text/javascript\">");
            out.println("alert('NIEPOPRAWNY LOGIN');");
            //  out.println("window.location.assign('user_login.html';");
            out.println("window.location = 'user_login.html';");
            out.println("</script>");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void listUrolps(HttpServletRequest request, HttpServletResponse response) throws Exception {

        List<Urlopy> urlopyList = dbUtil.getUrlopy(emial);

        // dodanie listy do obiektu zadania
        request.setAttribute("URLOPY_LIST", urlopyList);

        // dodanie request dispatcher
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user_view.jsp");

        // przekazanie do JSP
        dispatcher.forward(request, response);

    }

    private void updateUsun(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // odczytanie danych z formularza
        int id = Integer.parseInt(request.getParameter("id"));

        // uaktualnienie danych w BD
        dbUtil.updateUsun(id);

        // wyslanie danych do strony z lista telefonow
        listUrolps(request, response);

    }


}

