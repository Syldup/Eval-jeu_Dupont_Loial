package controller;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/home"})
public class HomeController extends HttpServlet {

	private static final String PAGE_HOME_JSP = "/WEB-INF/jsp/home.jsp";
	private static final String PAGE_QUEST = "/question";

	@Override
	protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

		request.getRequestDispatcher( PAGE_HOME_JSP ).forward( request, response );
	}
}
