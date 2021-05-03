package edu.ib.JSP20201JDBC.servlets;

import edu.ib.JSP20201JDBC.dao.DaneLogowaniaDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

    @WebServlet("/LoginServlet")
    public class LoginServlet extends HttpServlet {

        private DaneLogowaniaDAO daneLogowaniaDAO;
        private final String db_url = "jdbc:mysql://localhost:3306/ibgsm?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=CET";


        @Override
        public void init(ServletConfig config) throws ServletException {
            super.init(config);

            try {

                daneLogowaniaDAO  = new DaneLogowaniaDAO(db_url, "root", "root");

            } catch (Exception e) {
                throw new ServletException(e);
            }
        }

        public void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            String n=request.getParameter("username");
            String p=request.getParameter("userpass");

            try {
                if(daneLogowaniaDAO.validateLogin(n)){

                    if(daneLogowaniaDAO.validatePassword(n, p)) {

                        RequestDispatcher rd = request.getRequestDispatcher("servlet2");
                        rd.forward(request, response);
                    }
                    else {

                        out.print("Sorry password error");
                        RequestDispatcher rd=request.getRequestDispatcher("index2.html");
                        rd.include(request,response);
                    }

                }
                else{
                    out.print("Sorry username error");
                    RequestDispatcher rd=request.getRequestDispatcher("index2.html");
                    rd.include(request,response);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            out.close();
        }

}