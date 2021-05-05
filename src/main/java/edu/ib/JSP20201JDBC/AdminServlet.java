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
import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * @author Gabriela Wrona i Mateusz Nawrocki
 */

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {

    //jdbc/urlop_web_appKierownik
    private DataSource dataSource;
    private DBUtilAdmin dbUtilAdmin;
    private DBUtilUrlopy dbUtilUrlopy;
DBUtilPracwnikInfo dbUtilPracownikInfo;
    private final String db_url = "jdbc:mysql://localhost:3306/projektUrlop?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";


    /**
     *
     * @param config przy wywołaniu servletu wywołuje nawiązania z bazą danych
     * @throws ServletException
     */

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            dbUtilUrlopy = new DBUtilUrlopy(dataSource);
            dbUtilAdmin = new DBUtilAdmin(dataSource);
            dbUtilPracownikInfo = new DBUtilPracwnikInfo(dataSource);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    /**
     * Konstruktor bezparametrowy wykorzystywany do polaczenia się z baza poprzez urzytkownika Kierownik
     */

    public AdminServlet() {
        Context initCtx = null;
        try {
            initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            // Look up our data source
            dataSource = (DataSource)
                    envCtx.lookup("jdbc/urlop_web_appKierownik");

        } catch (NamingException e) {
            e.printStackTrace();
        }

    }

    /**
     *
     * @param request pobrane zadanie z pliku html, wykorzystane do zalogowania
     * @param response odpowiedz ktora otrzymujemy
     * @throws ServletException
     * @throws IOException
     *
     * metoda wykrzystywana do zalogowania sie jako admin, oraz pobrania wartosci z tabeli przechowujacej dane o urlopach oraz wysalanie jej do pliku jsp
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {

        response.setContentType("text/html");

        String name = request.getParameter("loginInput");
        String password = request.getParameter("passwordInput");


            DaneLogowania daneAdmina = dbUtilAdmin.getAdminByLogin(name);
            if (daneAdmina.getHaslo().equals(password)) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/admin_view.jsp");
                List<Urlopy> urlopyList = null;
                try {
                    urlopyList = dbUtilUrlopy.getAll();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                request.setAttribute("URLOPY_LIST", urlopyList);
                dispatcher.forward(request, response);
            }else{
            RequestDispatcher dispatcher = request.getRequestDispatcher("/index.html");
            dispatcher.include(request, response);
        }
    }

    /**
     *
     * @param request zadanie pobierane z http
     * @param response odpowiedz wysylana do http
     * @throws ServletException
     * @throws IOException
     *
     * metoda do obslugi funkcjonalnosci strony kierownika, wyswietlanie listy urlopow, dodawanie urlopu, zatwierdzanie urlopu,odrzucanie i usuwanie.
     *
     */
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


    /**
     *
     * @param request zadanie z http
     * @param response odpowiedz do http
     * @throws Exception
     *
     * metoda do wysietlania listy urlopow w tabeli
     */
    private void listUrolps(HttpServletRequest request, HttpServletResponse response) throws Exception {

        List<Urlopy> urlopyList = dbUtilUrlopy.getAll();

        // dodanie listy do obiektu zadania
        request.setAttribute("URLOPY_LIST", urlopyList);

        // dodanie request dispatcher
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin_view.jsp");

        // przekazanie do JSP
        dispatcher.forward(request, response);
    }

    /**
     * metoda wykorzystywana  do zatwierdzania urlopu w zaleznosci od jego id w tabeli
     * @param request zadanie z http
     *  @param response odpowiedz do http
     * @throws Exception
     */
    private void updateZatwierdz(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // odczytanie danych z formularza
        int id = Integer.parseInt(request.getParameter("id"));

        // uaktualnienie danych w BD
        dbUtilUrlopy.updateZatwierdz(id);

        // wyslanie danych do strony z lista telefonow
        listUrolps(request, response);

    }
    /**
     * metoda wykorzystywana  do odrzucania, zmiany statusu, nie ma wyplywu na ilosc pozostalego urlopu,zaleznosci od jego id w tabeli
     * @param request zadanie z http
     *  @param response odpowiedz do http
     * @throws Exception
     */
    private void updateUdrzuc(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // odczytanie danych z formularza
        int id = Integer.parseInt(request.getParameter("id"));

        // uaktualnienie danych w BD
        dbUtilUrlopy.updateOdrzuc(id);

        // wyslanie danych do strony z lista telefonow
        listUrolps(request, response);

    }
    /**
     * metoda wykorzystywana  do usuwania urlopu zaleznosci od jego id w tabeli, ma wplyw na pozostaly urlop.
     * @param request zadanie z http
     *  @param response odpowiedz do http
     * @throws Exception
     */
  private void deleteUrlop(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int dni;
        // odczytanie danych z formularza
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println(id);

        String email = request.getParameter("email");
        System.out.println(email);
        PracownikInfo pracownikInfo = dbUtilPracownikInfo.getByEmail(email);
        dni = dbUtilUrlopy.getById(id).getIloscDni();
        int d = pracownikInfo.getIloscDni()+dni;
        pracownikInfo.setIloscDni(d);
        // uaktualnienie danych w BD

        dbUtilPracownikInfo.update(pracownikInfo);
        dbUtilUrlopy.delete(id); //TODO ASK FOR DELETE ()
        //<todo zwiekszenie pulidostepnych dni
        //<wyciagnij mi z bazy ile dni mial usuwany urlop i update user info



        // wyslanie danych do strony z lista telefonow
        listUrolps(request, response);

    }

}
