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
import java.util.List;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private DBUtilUser dbUtil;
    private DataSource dataSource;


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


        String login = request.getParameter("loginInput");
        String haslo = request.getParameter("passwordInput");

        try {
            DaneLogowania daneLogowania = dbUtil.getKontoByLogin(login);
            if (daneLogowania.getHaslo().equals(haslo)) {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/user_view.html");



                requestDispatcher.forward(request, response);

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
        }
    }
}

