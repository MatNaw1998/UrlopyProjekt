package edu.ib.JSP20201JDBC.servlets;

import edu.ib.JSP20201JDBC.db.DBUtil;
import edu.ib.JSP20201JDBC.model.DaneLogowania;

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
import java.util.List;

public class DaneLogowaniaServlet extends HttpServlet {


    @WebServlet("/DaneLogowaniaServlet")
    public class AdminServlet extends HttpServlet {

        private DBUtil dbUtil;
        private final String db_url = "jdbc:mysql://localhost:3306/ibgsm?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=CET";


        @Override
        public void init(ServletConfig config) throws ServletException {
            super.init(config);

            try {

                dbUtil = new DBUtil(db_url);

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

                List<DaneLogowania> daneLogowaniaList = null;

                try {

                    daneLogowaniaList = dbUtil.getPhones();

                } catch (Exception e) {
                    e.printStackTrace();
                }

                // dodanie listy do obiektu zadania
                request.setAttribute("PHONES_LIST", daneLogowaniaList);

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

                 //   case "LIST":
                 //       listPhones(request, response);
                 //       break;

                    case "ADD":
                        addDaneLogowania(request, response);
                        break;

                    case "LOAD":
                        loadDaneLogowania(request, response);
                        break;

                    case "UPDATE":
                        updatePhone(request, response);
                        break;

                    case "DELETE":
                        deletePhone(request, response);
                        break;

                    default:
                        loadDaneLogowania(request, response);
                   //     listPhones(request, response);
                }

            } catch (Exception e) {
                throw new ServletException(e);
            }

        }

        private void deletePhone(HttpServletRequest request, HttpServletResponse response) throws Exception {

            // odczytanie danych z formularza
            String id = request.getParameter("phoneID");

            // usuniecie telefonu z BD
            dbUtil.deleteDaneLogowania(id);

            // wyslanie danych do strony z lista telefonow
            //listPhones(request, response);

        }

        private void updatePhone(HttpServletRequest request, HttpServletResponse response) throws Exception {

            // odczytanie danych z formularza
            String id = request.getParameter("userID"); //<todo autoincrement ID
            String login = request.getParameter("loginInput");
            String haslo = request.getParameter("hasloInput");

            // utworzenie nowego telefonu
            DaneLogowania daneLogowania = new DaneLogowania(id, login, haslo);

            // uaktualnienie danych w BD
            dbUtil.updateDaneLogowania(daneLogowania);

            // wyslanie danych do strony z lista telefonow
           // listPhones(request, response);

        }

        private void loadDaneLogowania(HttpServletRequest request, HttpServletResponse response) throws Exception {

            // odczytanie id telefonu z formularza
            String id = request.getParameter("phoneID");

            // pobranie  danych telefonu z BD
            DaneLogowania daneLogowania = dbUtil.getDaneLogowania(id);

            // przekazanie telefonu do obiektu request
            request.setAttribute("DANE_LOGOWANIA", daneLogowania);

            // wyslanie danych do formmularza JSP (update_phone_form)
            RequestDispatcher dispatcher = request.getRequestDispatcher("/update_phone_form.jsp");
            dispatcher.forward(request, response);

        }

        private void addDaneLogowania(HttpServletRequest request, HttpServletResponse response) throws Exception {

            // odczytanie danych z formularza
            String login = request.getParameter("loginInput");
            String haslo = request.getParameter("hasloInput");


            // utworzenie obiektu klasy Phone
            DaneLogowania daneLogowania = new DaneLogowania(login, haslo);

            // dodanie nowego obiektu do BD
            dbUtil.addDaneLogowania(daneLogowania);

            // powrot do listy
            //listPhones(request, response);

        }
/*
        private void listPhones(HttpServletRequest request, HttpServletResponse response) throws Exception {

            List<Phone> phoneList = dbUtil.getPhones();

            // dodanie listy do obiektu zadania
            request.setAttribute("PHONES_LIST", phoneList);

            // dodanie request dispatcher
            RequestDispatcher dispatcher = request.getRequestDispatcher("/admin_view.jsp");

            // przekazanie do JSP
            dispatcher.forward(request, response);

        }
*/

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
}