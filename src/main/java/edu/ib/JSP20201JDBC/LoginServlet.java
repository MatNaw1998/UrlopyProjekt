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

    private DBUtilUser dbUtilUser;
    private DBUtilUrlopy dbUtilUrlopy;
    private DBUtilPracwnikInfo dbUtilPracwnikInfo;
    private DataSource dataSource;
    private String emial;
    private PracownikInfo pracownikInfo2;
    private DaneLogowania daneLogowania2;

    public LoginServlet() {

        Context initCtx = null;
        try {
            initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            // Look up our data source
            dataSource = (DataSource)
                    envCtx.lookup("jdbc/urlop_web_appPracownik"); //todo

        } catch (NamingException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        try {

            dbUtilUser = new DBUtilUser(dataSource);
            dbUtilPracwnikInfo = new DBUtilPracwnikInfo(dataSource);
            dbUtilUrlopy = new DBUtilUrlopy(dataSource);
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
                out.println("alert('Zle dodawanie');");
                //  out.println("window.location.assign('user_login.html';");
                out.println("window.location = 'add_phone_form.jsp';");
                out.println("</script>");
            }
        }
        else if (request.getParameter("usun") !=null){
            try {
                usunUrlop(request, response);
            } catch (Exception e) {
            /*RequestDispatcher requestDispatcher = request.getRequestDispatcher("/not_created_user.html");
            requestDispatcher.forward(request, response);*/
                PrintWriter out = response.getWriter();
                response.setContentType("text/html");
                out.println("<script charset=\"utf-8\" type=\"text/javascript\">");
                out.println("alert('Zle dodawanie');");
                //  out.println("window.location.assign('user_login.html';");
                out.println("window.location = 'add_phone_form.jsp';");
                out.println("</script>");
            }
        }
        else if (request.getParameter("loginuser")!=null){
            try {
                fillTable(request, response);
            } catch (IOException e) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("add_phone_form.jsp");
            }
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
                        usunUrlop(request, response);
                        break;
                    case "LOAD":
                        getUrlopById(request, response);
                        break;
                    case "MOD":
                        updateUrlop(request, response);
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


        int dostepnyUrlop = pracownikInfo2.getIloscDni();
        int pozostalyUrlop = (int) (dostepnyUrlop -ilosc);

        if (pozostalyUrlop>=0){
            // utworzenie obiektu klasy Phone
            Urlopy urlopy = new Urlopy(emial,od,doU,ilosc.intValue(),"do akceptacji");

            pracownikInfo2.setIloscDni(pozostalyUrlop);
            dbUtilPracwnikInfo.update(pracownikInfo2);

            // dodanie nowego obiektu do BD
            dbUtilUser.addUrlop(urlopy);

            // powrot do listy
            listUrolps(request, response);

        }else{
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");

            out.println("<script type=\"text/javascript\">");
            out.println("alert('zbyt dlugi urlop');");
            //out.println("window.location.assign('user_login.html';");
            out.println("window.location = 'user_view.jsp';");
            out.println("</script>");
        }
    }






    private void fillTable(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String login = request.getParameter("loginInput");
        String haslo = request.getParameter("passwordInput");
        List<Urlopy> urlopyList = null;
        try {
            DaneLogowania daneLogowania = dbUtilUser.getKontoByLogin(login);
            PracownikInfo pracownikInfo = dbUtilPracwnikInfo.getById(Integer.parseInt(daneLogowania.getId_uzytkownika()));
            daneLogowania2 = daneLogowania;
            pracownikInfo2 = pracownikInfo;
            urlopyList = dbUtilUser.getUrlopy(login);
            if (daneLogowania.getHaslo().equals(haslo)) {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/user_view.jsp");


                request.setAttribute("URLOPY_LIST", urlopyList);
                request.setAttribute("ILOSC_DNI",pracownikInfo.getIloscDni());
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

        List<Urlopy> urlopyList = dbUtilUser.getUrlopy(emial);
        PracownikInfo pracownikInfo = dbUtilPracwnikInfo.getById(Integer.parseInt(daneLogowania2.getId_uzytkownika()));
        pracownikInfo2 = pracownikInfo;

        // dodanie listy do obiektu zadania
        request.setAttribute("URLOPY_LIST", urlopyList);
        request.setAttribute("ILOSC_DNI",pracownikInfo.getIloscDni());


        // dodanie request dispatcher
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user_view.jsp");

        // przekazanie do JSP
        dispatcher.forward(request, response);

    }

    private void usunUrlop(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int dni;
        // odczytanie danych z formularza
        int id = Integer.parseInt(request.getParameter("id"));


        dbUtilUrlopy.updatePoprosOUsuniecie(id);
        // wyslanie danych do strony z lista telefonow
        listUrolps(request, response);

    }
    private void updateUrlop(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // odczytanie danych z formularza
        int id = Integer.parseInt(request.getParameter("id"));

        String od = request.getParameter("od");
        String doU = request.getParameter("doU");
        LocalDate odD = LocalDate.parse(od);
        LocalDate doD = LocalDate.parse(doU);
        Long ilosc = DAYS.between(odD, doD);

        Urlopy urlopy1 = dbUtilUrlopy.getById(id);


        // utworzenie nowego telefonu
        Urlopy urlopy2 = new Urlopy(id,emial,od,doU,ilosc.intValue(),"do akceptacji");

        int diff = urlopy1.getIloscDni()-urlopy2.getIloscDni();
        pracownikInfo2.getIloscDni();


        PracownikInfo pracownikInfo3 = pracownikInfo2;
        pracownikInfo3.setIloscDni((pracownikInfo2.getIloscDni()+diff));

        // uaktualnienie danych w BD
        dbUtilUser.updateUrlop(urlopy2);
        dbUtilPracwnikInfo.update(pracownikInfo3);

        // wyslanie danych do strony z lista telefonow
        listUrolps(request, response);

    }

    private void getUrlopById(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String id = request.getParameter("id");

        Urlopy urlopy = dbUtilUser.getUrlopById(Integer.parseInt(id));

        request.setAttribute("URLOP",urlopy);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/update_urlop.jsp");
        dispatcher.forward(request,response);
    }



}

