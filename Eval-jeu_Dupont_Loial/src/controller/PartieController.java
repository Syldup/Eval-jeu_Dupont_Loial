package controller;

import model.LoginBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet
public class PartieController extends HttpServlet {

    private static final String PAGE_ACCUEIL_PARTIE_JSP = "/WEB-INF/jsp/accueil_partie.jsp";
    private static final String PAGE_HOME_JSP = "/";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoginBean model = new LoginBean();
        if ( model.isConnected( request ) ) {
            response.sendRedirect( request.getContextPath()+PAGE_ACCUEIL_PARTIE_JSP );
        } else {
            request.getRequestDispatcher( PAGE_HOME_JSP ).forward( request, response );
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
